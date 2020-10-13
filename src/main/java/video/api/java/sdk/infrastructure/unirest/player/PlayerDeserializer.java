package video.api.java.sdk.infrastructure.unirest.player;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.player.Player;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerDeserializer implements JsonDeserializer<Player> {
    @Override
    public Player deserialize(JSONObject data) throws JSONException {

        Player player = new Player(
                data.getString("playerId"),
                data.has("assets") ? deserializeAssets(data.getJSONObject("assets")) : null
        );

        if (data.has("shapeMargin")) {
            player.shapeMargin = data.getInt("shapeMargin");
        }

        if (data.has("shapeRadius")) {
            player.shapeRadius = data.getInt("shapeRadius");
        }

        if (data.has("shapeAspect")) {
            player.shapeAspect = data.getString("shapeAspect");
        }

        if (data.has("shapeBackgroundTop")) {
            player.shapeBackgroundTop = parseColor(data.getString("shapeBackgroundTop"));
        }

        if (data.has("shapeBackgroundBottom")) {
            player.shapeBackgroundBottom = parseColor(data.getString("shapeBackgroundBottom"));
        }

        if (data.has("text")) {
            player.text = parseColor(data.getString("text"));
        }

        if (data.has("link")) {
            player.link = parseColor(data.getString("link"));
        }

        if (data.has("linkHover")) {
            player.linkHover = parseColor(data.getString("linkHover"));
        }

        if (data.has("linkActive")) {
            player.linkActive = parseColor(data.getString("linkActive"));
        }

        if (data.has("trackPlayed")) {
            player.trackPlayed = parseColor(data.getString("trackPlayed"));
        }

        if (data.has("trackUnplayed")) {
            player.trackUnplayed = parseColor(data.getString("trackUnplayed"));
        }

        if (data.has("trackBackground")) {
            player.trackBackground = parseColor(data.getString("trackBackground"));
        }

        if (data.has("backgroundTop")) {
            player.backgroundTop = parseColor(data.getString("backgroundTop"));
        }

        if (data.has("backgroundBottom")) {
            player.backgroundBottom = parseColor(data.getString("backgroundBottom"));
        }

        if (data.has("backgroundText")) {
            player.backgroundText = parseColor(data.getString("backgroundText"));
        }

        if (data.has("shapeMargin")) {
            player.shapeMargin = data.getInt("shapeMargin");
        }

        if (data.has("shapeRadius")) {
            player.shapeRadius = data.getInt("shapeMargin");
        }

        if (data.has("enableApi")) {
            player.enableApi = (boolean) data.get("enableApi");
        }

        if (data.has("enableControls")) {
            player.enableControls = (boolean) data.get("enableControls");
        }

        if (data.has("forceAutoplay")) {
            player.forceAutoplay = data.getBoolean("forceAutoplay");
        }

        if (data.has("hideTitle")) {
            player.hideTitle = data.getBoolean("hideTitle");
        }

        if (data.has("forceLoop")) {
            player.forceLoop = data.getBoolean("forceLoop");
        }

        return player;
    }

    public Color parseColor(String input) {
        Pattern c = Pattern.compile("rgba *\\( *([0-9]+), *([0-9]+), *([0-9]+), ([+-]?([0-9]*[.])?[0-9]+) *\\)");
        Matcher m = c.matcher(input);

        if (m.matches()) {
            return new Color(Integer.parseInt(m.group(1)),  // r
                             Integer.parseInt(m.group(2)),  // g
                             Integer.parseInt(m.group(3)),  //b
                             (int) (Float.parseFloat((m.group(4))) * 256)); // alpha
        }

        return null;
    }

    private Player.Assets deserializeAssets(JSONObject data) throws JSONException {
        return new Player.Assets(
                data.getString("link"),
                data.getString("logo")
        );
    }

}
