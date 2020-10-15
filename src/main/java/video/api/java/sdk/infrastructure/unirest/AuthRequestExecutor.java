package video.api.java.sdk.infrastructure.unirest;

import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.exception.ClientException;
import video.api.java.sdk.domain.exception.ResponseException;
import video.api.java.sdk.domain.exception.ServerException;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilder;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilderFactory;

import static kong.unirest.HttpMethod.POST;

public class AuthRequestExecutor implements RequestExecutor {

    private final RequestBuilderFactory requestBuilderFactory;
    private final String apiKey;
    private String accessToken;
    private String refreshToken;

    public AuthRequestExecutor(RequestBuilderFactory requestBuilderFactory, String apiKey) {
        this.requestBuilderFactory = requestBuilderFactory;
        this.apiKey = apiKey;
    }

    public JsonNode executeJson(RequestBuilder requestBuilder) throws ResponseException {
        if (accessToken == null) {
            this.authByApiKey();
        }

        requestBuilder.headers.put("Authorization", "Bearer " + accessToken);
        HttpRequest<?> request = requestBuilder.build();

        HttpResponse<JsonNode> response = request.asJson();

        if (response.getStatus() == 401 && response.getBody().getObject().getString("hint").equals("Access token is invalid")) {
            this.authByRefreshToken();

            return executeJson(requestBuilder);
        }

        if (response.getStatus() >= 500) {
            throw new ServerException("A server issue occurred.", response.getBody(), response.getStatus());
        }

        if (response.getStatus() >= 400) {
            String message = parseErrorBody(response.getBody());
            throw new ClientException(message == null ? "A client issue occurred." : message, response.getBody(), response.getStatus());
        }

        return response.getBody();
    }

    private String parseErrorBody(JsonNode body) {
        try {
            return body.getObject().getString("title");
        } catch (Exception e) {
            return null;
        }
    }

    private void authByApiKey() throws ClientException {
        HttpResponse response = requestBuilderFactory.create(POST, "/auth/api-key")
                .withJson(new JSONObject().put("apiKey", apiKey))
                .build()
                .asJson();

        //noinspection unchecked
        parseAccessToken(response);
    }

    private void authByRefreshToken() throws ClientException {
        HttpResponse response = requestBuilderFactory.create(POST, "/auth/refresh")
                .withJson(new JSONObject().put("refreshToken", this.refreshToken))
                .build()
                .asJson();

        //noinspection unchecked
        parseAccessToken(response);
    }

    private void parseAccessToken(HttpResponse<JsonNode> response) throws ClientException {

        if (response.getStatus() >= 400) {
            throw new ClientException("Authentication failed.", response.getBody(), response.getStatus());
        }

        JSONObject responseBody = response.getBody().getObject();

        this.accessToken = responseBody.getString("access_token");
        this.refreshToken = responseBody.getString("refresh_token");
    }

}


