package video.api.java.sdk.infrastructure.unirest.video;

import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.Test;
import video.api.java.sdk.domain.video.VideoInput;

import static org.junit.jupiter.api.Assertions.*;

class VideoInputSerializerTest {
    private VideoInputSerializer serializer = new VideoInputSerializer();

    @Test
    void serialize() {
        VideoInput video = new VideoInput();
        video.title = "foo";
        video.mp4Support = true;
        JSONObject jsonVideo = serializer.serialize(video);
        assertEquals("foo", jsonVideo.getString("title"));
        assertTrue(jsonVideo.getBoolean("mp4Support"));
    }

    @Test
    void serializePanoramicDefault() {
        VideoInput video = new VideoInput();
        video.title = "foo";
        JSONObject jsonVideo = serializer.serialize(video);

        assertFalse(jsonVideo.getBoolean("panoramic"));
    }

    @Test
    void serializePanoramic() {
        VideoInput video = new VideoInput();
        video.title = "foo";
        video.panoramic = true;
        JSONObject jsonVideo = serializer.serialize(video);

        assertTrue(jsonVideo.getBoolean("panoramic"));
    }

    @Test
    void serializeSourceDefault() {
        VideoInput video = new VideoInput();
        video.title = "foo";
        JSONObject jsonVideo = serializer.serialize(video);

        assertTrue(jsonVideo.isNull("source"));
    }

    @Test
    void serializeSource() {
        String url = "https://localhost/video.mp4";
        VideoInput video = new VideoInput();
        video.title = "foo";
        video.source = url;
        JSONObject jsonVideo = serializer.serialize(video);

        assertEquals(url, jsonVideo.getString("source"));
    }
}
