package video.api.java.sdk.infrastructure.unirest.analytics;

import video.api.java.sdk.domain.analytics.PlayerSessionEvent;
import video.api.java.sdk.domain.exception.ResponseException;
import video.api.java.sdk.domain.pagination.PageQuery;
import video.api.java.sdk.infrastructure.pagination.IteratorIterable;
import video.api.java.sdk.infrastructure.pagination.PageIterator;
import video.api.java.sdk.infrastructure.unirest.RequestExecutor;
import video.api.java.sdk.infrastructure.unirest.pagination.UriPageLoader;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilderFactory;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;

import static kong.unirest.HttpMethod.GET;

public class PlayerSessionEventClient implements video.api.java.sdk.domain.analytics.PlayerSessionEventClient {
    private final RequestBuilderFactory              requestBuilderFactory;
    private final JsonSerializer<PlayerSessionEvent> serializer;
    private final RequestExecutor                    requestExecutor;

    public PlayerSessionEventClient(RequestBuilderFactory requestBuilderFactory, JsonSerializer<PlayerSessionEvent> serializer, RequestExecutor requestExecutor) {
        this.requestBuilderFactory = requestBuilderFactory;
        this.serializer            = serializer;
        this.requestExecutor       = requestExecutor;
    }

    public Iterable<PlayerSessionEvent> list(String sessionId) throws ResponseException, IllegalArgumentException {
        return new IteratorIterable<>(new PageIterator<>(new UriPageLoader<>(
                requestBuilderFactory.create(GET, "/analytics/sessions/" + sessionId + "/events"),
                requestExecutor,
                serializer
        ), new PageQuery()));
    }
}
