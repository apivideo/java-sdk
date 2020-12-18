package video.api.java.sdk.domain.video;

import java.util.Calendar;
import java.util.Map;

public class Video extends VideoInput {
    public static class SourceInfo {
        public final String type;
        public final String uri;
        public final LiveStreamSource liveStreamSource;

        public SourceInfo(String type, String uri, LiveStreamSource liveStreamSource) {
            this.type = type;
            this.uri  = uri;
            this.liveStreamSource = liveStreamSource;
        }
    }

    public final String              videoId;
    public final Calendar            publishedAt;
    public final Calendar            updatedAt;
    public final SourceInfo          sourceInfo;
    public final Map<String, String> assets;

    public Video(String videoId, Calendar publishedAt, Calendar updatedAt, SourceInfo sourceInfo, Map<String, String> assets) {
        this.videoId     = videoId;
        this.publishedAt = publishedAt;
        this.updatedAt   = updatedAt;
        this.sourceInfo  = sourceInfo;
        this.assets      = assets;
    }
}
