package video.api.java.sdk.infrastructure.unirest.player;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.player.PlayerInput;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonSerializer;


public class PlayerInputSerializer implements JsonSerializer<PlayerInput> {

    public JSONObject serialize(PlayerInput object) throws JSONException {

        JSONObject data = new JSONObject();
        data.put("shapeMargin", object.shapeMargin);
        data.put("shapeRadius", object.shapeRadius);
        data.put("shapeAspect", object.shapeAspect);
        data.put("shapeBackgroundTop", Colors.colorToString(object.shapeBackgroundTop));
        data.put("shapeBackgroundBottom", Colors.colorToString(object.shapeBackgroundBottom));
        data.put("text", Colors.colorToString(object.text));
        data.put("link", Colors.colorToString(object.link));
        data.put("linkHover", Colors.colorToString(object.linkHover));
        data.put("linkActive", Colors.colorToString(object.linkActive));
        data.put("trackPlayed", Colors.colorToString(object.trackPlayed));
        data.put("trackUnplayed", Colors.colorToString(object.trackUnplayed));
        data.put("trackBackground", Colors.colorToString(object.trackBackground));
        data.put("backgroundTop", Colors.colorToString(object.backgroundTop));
        data.put("backgroundBottom", Colors.colorToString(object.backgroundBottom));
        data.put("backgroundText", Colors.colorToString(object.backgroundText));
        data.put("shapeMargin", object.shapeMargin);
        data.put("shapeRadius", object.shapeMargin);
        data.put("enableApi", object.enableApi);
        data.put("enableControls", object.enableControls);
        data.put("forceAutoplay", object.forceAutoplay);
        data.put("hideTitle", object.hideTitle);
        data.put("forceLoop", object.forceLoop);

        return data;
    }

}
