package video.api.java.sdk.infrastructure.pagination;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.pagination.Page;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

import java.util.ArrayList;

public class PageDeserializer<T> implements JsonDeserializer<Page<T>> {
    private final JsonDeserializer<T> inner;

    public PageDeserializer(JsonDeserializer<T> inner) {
        this.inner = inner;
    }

    @Override
    public Page<T> deserialize(JSONObject body) throws JSONException {
        if (!body.has("pagination")) {
            return new Page<>(new ArrayList<>(), 0, 1);
        }

        JSONObject pagination = body.getJSONObject("pagination");
        
        return new Page<>(
                inner.deserialize(body.getJSONArray("data")),
                pagination.getInt("pagesTotal"),
                pagination.getInt("currentPage")
        );
    }
}
