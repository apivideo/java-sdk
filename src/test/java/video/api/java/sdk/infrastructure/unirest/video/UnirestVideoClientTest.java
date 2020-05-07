package video.api.java.sdk.infrastructure.unirest.video;

import kong.unirest.HttpRequest;
import org.junit.jupiter.api.Test;
import video.api.java.sdk.domain.QueryParams;
import video.api.java.sdk.domain.exception.ResponseException;
import video.api.java.sdk.domain.video.UploadedVideo;
import video.api.java.sdk.domain.video.VideoClient;
import video.api.java.sdk.domain.video.Video;
import video.api.java.sdk.infrastructure.unirest.EmptySerializer;
import video.api.java.sdk.infrastructure.unirest.RequestBuilderInspector;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilderFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static kong.unirest.HttpMethod.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnirestVideoClientTest {
    private RequestBuilderInspector inspector = new RequestBuilderInspector();
    private VideoClient client = createClient(inspector);

    @Test
    public void list() throws ResponseException {
        client.list();

        HttpRequest request = inspector.buildRequest();

        assertEquals(GET, request.getHttpMethod());
        String url = request.getUrl();

        assertTrue(url.startsWith("/videos"));
    }

    @Test
    public void listSorted() throws ResponseException, URISyntaxException {
        QueryParams params = new QueryParams();
        params.sortBy = "publishedAt";
        params.sortOrder = "asc";
        client.list(params);

        HttpRequest request = inspector.buildRequest();

        assertEquals(GET, request.getHttpMethod());
        String url = request.getUrl();

        assertTrue(url.startsWith("/videos"));
        assertTrue(url.contains("sortBy=publishedAt"));
        assertTrue(url.contains("sortOrder=asc"));

        params.sortOrder = "desc";
        client.list(params);
        request = inspector.buildRequest();
        url = request.getUrl();

        assertTrue(url.contains("sortOrder=desc"));
    }

    @Test
    public void get() throws ResponseException {
        client.get("viXXX");

        HttpRequest request = inspector.buildRequest();

        assertEquals(GET, request.getHttpMethod());
        assertEquals("/videos/viXXX", request.getUrl());
    }

    @Test
    public void getStatus() throws ResponseException {
        client.getStatus("viXXX");

        HttpRequest request = inspector.buildRequest();

        assertEquals(GET, request.getHttpMethod());
        assertEquals("/videos/viXXX/status", request.getUrl());
    }

    @Test
    public void create() throws ResponseException {
        Video videoInput = new Video();
        videoInput.title = "foo";

        client.createVideoContainer(videoInput);

        HttpRequest request = inspector.buildRequest();

        assertEquals(POST, request.getHttpMethod());
        assertEquals("/videos", request.getUrl());
    }

    @Test
    public void upload() throws ResponseException {
        URL url = getFileUrl();

        client.upload(new File(url.getPath()));

        HttpRequest request = inspector.buildRequest();

        assertEquals(POST, request.getHttpMethod());
        assertEquals("/videos/viXXX/source", request.getUrl());
    }

    @Test
    public void uploadThumbnail() throws ResponseException, IOException {
        URL url = getFileUrl();

        client.uploadThumbnail("viXXX", new File(url.getPath()));

        HttpRequest request = inspector.buildRequest();

        assertEquals(POST, request.getHttpMethod());
        assertEquals("/videos/viXXX/thumbnail", request.getUrl());
    }

    private URL getFileUrl() {
        return Thread.currentThread().getContextClassLoader().getResource("test.vtt");
    }

    @Test
    public void update() throws ResponseException, IOException {
        UploadedVideo video = createDefault();

        client.update(video);

        HttpRequest request = inspector.buildRequest();

        assertEquals(PATCH, request.getHttpMethod());
        assertEquals("/videos/viXXX", request.getUrl());
    }

    @Test
    public void delete() throws ResponseException, IOException {
        client.delete("viXXX");

        HttpRequest request = inspector.buildRequest();

        assertEquals(DELETE, request.getHttpMethod());
        assertEquals("/videos/viXXX", request.getUrl());
    }

    private UnirestVideoClient createClient(RequestBuilderInspector inspector) {
        return new UnirestVideoClient(
                new RequestBuilderFactory(""),
                new EmptySerializer<>(),
                data -> createDefault(),
                inspector
        );
    }

    private UploadedVideo createDefault() {
        return new UploadedVideo("viXXX", new GregorianCalendar(), new GregorianCalendar(), new UploadedVideo.SourceInfo("", ""), new HashMap<>());
    }
}