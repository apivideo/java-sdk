package video.api.java.sdk.infrastructure.unirest.video;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.video.VideoInput;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

public class VideoInputSerializer implements JsonSerializer<VideoInput> {
    @Override
    public JSONObject serialize(VideoInput object) throws JSONException {
        JSONObject data = new JSONObject();
        data.put("description", object.description);
        data.put("panoramic", object.panoramic);
        data.put("public", object.isPublic);
        data.put("mp4Support", object.mp4Support);
        if (!object.metadata.isEmpty()) {
            data.put("metadata", convertMapToKeyValueJson(object.metadata));
        }
        data.put("playerId", object.playerId);
        data.put("source", object.source);
        data.put("tags", new JSONArray(object.tags));
        data.put("title", object.title);

        return data;
    }
}
