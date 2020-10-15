package video.api.java.sdk.domain.analytics;

import java.util.Calendar;
import java.util.Map;

public class Info {
    public final String              sessionId;
    public final Calendar            loadedAt;
    public final Calendar            endedAt;
    public final Map<String, Object> metadata;

    public Info(String sessionId, Calendar loadedAt, Calendar endedAt, Map<String, Object> metadata) {
        this.sessionId = sessionId;
        this.loadedAt  = loadedAt;
        this.endedAt   = endedAt;
        this.metadata  = metadata;
    }
}
