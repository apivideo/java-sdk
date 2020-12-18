package video.api.java.sdk.infrastructure.unirest.video;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.video.Links;
import video.api.java.sdk.domain.video.LiveStreamSource;
import video.api.java.sdk.domain.video.Video;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.List;

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

        if (!data.isNull("title")) {
            video.title = data.getString("title");
        }

        if (!data.isNull("description")) {
            video.description = data.getString("description");
        }

        if (!data.isNull("public")) {
            video.isPublic = data.getBoolean("public");
        }

        if (!data.isNull("panoramic")) {
            video.panoramic = data.getBoolean("panoramic");
        }

        if (!data.isNull("mp4Support")) {
            video.mp4Support = data.getBoolean("mp4Support");
        }

        if (data.has("tags")) {
            video.tags.addAll(convertJsonArrayToStringList(data.getJSONArray("tags")));
        }

        if (data.has("metadata")) {
            video.metadata.putAll(convertKeyValueJsonArrayToMap(data.getJSONArray("metadata")));
        }

        if (!data.isNull("playerId")) {
            video.playerId = data.getString("playerId");
        }

        return video;
    }

    private Video.SourceInfo deserializeSourceInfo(JSONObject data) {
        System.out.println(data);
        return new Video.SourceInfo(
                data.getString("type"),
                data.isNull("uri") ? null : data.getString("uri"),
                data.isNull("liveStream") ? null : deserializeLiveStream(data.getJSONObject("liveStream"))
        );
    }

    private LiveStreamSource deserializeLiveStream(JSONObject data){
        return new LiveStreamSource(
                data.getString("liveStreamId"),
                deserializeLinks(data.getJSONArray("links"))
        );
    }

    private List<Links> deserializeLinks(JSONArray data){
        List<Links> links = new ArrayList<>();
        data.forEach(item ->{
            JSONObject obj = (JSONObject) item;
            links.add(deserializeLink(obj));
        });
        return links;
    }

    private Links deserializeLink(JSONObject data){
        return new Links(
                data.getString("rel"),
                data.getString("uri")
        );
    }
}
