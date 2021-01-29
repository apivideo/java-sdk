package video.api.java.sdk.infrastructure.unirest.serializer;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface JsonDeserializer<T> {
    T deserialize(JSONObject data) throws JSONException;

    default List<T> deserialize(JSONArray data) throws JSONException {
        List<T> list = new ArrayList<>();

        for (Object item : data) {
            list.add(deserialize((JSONObject) item));
        }

        return list;
    }

    default Map<String, Object> convertKeyValueJsonArrayToMap(JSONArray array) {
        Map<String, Object> map = new HashMap<>();

        for (Object object : array) {
            JSONObject jsonObject = (JSONObject) object;

            Object value = jsonObject.isNull("value") ? null : jsonObject.get("value");

            map.put(jsonObject.getString("key"), value);
        }

        return map;
    }

    default List<String> convertJsonArrayToStringList(JSONArray array) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            stringList.add(array.getString(i));
        }

        return stringList;
    }

    default Map<String, String> convertJsonMapToStringMap(JSONObject map) {
        return map.toMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
    }

    default Calendar deserializeDateTime(String dateTime) {
        if(dateTime == null) {
            return null;
        }

        try {
            return DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(dateTime)
                    .toGregorianCalendar();
        } catch (DatatypeConfigurationException e) {
            throw new JSONException(e.getMessage());
        }
    }

    default <T> T deserializeNullableValue(JSONObject jsonObject, Function<String, T> deserializer, String key) {
        return jsonObject.has(key) && !jsonObject.isNull(key) ? deserializer.apply(key) : null;
    }

    default String deserializeNullableString(JSONObject jsonObject, String key) {
        return deserializeNullableValue(jsonObject, jsonObject::getString, key);
    }
}
