package video.api.java.sdk.infrastructure.unirest.caption;

import kong.unirest.HttpRequest;
import org.junit.jupiter.api.Test;
import video.api.java.sdk.domain.caption.Caption;
import video.api.java.sdk.domain.caption.CaptionClient;
import video.api.java.sdk.domain.exception.ResponseException;
import video.api.java.sdk.infrastructure.unirest.EmptySerializer;
import video.api.java.sdk.infrastructure.unirest.RequestBuilderInspector;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilderFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import static kong.unirest.HttpMethod.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnirestCaptionClientTest {
    private final RequestBuilderInspector inspector = new RequestBuilderInspector();
    private final CaptionClient client = createClient(inspector);

    @Test
    public void list() throws ResponseException {
        client.list("viXXX");

        HttpRequest request = inspector.buildRequest();

        assertEquals(GET, request.getHttpMethod());
        String url = request.getUrl();

        assertTrue(url.startsWith("/videos/viXXX/captions"));
    }

    @Test
    public void get() throws ResponseException {
        client.get("viXXX", Locale.FRENCH);

        HttpRequest request = inspector.buildRequest();

        assertEquals(GET, request.getHttpMethod());
        assertEquals("/videos/viXXX/captions/fr", request.getUrl());
    }

    @Test
    public void upload() throws ResponseException, IOException {
        URL url = getFileUrl();

        client.upload("viXXX", new File(url.getPath()), Locale.FRENCH);

        HttpRequest request = inspector.buildRequest();

        assertEquals(POST, request.getHttpMethod());
        assertEquals("/videos/viXXX/captions/fr", request.getUrl());
    }

    private URL getFileUrl() {
        return Thread.currentThread().getContextClassLoader().getResource("test.vtt");
    }

    @Test
    public void update() throws ResponseException, IOException {
        Caption caption = createDefault();

        client.update("viXXX", caption);

        HttpRequest request = inspector.buildRequest();

        assertEquals(PATCH, request.getHttpMethod());
        assertEquals("/videos/viXXX/captions/fr", request.getUrl());
    }

    @Test
    public void delete() throws ResponseException, IOException {
        client.delete("viXXX", Locale.FRENCH);

        HttpRequest request = inspector.buildRequest();

        assertEquals(DELETE, request.getHttpMethod());
        assertEquals("/videos/viXXX/captions/fr", request.getUrl());
    }

    private UnirestCaptionClient createClient(RequestBuilderInspector inspector) {
        return new UnirestCaptionClient(
                new RequestBuilderFactory(""),
                new EmptySerializer<>(),
                data -> createDefault(),
                inspector
        );
    }

    private Caption createDefault() {
        return new Caption(Locale.FRENCH, "/videos/viXXX/captions/fr", "https://cdn.api.video/vod/viXXX/captions/en.vtt");
    }
}