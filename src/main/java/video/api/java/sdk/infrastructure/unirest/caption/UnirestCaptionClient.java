package video.api.java.sdk.infrastructure.unirest.caption;

import kong.unirest.JsonNode;
import video.api.java.sdk.domain.caption.Caption;
import video.api.java.sdk.domain.caption.CaptionClient;
import video.api.java.sdk.domain.caption.CaptionInput;
import video.api.java.sdk.domain.exception.ResponseException;
import video.api.java.sdk.domain.pagination.PageQuery;
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
import java.util.Locale;

import static kong.unirest.HttpMethod.*;

public class UnirestCaptionClient implements CaptionClient {
    private final RequestBuilderFactory        requestBuilderFactory;
    private final JsonSerializer<CaptionInput> serializer;
    private final JsonDeserializer<Caption>    deserializer;
    private final RequestExecutor              requestExecutor;

    public UnirestCaptionClient(RequestBuilderFactory requestBuilderFactory, JsonSerializer<CaptionInput> serializer, JsonDeserializer<Caption> deserializer, RequestExecutor requestExecutor) {
        this.requestBuilderFactory = requestBuilderFactory;
        this.serializer            = serializer;
        this.deserializer          = deserializer;
        this.requestExecutor       = requestExecutor;
    }

    public Caption get(String videoId, Locale language) throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(GET, buildCaptionUrl(videoId, language));

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }

    private String buildCaptionUrl(String videoId, Locale language) {
        return "/videos/" + videoId + "/captions/" + language.getLanguage().toLowerCase();
    }

    public Iterable<Caption> list(String videoId) throws ResponseException, IllegalArgumentException {
        return new IteratorIterable<>(new PageIterator<>(new UriPageLoader<>(
                requestBuilderFactory
                        .create(GET, "/videos/" + videoId + "/captions"),
                requestExecutor,
                deserializer
        ), new PageQuery()));
    }

    public Caption upload(String videoId, File file, Locale language) throws ResponseException, IOException {
        RequestBuilder request = requestBuilderFactory
                .create(POST, buildCaptionUrl(videoId, language))
                .withFile(file);

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }

    public Caption update(String videoId, CaptionInput captionInput) throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(PATCH, buildCaptionUrl(videoId, captionInput.language))
                .withJson(serializer.serialize(captionInput));

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }

    public void delete(String videoId, Locale language) throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(DELETE, buildCaptionUrl(videoId, language));

        requestExecutor.executeJson(request);
    }
}
