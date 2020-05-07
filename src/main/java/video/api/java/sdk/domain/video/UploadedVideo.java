package video.api.java.sdk.domain.video;

import java.util.Calendar;
import java.util.Map;

public class UploadedVideo extends Video {
    public static class SourceInfo {
        public final String type;
        public final String uri;

        public SourceInfo(String type, String uri) {
            this.type = type;
            this.uri  = uri;
        }
    }

    public final String              videoId;
    public final Calendar            publishedAt;
    public final Calendar            updatedAt;
    public final SourceInfo          sourceInfo;
    public final Map<String, String> assets;

    public UploadedVideo(String videoId, Calendar publishedAt, Calendar updatedAt, SourceInfo sourceInfo, Map<String, String> assets) {
        this.videoId     = videoId;
        this.publishedAt = publishedAt;
        this.updatedAt   = updatedAt;
        this.sourceInfo  = sourceInfo;
        this.assets      = assets;
    }
}
