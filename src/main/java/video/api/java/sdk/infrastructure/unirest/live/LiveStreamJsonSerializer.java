package video.api.java.sdk.infrastructure.unirest.live;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import video.api.java.sdk.domain.asset.Assets;
import video.api.java.sdk.domain.live.LiveStream;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class LiveStreamJsonSerializer implements JsonSerializer<LiveStream> {
    private final JsonSerializer<Assets> assetsSerializer;

    public LiveStreamJsonSerializer(JsonSerializer<Assets> assetsSerializer) {
        this.assetsSerializer = assetsSerializer;
    }

    @Override
    public LiveStream deserialize(JSONObject data) throws JSONException {
        LiveStream liveStream = new LiveStream(data.getString("name"));

        liveStream.liveStreamId = data.getString("liveStreamId");

        if (data.has("streamKey"))
            liveStream.streamKey = data.getString("streamKey");
        if (data.has("record"))
            liveStream.record = data.getBoolean("record");
        if (data.has("broadcasting"))
            liveStream.broadcasting = data.getBoolean("broadcasting");
        if (data.has("assets"))
            liveStream.assets = assetsSerializer.deserialize(data.getJSONObject("assets"));
        if (data.has("playerId")) {
            liveStream.playerId = data.getString("playerId");
        }

        return liveStream;
    }

    @Override
    public List<LiveStream> deserialize(JSONArray data) throws JSONException {
        List<LiveStream> lives = new ArrayList<>();
        for (Object item : data) {
            lives.add(deserialize((JSONObject) item));
        }
        return lives;
    }

    @Override
    public JSONObject serialize(LiveStream liveStream) throws JSONException {
        JSONObject data = new JSONObject();
        if (liveStream.name != null) {
            data.put("name", liveStream.name);
        }
        data.put("record", liveStream.record);

        if (liveStream.playerId != null) {
            data.put("playerId", liveStream.playerId);
        }
        return data;
    }

}
