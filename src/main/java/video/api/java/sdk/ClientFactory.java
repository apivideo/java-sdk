package video.api.java.sdk;


import video.api.java.sdk.infrastructure.unirest.AuthRequestExecutor;
import video.api.java.sdk.infrastructure.unirest.account.AccountClient;
import video.api.java.sdk.infrastructure.unirest.account.AccountDeserializer;
import video.api.java.sdk.infrastructure.unirest.analytics.*;
import video.api.java.sdk.infrastructure.unirest.caption.UnirestCaptionClient;
import video.api.java.sdk.infrastructure.unirest.caption.CaptionDeserializer;
import video.api.java.sdk.infrastructure.unirest.caption.CaptionInputSerializer;
import video.api.java.sdk.infrastructure.unirest.chapter.ChapterDeserializer;
import video.api.java.sdk.infrastructure.unirest.chapter.UnirestChapterClientFactory;
import video.api.java.sdk.infrastructure.unirest.live.LiveStreamClient;
import video.api.java.sdk.infrastructure.unirest.live.LiveStreamDeserializer;
import video.api.java.sdk.infrastructure.unirest.live.LiveStreamInputSerializer;
import video.api.java.sdk.infrastructure.unirest.player.PlayerClient;
import video.api.java.sdk.infrastructure.unirest.player.PlayerDeserializer;
import video.api.java.sdk.infrastructure.unirest.player.PlayerInputSerializer;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilderFactory;
import video.api.java.sdk.infrastructure.unirest.video.UnirestVideoClient;
import video.api.java.sdk.infrastructure.unirest.video.VideoDeserializer;
import video.api.java.sdk.infrastructure.unirest.video.VideoInputSerializer;

public class ClientFactory {
    public Client create(String apiKey) {
        return create(apiKey, "https://ws.api.video");
    }

    public Client createSandbox(String apiKey) {
        return create(apiKey, "https://sandbox.api.video");
    }

    protected Client create(String apiKey, String baseUri) {
        RequestBuilderFactory requestBuilderFactory = new RequestBuilderFactory(baseUri);
        AuthRequestExecutor requestExecutor = new AuthRequestExecutor(requestBuilderFactory, apiKey);

        return new Client(
                new AccountClient(requestBuilderFactory, new AccountDeserializer(), requestExecutor),
                new UnirestCaptionClient(requestBuilderFactory, new CaptionInputSerializer(), new CaptionDeserializer(), requestExecutor),
                new LiveStreamClient(requestBuilderFactory, new LiveStreamInputSerializer(), new LiveStreamDeserializer(), requestExecutor),
                new LiveStreamSessionClient(requestBuilderFactory, new PlayerSessionDeserializer(), requestExecutor),
                new PlayerClient(requestBuilderFactory, new PlayerInputSerializer(), new PlayerDeserializer(), requestExecutor),
                new PlayerSessionEventClient(requestBuilderFactory, new SessionEventJsonSerializer(), requestExecutor),
                new UnirestVideoClient(requestBuilderFactory, new VideoInputSerializer(), new VideoDeserializer(), requestExecutor),
                new VideoSessionClient(requestBuilderFactory, new PlayerSessionDeserializer(), requestExecutor),
                new UnirestChapterClientFactory(requestBuilderFactory, new ChapterDeserializer(), requestExecutor)
        );
    }
}
