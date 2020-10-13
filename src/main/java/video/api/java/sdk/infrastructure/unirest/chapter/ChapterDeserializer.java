package video.api.java.sdk.infrastructure.unirest.chapter;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.chapter.Chapter;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

public class ChapterDeserializer implements JsonDeserializer<Chapter> {

    @Override
    public Chapter deserialize(JSONObject data) throws JSONException {
        return new Chapter(
                data.getString("language"),
                data.getString("uri"),
                data.getString("src")
        );
    }
}
