package video.api.java.sdk.infrastructure.unirest.caption;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.caption.CaptionInput;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

public class CaptionInputSerializer implements JsonSerializer<CaptionInput> {

    public JSONObject serialize(CaptionInput object) throws JSONException {
        JSONObject data = new JSONObject();

        data.put("default", object.isDefault);

        return data;
    }

}
