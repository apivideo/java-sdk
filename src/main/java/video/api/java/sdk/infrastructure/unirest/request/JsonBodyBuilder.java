package video.api.java.sdk.infrastructure.unirest.request;

import kong.unirest.HttpRequest;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.json.JSONObject;

public class JsonBodyBuilder implements BodyBuilder {
    private final JSONObject data;

    public JsonBodyBuilder(JSONObject data) {
        this.data = data;
    }

    @Override
    public HttpRequest build(HttpRequestWithBody request) {
        return request.body(data);
    }
}
