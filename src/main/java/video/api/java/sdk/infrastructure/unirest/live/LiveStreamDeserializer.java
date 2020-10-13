package video.api.java.sdk.infrastructure.unirest.live;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.live.LiveStream;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

public class LiveStreamDeserializer implements JsonDeserializer<LiveStream> {
    @Override
    public LiveStream deserialize(JSONObject data) throws JSONException {
        LiveStream liveStream = new LiveStream(
                data.getString("name"),
                data.getString("liveStreamId"),
                data.getString("streamKey"),
                data.getBoolean("broadcasting")
        );

        if (data.has("record")) {
            liveStream.record = data.getBoolean("record");
        }

        if (data.has("assets")) {
            liveStream.assets.putAll(convertJsonMapToStringMap(data.getJSONObject("assets")));
        }

        if (data.has("playerId")) {
            liveStream.playerId = data.getString("playerId");
        }

        return liveStream;
    }
}
