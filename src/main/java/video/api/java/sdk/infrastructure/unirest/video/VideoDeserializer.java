package video.api.java.sdk.infrastructure.unirest.video;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.video.Video;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

public class VideoDeserializer implements JsonDeserializer<Video> {
    @Override
    public Video deserialize(JSONObject data) throws JSONException {
        Video video = new Video(
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

        if (data.has("mp4Support")) {
            video.mp4Support = data.getBoolean("mp4Support");
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

    private Video.SourceInfo deserializeSourceInfo(JSONObject data) {
        return new Video.SourceInfo(
                data.getString("type"),
                data.has("uri") ? data.getString("uri") : null
        );
    }
}
