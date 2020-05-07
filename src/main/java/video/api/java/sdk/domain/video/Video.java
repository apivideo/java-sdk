package video.api.java.sdk.domain.video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Video {

    public final Map<String, String> metadata = new HashMap<>();
    public final List<String> tags = new ArrayList<>();
    public String description;
    public boolean isPublic = true;
    public boolean panoramic;
    public String playerId;
    public String title;
}
