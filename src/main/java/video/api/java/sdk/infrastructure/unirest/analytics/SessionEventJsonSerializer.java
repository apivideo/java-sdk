package video.api.java.sdk.infrastructure.unirest.analytics;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.analytics.PlayerSessionEvent;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

public class SessionEventJsonSerializer implements JsonDeserializer<PlayerSessionEvent> {
    public PlayerSessionEvent deserialize(JSONObject data) throws JSONException {
        return new PlayerSessionEvent(
                data.getString("type"),
                deserializeDateTime(data.getString("emittedAt")),
                data.has("at") ? data.getInt("at") : null,
                data.has("from") ? data.getInt("from") : null,
                data.has("to") ? data.getInt("to") : null
        );
    }
}
