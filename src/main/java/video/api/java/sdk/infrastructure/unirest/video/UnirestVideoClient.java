package video.api.java.sdk.infrastructure.unirest.video;

import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.QueryParams;
import video.api.java.sdk.domain.exception.ResponseException;
import video.api.java.sdk.domain.pagination.PageQuery;
import video.api.java.sdk.domain.video.*;
import video.api.java.sdk.infrastructure.pagination.IteratorIterable;
import video.api.java.sdk.infrastructure.pagination.PageIterator;
import video.api.java.sdk.infrastructure.unirest.RequestExecutor;
import video.api.java.sdk.infrastructure.unirest.pagination.UriPageLoader;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilder;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilderFactory;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static kong.unirest.HttpMethod.*;

public class UnirestVideoClient implements VideoClient {
    public static final int CHUNK_SIZE = 128 * 1024 * 1024; // 128 MB

    private final RequestBuilderFactory      requestBuilderFactory;
    private final JsonSerializer<VideoInput> serializer;
    private final JsonDeserializer<Video>    deserializer;
    private final RequestExecutor            requestExecutor;
    private final StatusDeserializer         statusDeserializer = new StatusDeserializer();

    public UnirestVideoClient(RequestBuilderFactory requestBuilderFactory, JsonSerializer<VideoInput> serializer, JsonDeserializer<Video> deserializer, RequestExecutor requestExecutor) {
        this.requestBuilderFactory = requestBuilderFactory;
        this.serializer            = serializer;
        this.deserializer          = deserializer;
        this.requestExecutor       = requestExecutor;
    }

    public Video get(String videoId) throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(GET, "/videos/" + videoId);

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }

    public Status getStatus(String videoId) throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(GET, "/videos/" + videoId + "/status");

        JsonNode responseBody = requestExecutor.executeJson(request);

        return statusDeserializer.deserialize(responseBody.getObject());
    }

    public Video create(VideoInput videoInput) throws ResponseException {
        if (videoInput.title == null) {
            videoInput.title = "";
        }

        RequestBuilder request = requestBuilderFactory
                .create(POST, "/videos")
                .withJson(serializer.serialize(videoInput));

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }

    public Video upload(File file) throws ResponseException {
        return upload(file, new VideoInput(), null);
    }

    public Video upload(File file, UploadProgressListener listener) throws ResponseException {
        return upload(file, new VideoInput(), listener);
    }

    public Video upload(File file, VideoInput videoInput) throws ResponseException {
        if (videoInput.title == null) {
            return upload(file);
        }

        return upload(file, videoInput, null);
    }

    public Video upload(File file, VideoInput videoInput, UploadProgressListener listener) throws ResponseException {
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("Can't open file.");
        }
        String videoId;
        if (videoInput instanceof Video) {
            videoId = ((Video) videoInput).videoId;
        } else {
            if (videoInput.title == null) {
                videoInput.title = file.getName();
            }
            videoId = create(videoInput).videoId;
        }

        long fileLength = file.length();

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
        }

        try {
            // Upload in a single request when file is small enough
            if (fileLength <= 0) {
                throw new IllegalArgumentException("Source is empty.");
            }

            JsonNode responseBody;
            if (fileLength < CHUNK_SIZE) {
                responseBody = uploadSingleRequest(listener, file, videoId);
            } else {
                responseBody = uploadMultipleRequests(file, listener, videoId);
            }

            return deserializer.deserialize(responseBody.getObject());
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    private JsonNode uploadMultipleRequests(File file, UploadProgressListener listener, String videoId) throws IOException, ResponseException {
        long     fileLength   = file.length();
        int      chunkCount   = (int) Math.ceil((double) fileLength / CHUNK_SIZE);
        JsonNode responseBody = null;

        for (int chunkNum = 0; chunkNum < chunkCount; chunkNum++) {
            int from = chunkNum * CHUNK_SIZE;

            try (InputStream chunk = new ChunkInputStream(file, from, CHUNK_SIZE)) {
                long chunkSize = Math.min(CHUNK_SIZE, chunk.available());

                String rangeHeader = "bytes " + from + "-" + (from + chunkSize - 1) + "/" + fileLength;

                RequestBuilder request = requestBuilderFactory
                        .create(POST, "/videos/" + videoId + "/source")
                        .withInputStream(file.getName(), chunk, chunkCount, chunkNum, listener)
                        .withHeader("Content-Range", rangeHeader);

                responseBody = requestExecutor.executeJson(request);
            }
        }

        return responseBody;
    }

    private JsonNode uploadSingleRequest(UploadProgressListener listener, File file, String videoId) throws ResponseException, IOException {
        RequestBuilder request = requestBuilderFactory
                .create(POST, "/videos/" + videoId + "/source")
                .withFile(file, listener);

        return requestExecutor.executeJson(request);
    }

    public Video uploadThumbnail(String videoId, File file) throws ResponseException, IOException {
        RequestBuilder request = requestBuilderFactory
                .create(POST, "/videos/" + videoId + "/thumbnail")
                .withFile(file);

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }

    public Video updateThumbnail(String videoId, String timeCode) throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(PATCH, "/videos/" + videoId + "/thumbnail")
                .withJson(new JSONObject().put("timecode", timeCode));

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }

    public Video update(Video video) throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(PATCH, "/videos/" + video.videoId)
                .withJson(serializer.serialize(video));

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }


    public void delete(String videoId) throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(DELETE, "/videos/" + videoId);

        requestExecutor.executeJson(request);
    }

    /////////////////////////Iterators//////////////////////////////

    public Iterable<Video> list() throws ResponseException, IllegalArgumentException {
        return list(new QueryParams());
    }

    public Iterable<Video> list(QueryParams queryParams) throws ResponseException, IllegalArgumentException {
        return new IteratorIterable<>(new PageIterator<>(new UriPageLoader<>(
                requestBuilderFactory
                        .create(GET, "/videos")
                        .withQueryParams(queryParams.toMap()),
                requestExecutor,
                deserializer
        ), new PageQuery()));
    }

}
