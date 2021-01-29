package video.api.java.sdk.infrastructure.unirest.analytics;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.analytics.*;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

public class PlayerSessionDeserializer implements JsonDeserializer<PlayerSession> {

    @Override
    public PlayerSession deserialize(JSONObject data) throws JSONException {
        return new PlayerSession(
                data.has("session") ? deserializeInfo(data.getJSONObject("session")) : null,
                data.has("location") ? deserializeLocation(data.getJSONObject("location")) : null,
                data.has("referrer") ? deserializeReferrer(data.getJSONObject("referrer")) : null,
                data.has("device") ? deserializeDevice(data.getJSONObject("device")) : null,
                data.has("os") ? deserializeOperatingSystem(data.getJSONObject("os")) : null,
                data.has("client") ? deserializeClient(data.getJSONObject("client")) : null
        );
    }

    private Info deserializeInfo(JSONObject object) {
        return new Info(
                deserializeNullableString(object,"sessionId"),
                deserializeDateTime(deserializeNullableString(object,"endedAt")),
                deserializeDateTime(deserializeNullableString(object,"loadedAt")),
                object.has("metadata") ? convertKeyValueJsonArrayToMap(object.getJSONArray("metadata")) : null
        );
    }

    protected Location deserializeLocation(JSONObject object) {
        return new Location(
                deserializeNullableString(object, "country"),
                deserializeNullableString(object, "city")
        );
    }

    private Referrer deserializeReferrer(JSONObject object) {
        return new Referrer(
                deserializeNullableString(object, "url"),
                deserializeNullableString(object, "medium"),
                deserializeNullableString(object, "source"),
                deserializeNullableString(object, "searchTerm")
        );
    }

    private Device deserializeDevice(JSONObject object) {
        return new Device(
                deserializeNullableString(object, "model"),
                deserializeNullableString(object, "type"),
                deserializeNullableString(object, "vendor")
        );
    }

    private OperatingSystem deserializeOperatingSystem(JSONObject object) {
        return new OperatingSystem(
                deserializeNullableString(object, "name"),
                deserializeNullableString(object, "shortname"),
                deserializeNullableString(object, "version")
        );
    }

    private Client deserializeClient(JSONObject object) {
        return new Client(
                deserializeNullableString(object, "type"),
                deserializeNullableString(object, "name"),
                deserializeNullableString(object, "version")
        );
    }
}
