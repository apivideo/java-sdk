package video.api.java.sdk.infrastructure.unirest.account;

import kong.unirest.JsonNode;
import video.api.java.sdk.domain.account.Account;
import video.api.java.sdk.domain.exception.ResponseException;
import video.api.java.sdk.infrastructure.unirest.RequestExecutor;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilder;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilderFactory;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

import static kong.unirest.HttpMethod.GET;

public class AccountClient {
    private final RequestBuilderFactory     requestBuilderFactory;
    private final JsonDeserializer<Account> deserializer;
    private final RequestExecutor           requestExecutor;

    public AccountClient(RequestBuilderFactory requestBuilderFactory, JsonDeserializer<Account> deserializer, RequestExecutor requestExecutor) {
        this.requestBuilderFactory = requestBuilderFactory;
        this.deserializer          = deserializer;
        this.requestExecutor       = requestExecutor;
    }

    public Account get() throws ResponseException {
        RequestBuilder request = requestBuilderFactory
                .create(GET, "/account");

        JsonNode responseBody = requestExecutor.executeJson(request);

        return deserializer.deserialize(responseBody.getObject());
    }

}