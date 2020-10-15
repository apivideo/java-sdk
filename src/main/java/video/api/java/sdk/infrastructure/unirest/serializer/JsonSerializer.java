package video.api.java.sdk.infrastructure.unirest.serializer;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public interface JsonSerializer<T> {
    JSONObject serialize(T object) throws JSONException;

    default JSONArray convertMapToKeyValueJson(Map<String, Object> map) {
        JSONArray array = new JSONArray();

        for (Map.Entry<String, Object> e : map.entrySet()) {
            array.put(
                    new JSONObject()
                            .put("key", e.getKey())
                            .put("value", e.getValue())
            );
        }

        return array;
    }
}
