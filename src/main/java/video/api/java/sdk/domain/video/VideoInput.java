package video.api.java.sdk.domain.video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoInput {
    public       String              description;
    public       boolean             isPublic   = true;
    public final Map<String, Object> metadata   = new HashMap<>();
    public       boolean             panoramic;
    public       String              playerId;
    public       String              source;
    public final List<String>        tags       = new ArrayList<>();
    public       String              title;
    public       boolean             mp4Support = true;
}
