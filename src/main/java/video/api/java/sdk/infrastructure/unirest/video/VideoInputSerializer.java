package video.api.java.sdk.infrastructure.unirest.video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import video.api.java.sdk.domain.video.Video;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

public class VideoInputSerializer implements JsonSerializer<Video> {
    @Override
    public JSONObject serialize(Video object) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("description", object.description);
        data.put("isPublic", object.isPublic);
        if (!object.metadata.isEmpty()) {
            data.put("metadata", convertMapToKeyValueJson(object.metadata));
        }
        data.put("playerId", object.playerId);
        data.put("tags", new JSONArray(object.tags));
        data.put("title", object.title);

        return data;
    }
}
