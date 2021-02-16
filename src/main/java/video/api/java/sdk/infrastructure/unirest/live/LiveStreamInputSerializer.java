package video.api.java.sdk.infrastructure.unirest.live;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.live.LiveStreamInput;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

public class LiveStreamInputSerializer implements JsonSerializer<LiveStreamInput> {
    @Override
    public JSONObject serialize(LiveStreamInput object) throws JSONException {
        JSONObject data = new JSONObject();
        if (object.name != null) {
            data.put("name", object.name);
        }
        data.put("record", object.record);
        data.put("public", object.isPublic);

        if (object.playerId != null) {
            data.put("playerId", object.playerId);
        }

        return data;
    }
}
