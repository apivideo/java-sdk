package video.api.java.sdk.infrastructure.unirest.video;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.video.Status;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

public class StatusDeserializer implements JsonDeserializer<Status> {
    private ReceivedBytesSerializer receivedBytesDeserializer = new ReceivedBytesSerializer();

    public Status deserialize(JSONObject data) throws JSONException {
        return new Status(
                data.has("ingest") ? deserializeIngest(data.getJSONObject("ingest")) : null,
                data.has("encoding") ? deserializeEncoding(data.getJSONObject("encoding")) : null
        );
    }

    private Status.Ingest deserializeIngest(JSONObject data) {
        return new Status.Ingest(
                data.getString("status"),
                data.getInt("filesize"),
                receivedBytesDeserializer.deserialize(data.getJSONArray("receivedBytes"))
        );
    }

    private Status.Encoding deserializeEncoding(JSONObject data) {
        return new Status.Encoding(
                data.getBoolean("playable"),
                data.getJSONArray("qualities").toList(),
                data.getJSONObject("metadata").toMap()
        );
    }
}
