package video.api.java.sdk.infrastructure.unirest.player;

import kong.unirest.json.JSONObject;

class PlayerInputSerializerTest {
    private PlayerInputSerializer playerInputSerializer = new PlayerInputSerializer();
    private JSONObject            data                  = new JSONObject(
            "  {\n" +
                    "            \"playerId\": \"pl7icQgMd5uE3gajeWOy4oPQ\",\n" +
                    "            \"assets\": {\n" +
                    "                \"logo\": \"https://cdn-staging.api.video/player/pl7icQgMd5uE3gajeWOy4oPQ/logo.png\",\n" +
                    "                \"link\": \"https://api.video\"\n" +
                    "            },\n" +
                    "            \"shapeMargin\": 10,\n" +
                    "            \"shapeRadius\": 10,\n" +
                    "            \"shapeAspect\": \"flat\",\n" +
                    "            \"shapeBackgroundTop\": \"rgba(150, 150, 150, .7)\",\n" +
                    "            \"shapeBackgroundBottom\": \"rgba(150, 150, 150, .8)\",\n" +
                    "            \"text\": \"rgba(155, 155, 155, .95)\",\n" +
                    "            \"link\": \"rgba(155, 0, 0, .95)\",\n" +
                    "            \"linkHover\": \"rgba(155, 155, 155, .25)\",\n" +
                    "            \"linkActive\": \"rgba(25, 0, 0, .75)\",\n" +
                    "            \"trackPlayed\": \"rgba(25, 25, 25, .5)\",\n" +
                    "            \"trackUnplayed\": \"rgba(25, 25, 55, .1)\",\n" +
                    "            \"trackBackground\": \"rgba(10, 10, 10, .10)\",\n" +
                    "            \"backgroundTop\": \"rgba(172, 14, 145, .1)\",\n" +
                    "            \"backgroundBottom\": \"rgba(194, 195, 189, .11)\",\n" +
                    "            \"backgroundText\": \"rgba(25, 25, 25, .95)\",\n" +
                    "            \"enableApi\": true,\n" +
                    "            \"enableControls\": true,\n" +
                    "            \"forceAutoplay\": false,\n" +
                    "            \"hideTitle\": true,\n" +
                    "            \"forceLoop\": false\n" +
                    "        }"
    );
}