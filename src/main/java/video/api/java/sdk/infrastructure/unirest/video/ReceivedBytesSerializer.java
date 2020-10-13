package video.api.java.sdk.infrastructure.unirest.video;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.video.ReceivedBytes;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

public class ReceivedBytesSerializer implements JsonDeserializer<ReceivedBytes> {

    public ReceivedBytes deserialize(JSONObject data) throws JSONException {
        return new ReceivedBytes(
                data.getInt("to"),
                data.getInt("from"),
                data.getInt("total")
        );
    }
}
