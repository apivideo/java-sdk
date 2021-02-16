package video.api.java.sdk.infrastructure.unirest.live;

import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.Test;
import video.api.java.sdk.domain.live.LiveStreamInput;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LiveStreamInputSerializerTest {

    private LiveStreamInputSerializer serializer = new LiveStreamInputSerializer();

    @Test
    void serializeMin() {
        LiveStreamInput liveStream = new LiveStreamInput("test");

        JSONObject serialized = serializer.serialize(liveStream);

        assertEquals(liveStream.name, serialized.getString("name"));
        assertEquals(liveStream.record, serialized.getBoolean("record"));
        assertFalse(serialized.has("playerId"));
    }

    @Test
    void serializeMax() {
        LiveStreamInput liveStream = new LiveStreamInput("test");
        liveStream.record   = true;
        liveStream.playerId = "xxx";
        liveStream.isPublic = true;

        JSONObject serialized = serializer.serialize(liveStream);

        assertEquals(liveStream.record, serialized.getBoolean("record"));
        assertEquals(liveStream.playerId, serialized.getString("playerId"));
        assertEquals(liveStream.isPublic, serialized.getBoolean("public"));
    }
}