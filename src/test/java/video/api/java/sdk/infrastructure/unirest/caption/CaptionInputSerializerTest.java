package video.api.java.sdk.infrastructure.unirest.caption;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import video.api.java.sdk.domain.caption.Caption;
import video.api.java.sdk.domain.caption.CaptionInput;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CaptionInputSerializerTest {
    private CaptionInputSerializer captionDeserializer = new CaptionInputSerializer();

    @Test
    void serialize() {
        CaptionInput caption = new CaptionInput(Locale.FRENCH);
        caption.isDefault = true;

        JSONObject serialized =  captionDeserializer.serialize(caption);

        assertEquals(caption.isDefault, serialized.getBoolean("default"));
    }
}
