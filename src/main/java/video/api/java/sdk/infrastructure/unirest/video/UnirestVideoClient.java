package video.api.java.sdk.infrastructure.unirest.video;

import kong.unirest.JsonNode;
import org.json.JSONObject;
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

import java.io.*;

import static java.lang.Math.min;
import static kong.unirest.HttpMethod.*;

public class UnirestVideoClient implements VideoClient {

    private static final int CHUNK_SIZE = 128 * 1024 * 1024; // 128 MB

    private final RequestBuilderFactory requestBuilderFactory;
    private final JsonSerializer<Video> serializer;
    private final JsonDeserializer<UploadedVideo> deserializer;
    private final RequestExecutor requestExecutor;
    private final StatusDeserializer statusDeserializer = new StatusDeserializer();

    public UnirestVideoClient(RequestBuilderFactory requestBuilderFactory, JsonSerializer<Video> serializer, JsonDeserializer<UploadedVideo> deserializer, RequestExecutor requestExecutor) {
        this.requestBuilderFactory = requestBuilderFactory;
        this.serializer = serializer;
        this.deserializer = deserializer;
        this.requestExecutor = requestExecutor;
    }

    public UploadedVideo get(String videoId) throws ResponseException {
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

    public UploadedVideo createVideoContainer(Video video) throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(POST, "/videos")
                .withJson(serializer.serialize(video));
        JsonNode responseBody = requestExecutor.executeJson(request);
        return deserializer.deserialize(responseBody.getObject());
    }

    public UploadedVideo upload(File file) throws ResponseException {
        return upload(file, new Video(), null);
    }

    public UploadedVideo upload(File file, UploadProgressListener listener) throws ResponseException {
        return upload(file, new Video(), listener);
    }

    public UploadedVideo upload(File file, Video video) throws ResponseException {

        return upload(file, video, null);
    }

    public UploadedVideo upload(File file, Video video, UploadProgressListener listener) throws ResponseException {
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("Can't open file.");
        }

        if (isNewVideo(video)) {
            if (video.title == null) {
                video.title = file.getName();
            }
            video = createVideoContainer(video);
        }

        String videoId = ((UploadedVideo) video).videoId;
        int fileLength = (int) file.length();

        try {
            Thread.sleep(150);
        }
        catch (InterruptedException e) {
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
            }
            else {
                responseBody = uploadMultipleRequests(file, listener, videoId, fileLength);
            }

            return deserializer.deserialize(responseBody.getObject());
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public UploadedVideo uploadThumbnail(String videoId, File file) throws ResponseException, IOException {
        RequestBuilder request = requestBuilderFactory
                .create(POST, "/videos/" + videoId + "/thumbnail")
                .withFile(file);

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }

    public UploadedVideo updateThumbnail(String videoId, String timeCode) throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(PATCH, "/videos/" + videoId)
                .withJson(new JSONObject().put("timecode", timeCode));

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }

    public UploadedVideo update(UploadedVideo video) throws ResponseException {
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

    public Iterable<UploadedVideo> list() throws ResponseException, IllegalArgumentException {
        return list(new QueryParams());
    }

    public Iterable<UploadedVideo> list(QueryParams queryParams) throws ResponseException, IllegalArgumentException {
        return new IteratorIterable<>(new PageIterator<>(new UriPageLoader<>(
                requestBuilderFactory
                        .create(GET, "/videos")
                        .withQueryParams(queryParams.toMap()),
                requestExecutor,
                deserializer
        ), new PageQuery()));
    }

    private boolean isNewVideo(Video video) {
        return !(video instanceof UploadedVideo);
    }

    /////////////////////////Iterators//////////////////////////////

    private JsonNode uploadMultipleRequests(File file, UploadProgressListener listener, String videoId, int fileLength) throws IOException, ResponseException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        int copiedBytes = 0;
        int chunkCount = (int) Math.ceil((double) fileLength / CHUNK_SIZE);
        JsonNode responseBody = null;
        for (int chunkNum = 0; chunkNum < chunkCount; chunkNum++) {

            String chunkFileName = "upload-chunk-";
            int from = copiedBytes;
            copiedBytes = min(copiedBytes + CHUNK_SIZE, fileLength);
            int chunkFileSize = copiedBytes - from;

            String tmpdir = System.getProperty("java.io.tmpdir");

            try (FileInputStream chunkStream = new FileInputStream(file)) {
                //noinspection ResultOfMethodCallIgnored
                chunkStream.skip(from);

                byte[] b = new byte[chunkFileSize];
                File chunkFile = File.createTempFile(tmpdir, chunkFileName);

                RandomAccessFile randomAccessChunk = new RandomAccessFile(chunkFile, "rw");
                randomAccessFile.readFully(b);
                randomAccessChunk.write(b, 0, chunkFileSize);
                final InputStream inputStream = new FileInputStream(chunkFile);
                String rangeHeader = "bytes " + from + "-" + (copiedBytes - 1) + "/" + fileLength;

                RequestBuilder request = requestBuilderFactory
                        .create(POST, "/videos/" + videoId + "/source")
                        .withChunk(file.getName(), inputStream, chunkCount, chunkNum, listener)
                        .withHeader("Content-Range", rangeHeader);

                responseBody = requestExecutor.executeJson(request);

                chunkFile.deleteOnExit();
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
}
