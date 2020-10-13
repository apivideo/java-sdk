package video.api.java.sdk.infrastructure.unirest.analytics;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import video.api.java.sdk.domain.analytics.PlayerSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerSessionDeserializerTest {
    private PlayerSessionDeserializer playerSessionDeserializer;

    @BeforeEach
    void SetUp() {
        playerSessionDeserializer = new PlayerSessionDeserializer();
    }


    @Test
    void deserializeMax() {
        PlayerSession playerSession = playerSessionDeserializer.deserialize(createSerializedPlayerSessionMax());
        assertNull(playerSession.info.metadata.get("age"));
        assertEquals("Foo", playerSession.info.metadata.get("name"));
        assertEquals(1574085943808L, playerSession.info.endedAt.getTimeInMillis());
        //assertEquals("viSuccess", playerSession.);
    }

    @Test
    void deserializeMin() {
        PlayerSession playerSession = playerSessionDeserializer.deserialize(createSerializedPlayerSessionMin());

        assertEquals("ps3uBmLoqYV2O4k9BOeSQ6Sm", playerSession.info.sessionId);

    }


    @Test
    void deserializeCollection() {
        JSONArray collection = new JSONArray();

        collection.put(createSerializedPlayerSessionMin());
        collection.put(createSerializedPlayerSessionMin());
        collection.put(createSerializedPlayerSessionMin());
        collection.put(createSerializedPlayerSessionMin());
        assertEquals(4, playerSessionDeserializer.deserialize(collection).size());


    }

    private JSONObject createSerializedPlayerSessionMin() {
        return new JSONObject()
                .put("session", new JSONObject()
                        .put("sessionId", "ps3uBmLoqYV2O4k9BOeSQ6Sm")
                        .put("loadedAt", "2019-11-18T14:05:43.808000+00:00")
                        .put("endedAt", "2019-11-18T14:06:20.342000+00:00")
                );
    }

    private JSONObject createSerializedPlayerSessionMax() {
        return new JSONObject()
                .put("session", new JSONObject()
                        .put("sessionId", "ps3uBmLoqYV2O4k9BOeSQ6Sm")
                        .put("loadedAt", "2019-11-18T14:05:43.808000+00:00")
                        .put("endedAt", "2019-11-18T14:06:20.342000+00:00")
                        .put("metadata", new JSONArray()
                                .put(new JSONObject().put("key", "age").put("value", (String) null))
                                .put(new JSONObject().put("key", "name").put("value", "Foo"))
                        )
                )
                .put("location", new JSONObject()
                        .put("country", "France")
                        .put("city", "Vaulx-en-Velin")
                )
                .put("referrer", new JSONObject()
                        .put("url", "unknown")
                        .put("medium", "unknown")
                        .put("source", "unknown")
                        .put("searchTerm", "unknown")
                )
                .put("device", new JSONObject()
                        .put("type", "smartphone")
                        .put("vendor", "Huawei")
                        .put("model", "P8 Lite (2017)")
                )
                .put("os", new JSONObject()
                        .put("name", "unknown")
                        .put("shortname", "unknown")
                        .put("version", "unknown")
                )
                .put("client", new JSONObject()
                        .put("type", "browser")
                        .put("name", "Chrome Mobile")
                        .put("version", "62.0")
                )
                ;

    }
}