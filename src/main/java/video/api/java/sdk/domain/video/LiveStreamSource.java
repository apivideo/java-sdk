package video.api.java.sdk.domain.video;

import java.util.List;

public class LiveStreamSource {
    public final String liveStreamId;
    public final List<Links> links;

    public LiveStreamSource(String liveStreamId, List<Links> links) {
        this.liveStreamId = liveStreamId;
        this.links = links;
    }
}
