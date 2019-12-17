package video.api.java.sdk.infrastructure.unirest.analytics;

import org.json.JSONException;
import org.json.JSONObject;
import video.api.java.sdk.domain.analytics.PlayerSessionEvent;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

import javax.xml.bind.DatatypeConverter;

public class SessionEventJsonSerializer implements JsonDeserializer<PlayerSessionEvent> {
    public PlayerSessionEvent deserialize(JSONObject data) throws JSONException {
        return new PlayerSessionEvent(
                data.getString("type"),
                DatatypeConverter.parseDateTime(data.getString("emittedAt")),
                data.has("at") ? data.getInt("at") : null,
                data.has("from") ? data.getInt("from") : null,
                data.has("to") ? data.getInt("to") : null
        );
    }
}
