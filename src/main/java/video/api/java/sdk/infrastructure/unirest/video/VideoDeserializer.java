package video.api.java.sdk.infrastructure.unirest.video;

import org.json.JSONException;
import org.json.JSONObject;
import video.api.java.sdk.domain.video.UploadedVideo;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

public class VideoDeserializer implements JsonDeserializer<UploadedVideo> {
    @Override
    public UploadedVideo deserialize(JSONObject data) throws JSONException {
        UploadedVideo video = new UploadedVideo(
                data.getString("videoId"),
                deserializeDateTime(data.getString("publishedAt")),
                deserializeDateTime(data.getString("updatedAt")),
                deserializeSourceInfo(data.getJSONObject("source")),
                convertJsonMapToStringMap(data.getJSONObject("assets"))
        );

        if (data.has("title")) {
            video.title = data.getString("title");
        }

        if (data.has("description")) {
            video.description = data.getString("description");
        }

        if (data.has("public")) {
            video.isPublic = data.getBoolean("public");
        }

        if (data.has("panoramic")) {
            video.panoramic = data.getBoolean("panoramic");
        }

        if (data.has("tags")) {
            video.tags.addAll(convertJsonArrayToStringList(data.getJSONArray("tags")));
        }

        if (data.has("metadata")) {
            video.metadata.putAll(convertKeyValueJsonArrayToMap(data.getJSONArray("metadata")));
        }

        if (data.has("playerId")) {
            video.playerId = data.getString("playerId");
        }

        return video;
    }

    private UploadedVideo.SourceInfo deserializeSourceInfo(JSONObject data) {
        return new UploadedVideo.SourceInfo(
                data.getString("type"),
                data.getString("uri")
        );
    }
}
