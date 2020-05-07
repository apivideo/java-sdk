package video.api.java.sdk.infrastructure.unirest.video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import video.api.java.sdk.domain.video.UploadedVideo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VideoDeserializerTest {
    private VideoDeserializer deserializer = new VideoDeserializer();

    @Test
    void deserializeFailure() {
        assertThrows(JSONException.class, () -> deserializer.deserialize(new JSONObject()));
    }

    @Test
    void deserializeAllFailure() {
        JSONArray array = new JSONArray()
                .put(new JSONObject()
                    .put("unexpected", "data")
                );

        assertThrows(JSONException.class, () -> deserializer.deserialize(array));
    }

    @Test
    void deserializeMinimalSuccess() {

        UploadedVideo video = deserializer.deserialize(createMinimalVideo());

        assertEquals("viXXX", video.videoId);
        assertNotNull(video.publishedAt);
        assertNotNull(video.updatedAt);
        assertEquals("toto", video.title);
        assertTrue(video.isPublic);
        assertTrue(video.panoramic);
        assertEquals("upload", video.sourceInfo.type);
        assertEquals("/videos/vi2SRWJ6ipruaD9K73CJbP0V/source", video.sourceInfo.uri);
    }

    private JSONObject createMinimalVideo() {
        return new JSONObject()
                .put("videoId", "viXXX")
                .put("publishedAt", "2019-08-28T16:25:51+02:00")
                .put("updatedAt", "2019-08-28T16:25:51+02:00")
                .put("title", "toto")
                .put("isPublic", true)
                .put("panoramic", true)
                .put("source", new JSONObject()
                        .put("type", "upload")
                        .put("uri", "/videos/vi2SRWJ6ipruaD9K73CJbP0V/source")
                )
                .put("assets", new JSONObject()
                        .put("iframe", "<iframe src=\"https://embed.api.video/vod/virMgDJYvjHzoFZZYgMCkvC\" width=\"100%\" height=\"100%\" frameborder=\"0\" scrolling=\"no\" allowfullscreen=\"\"></iframe>")
                        .put("player", "https://embed.api.video/vod/virMgDJYvjHzoFZZYgMCkvC")
                        .put("hls", "https://cdn.api.video/vod/virMgDJYvjHzoFZZYgMCkvC/hls/manifest.m3u8")
                        .put("thumbnail", "https://cdn.api.video/vod/virMgDJYvjHzoFZZYgMCkvC/thumbnail.jpg")
                );
    }

    @Test
    void deserializeMaximalSuccess() {
        JSONObject deserialized = createMaximalVideo();

        UploadedVideo video = deserializer.deserialize(deserialized);

        assertEquals("desc", video.description);
        assertEquals("foo", video.tags.get(0));
        assertEquals("bar", video.tags.get(1));
        assertEquals("__age__", video.metadata.get("age"));
        assertEquals("Nader", video.metadata.get("actor"));
    }

    private JSONObject createMaximalVideo() {
        return createMinimalVideo()
                .put("description", "desc")
                .put("panoramic", true)
                .put("tags", new JSONArray()
                        .put("foo")
                        .put("bar")
                )
                .put("metadata", new JSONArray()
                        .put(new JSONObject()
                                .put("key", "age")
                                .put("value", "__age__")
                        )
                        .put(new JSONObject()
                                .put("key", "actor")
                                .put("value", "Nader")
                        )
                );
    }

    @Test
    void deserializeCollection() {
        JSONArray collection = new JSONArray()
                .put(createMaximalVideo())
                .put(createMinimalVideo());

        List<UploadedVideo> videos = deserializer.deserialize(collection);

        assertEquals(2, videos.size());
        assertEquals("viXXX", videos.get(0).videoId);
    }
}