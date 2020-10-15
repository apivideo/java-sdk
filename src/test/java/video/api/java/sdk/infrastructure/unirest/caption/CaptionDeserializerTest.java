package video.api.java.sdk.infrastructure.unirest.caption;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import video.api.java.sdk.domain.caption.Caption;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CaptionDeserializerTest {
    private CaptionDeserializer captionDeserializer;

    @BeforeEach
    void SetUp() {
        captionDeserializer = new CaptionDeserializer();

    }

    @Test
    void deserializeMax() {

        Caption caption = captionDeserializer.deserialize(createCaptionEn());
        assertEquals("foo", caption.uri);
        assertEquals("vtt", caption.src);
        assertEquals(Locale.ENGLISH, caption.language);
        assertFalse(caption.isDefault);

    }


    @Test
    void deserializeMin() {
        Caption caption = captionDeserializer.deserialize(createCaptionMinimal());

        assertEquals("foo", caption.uri);
        assertEquals(Locale.ENGLISH, caption.language);
        assertEquals("baz", caption.src);
    }

    private JSONObject createCaptionMinimal() {
        return new JSONObject()
                .put("uri", "foo")
                .put("srclang", "en")
                .put("src", "baz");
    }

    @Test
    void deserializeAll() {
        JSONArray captions = new JSONArray()
                .put(createCaptionEn())
                .put(createCaptionFr())
                .put(createCaptionEs());

        List<Caption> deserialized = captionDeserializer.deserialize(captions);

        assertEquals(deserialized.get(0).language, Locale.ENGLISH);
        assertEquals(deserialized.get(1).language, Locale.FRENCH);
        assertEquals(deserialized.get(2).language, new Locale("es"));
    }

    private JSONObject createCaptionEn() {
        return new JSONObject()
                .put("uri", "foo")
                .put("src", "vtt")
                .put("srclang", "en")
                .put("default", false);
    }

    private JSONObject createCaptionEs() {
        return new JSONObject()
                .put("uri", "bar")
                .put("src", "vtt")
                .put("srclang", "es")
                .put("default", false);
    }

    private JSONObject createCaptionFr() {
        return new JSONObject()
                .put("uri", "baz")
                .put("src", "vtt")
                .put("srclang", "fr")
                .put("default", false);
    }

}
