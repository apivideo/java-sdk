package video.api.java.sdk.infrastructure.unirest.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import video.api.java.sdk.domain.QueryParams;
import video.api.java.sdk.domain.exception.ResponseException;
import video.api.java.sdk.domain.player.Player;
import video.api.java.sdk.domain.player.PlayerInput;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilderFactory;
import video.api.java.sdk.infrastructure.unirest.TestRequestExecutor;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("WeakerAccess")
class PlayerClientTest {
    PlayerClient playerClient;
    PlayerClient playerResponseException;

    @BeforeEach
    void setUp() {
        TestRequestExecutor executorFailure = new TestRequestExecutor();
        executorFailure.exception = new ResponseException("foo", executorFailure.responseFailure(), 400);

        playerResponseException = new PlayerClient(
                new RequestBuilderFactory(""),
                new PlayerInputSerializer(),
                new PlayerDeserializer(),
                executorFailure
        );
        playerClient            = new PlayerClient(
                new RequestBuilderFactory(""),
                new PlayerInputSerializer(),
                new PlayerDeserializer(),
                new TestRequestExecutor()
        );

    }

    @Test
    void get() throws ResponseException {
        assertNotNull(playerClient.get("plSuccess"));
    }

    @Test
    void create() throws ResponseException {
        assertNotNull(playerClient.create(new PlayerInput()));
    }

    @Test
    void update() throws ResponseException {
        Player player = new Player("plSuccess", new Player.Assets("", ""));

        assertNotNull(playerClient.update(player));
    }

    @Test
    void uploadFailureLogo() {
        assertThrows(IOException.class, () -> playerClient.uploadLogo("plSuccess", new File("Failure Source"), "test.fr"));
    }

    @Test
    void deleteLogo() {
        assertDoesNotThrow(
                () -> playerClient.deleteLogo("plSuccess")
        );
    }

    @Test
    void getPlayerFailure() {
        assertThrows(ResponseException.class, () -> playerResponseException.get("plFailure"));
    }

    @Test
    void delete() {

    }

    @Test
    void list() throws ResponseException {
        assertNotNull(playerClient.list());
    }

    @Test
    void listWithParams() throws ResponseException {
        assertNotNull(playerClient.list(new QueryParams()));
    }
}