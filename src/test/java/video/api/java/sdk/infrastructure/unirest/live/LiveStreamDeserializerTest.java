package video.api.java.sdk.infrastructure.unirest.live;

import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.Test;
import video.api.java.sdk.domain.live.LiveStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LiveStreamDeserializerTest {

    private LiveStreamDeserializer deserializer = new LiveStreamDeserializer();

    @Test
    void deserializeMin() {
        JSONObject json = createLiveStreamMinimal();

        LiveStream liveStream = deserializer.deserialize(json);

        assertEquals(json.getString("liveStreamId"), liveStream.liveStreamId);
        assertEquals(json.getString("name"), liveStream.name);
        assertEquals(json.getString("streamKey"), liveStream.streamKey);
    }

    private JSONObject createLiveStreamMinimal() {
        return new JSONObject()
                .put("liveStreamId", "liSuccess")
                .put("name", "test")
                .put("streamKey", "xxx")
                .put("broadcasting", true);
    }

    @Test
    void deserializeMax() {
        JSONObject assets = new JSONObject()
                .put("iframe", "<iframe src=\"...\" width=\"100%\" height=\"100%\" frameborder=\"0\" scrolling=\"no\" allowfullscreen=\"\"></iframe>")
                .put("player", "...")
                .put("hls", "...")
                .put("thumbnail", "...");

        JSONObject json = createLiveStreamMinimal()
                .put("record", true)
                .put("assets", assets);

        LiveStream liveStream = deserializer.deserialize(json);
        assertEquals(json.getString("streamKey"), liveStream.streamKey);
        assertEquals(json.getBoolean("record"), liveStream.record);
        assertEquals(json.getBoolean("broadcasting"), liveStream.broadcasting);
        assertEquals(assets.getString("iframe"), liveStream.assets.get("iframe"));
        assertEquals(assets.getString("player"), liveStream.assets.get("player"));
        assertEquals(assets.getString("hls"), liveStream.assets.get("hls"));
        assertEquals(assets.getString("thumbnail"), liveStream.assets.get("thumbnail"));
    }
}