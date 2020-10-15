package video.api.java.sdk.domain.caption;

import java.util.Locale;

public class CaptionInput {
    public Locale language;
    public boolean isDefault;

    public CaptionInput(Locale language) {
        this.language = language;
    }
}
