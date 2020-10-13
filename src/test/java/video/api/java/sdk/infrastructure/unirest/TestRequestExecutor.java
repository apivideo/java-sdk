package video.api.java.sdk.infrastructure.unirest;

import kong.unirest.HttpRequest;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.exception.ClientException;
import video.api.java.sdk.domain.exception.ResponseException;
import video.api.java.sdk.domain.exception.ServerException;
import video.api.java.sdk.infrastructure.unirest.RequestExecutor;
import video.api.java.sdk.infrastructure.unirest.request.RequestBuilder;

public class TestRequestExecutor implements RequestExecutor {

    private static final HttpRequest<?>    updateThumbnailVideoSuccess        = Unirest.post("/videos/" + "viSuccess" + "/thumbnail");
    private static final HttpRequest<?>    updateThumbnailWithTimeCodeSuccess = Unirest.patch("/videos/" + "viSuccess");
    private static final HttpRequest<?>    updateAddPlayerSuccess             = Unirest.patch("/videos/" + "viSuccess");
    private static final HttpRequest<?>    uploadVideoSuccess                 = Unirest.post("/videos/viSuccess/source");
    private static final HttpRequest<?>    createVideoSuccess                 = Unirest.post("/videos").body(new JSONObject().put("title", "tiSuccess"));
    private static final HttpRequest<?>    updateVideoSuccess                 = Unirest.patch("/videos/" + "viSuccess");
    private static final HttpRequest<?>    getVideoSuccess                    = Unirest.get("/videos/viSuccess");
    private static final HttpRequest<?>    deleteVideoSuccess                 = Unirest.delete("/videos/viSuccess");
    private static final HttpRequest<?>    getStatusVideoSuccess              = Unirest.get("/videos/viSuccess/status");
    private static final HttpRequest<?>    listVideosSuccess                  = Unirest.get("/videos?currentPage=1&pageSize=25");
    private static final HttpRequest<?>    getVideoServerException            = Unirest.get("/videos/viServerException");
    private static final HttpRequest<?>    getVideoClientException            = Unirest.get("/videos/viClientException");
    private static final HttpRequest<?>    createCaptionSuccess               = Unirest.post("/videos/viSuccess/captions/en");
    private static final HttpRequest<?>    updateCaptionSuccess               = Unirest.patch("/videos/" + "viSuccess/captions/en");
    private static final HttpRequest<?>    getCaptionSuccess                  = Unirest.get("/videos/viSuccess/captions/en");
    private static final HttpRequest<?>    getCaptionsSuccess                 = Unirest.get("/videos/viSuccess/captions");
    private static final HttpRequest<?>    deleteCaptionSuccess               = Unirest.delete("/videos/viSuccess/captions/en");
    private static final HttpRequest<?>    getCaptionFailure                  = Unirest.get("/videos/viFailure/captions/en");
    // Players
    private static final HttpRequest<?>    createPlayerSuccess                = Unirest.post("/players");
    private static final HttpRequest<?>    updateLogoPlayerSuccess            = Unirest.post("/players/" + "plSuccess/logo");
    private static final HttpRequest<?>    deleteLogoPlayerSuccess            = Unirest.delete("/players/" + "plSuccess/logo");
    private static final HttpRequest<?>    updatePlayerSuccess                = Unirest.patch("/players/" + "plSuccess");
    private static final HttpRequest<?>    getPlayerSuccess                   = Unirest.get("/players/plSuccess");
    private static final HttpRequest<?>    deletePlayerSuccess                = Unirest.delete("/players/plSuccess");
    private static final HttpRequest<?>    listPlayersSuccess                 = Unirest.get("/players?currentPage=1&pageSize=25");
    private static final HttpRequest<?>    getPlayerFailure                   = Unirest.get("/players/plFailure");

    private static final HttpRequest<?>    createLiveSuccess                  = Unirest.post("/live-streams").body(new JSONObject().put("title", "tiSuccess"));
    private static final HttpRequest<?>    updateThumbnailLiveSuccess         = Unirest.post("/live-streams/" + "liSuccess" + "/thumbnail");
    private static final HttpRequest<?>    updateAddPlayerLiveSuccess         = Unirest.patch("/live-streams/" + "liSuccess");
    private static final HttpRequest<?>    updateLiveSuccess                  = Unirest.patch("/live-streams/" + "liSuccess");
    private static final HttpRequest<?>    getLiveSuccess                     = Unirest.get("/live-streams/liSuccess");
    private static final HttpRequest<?>    deleteLiveSuccess                  = Unirest.delete("/live-streams/liSuccess");
    private static final HttpRequest<?>    getLivesSuccess                    = Unirest.get("/live-streams?currentPage=1&pageSize=25");
    private static final HttpRequest<?>    getLiveServerException             = Unirest.get("/live-streams/liServerException");
    private static final HttpRequest<?>    getLiveClientException             = Unirest.get("/live-streams/liClientException");
    private static final HttpRequest<?>    getAnalyticVideosSuccess           = Unirest.get("/analytics/videos/viSuccess?period=2019");
    private static final HttpRequest<?>    getAnalyticsVideosSuccess          = Unirest.get("/analytics/videos?currentPage=1&pageSize=25");
    private static final HttpRequest<?>    getAnalyticLivesSuccess            = Unirest.get("/analytics/live-streams/liSuccess?period=2019");
    private static final HttpRequest<?>    getAnalyticsLivesSuccess           = Unirest.get("/analytics/live-streams?currentPage=1&pageSize=25");
    private static final HttpRequest<?>    getAnalyticsEventsSuccess          = Unirest.get("/analytics/sessions/sessionId/events?currentPage=1&pageSize=25");
    public               ResponseException exception;

    @Override
    public JsonNode executeJson(RequestBuilder request) throws ResponseException {
        if (exception != null) {
            throw exception;
        }

        if (equals(request, createVideoSuccess)) {
            return videoResponseSuccess();

        } else if (equals(request, getVideoSuccess)) {
            return videoResponseSuccess();
        } else if (equals(request, listVideosSuccess)) {
            return videosResponseSuccess();
        } else if (equals(request, getStatusVideoSuccess)) {
            return videoStatusResponseSuccess();
        } else if (equals(request, deleteVideoSuccess)) {
            return videoResponseSuccess();
        } else if (equals(request, updateThumbnailVideoSuccess)) {
            return videoResponseSuccess();
        } else if (equals(request, updateThumbnailWithTimeCodeSuccess)) {
            return videoResponseSuccess();
        } else if (equals(request, updateAddPlayerSuccess)) {
            return videoResponseSuccess();
        } else if (equals(request, updateVideoSuccess)) {
            return videoResponseSuccess();
        } else if (equals(request, uploadVideoSuccess)) {
            return videoResponseSuccess();
        } else if (equals(request, getVideoServerException)) {
            throw  videoServerException();
        } else if (equals(request, getVideoClientException)) {
            throw  videoClientException();
        } else if (equals(request, createCaptionSuccess)) {
            return captionResponseSuccess();
        } else if (equals(request, getCaptionSuccess)) {
            return captionResponseSuccess();
        } else if (equals(request, getCaptionsSuccess)) {
            return captionsResponseSuccess();
        } else if (equals(request, updateCaptionSuccess)) {
            return captionResponseSuccess();
        } else if (equals(request, deleteCaptionSuccess)) {
            return captionResponseSuccess();
        } else if (equals(request, getCaptionFailure)) {
            return responseFailure();


        } else if (equals(request, createPlayerSuccess)) {
            return playerResponseSuccess();
        } else if (equals(request, updatePlayerSuccess)) {
            return playerResponseSuccess();
        } else if (equals(request, updateLogoPlayerSuccess)) {
            return playerResponseSuccess();
        } else if (equals(request, deleteLogoPlayerSuccess)) {
            return playerResponseSuccess();
        } else if (equals(request, getPlayerSuccess)) {
            return playerResponseSuccess();
        } else if (equals(request, deletePlayerSuccess)) {
            return playerResponseSuccess();
        } else if (equals(request, listPlayersSuccess)) {
            return playersResponseSuccess();
        } else if (equals(request, getPlayerFailure)) {
            return responseFailure();


        } else if (equals(request, createLiveSuccess)) {
            return liveResponseSuccess();
        } else if (equals(request, updateThumbnailLiveSuccess)) {
            return liveResponseSuccess();
        } else if (equals(request, updateAddPlayerLiveSuccess)) {
            return liveResponseSuccess();
        } else if (equals(request, updateLiveSuccess)) {
            return liveResponseSuccess();
        } else if (equals(request, getLiveSuccess)) {
            return liveResponseSuccess();
        } else if (equals(request, deleteLiveSuccess)) {
            return liveResponseSuccess();
        } else if (equals(request, getLivesSuccess)) {
            return livesResponseSuccess();
        } else if (equals(request, getLiveServerException)) {
            throw videoServerException();
        } else if (equals(request, getLiveClientException)) {
            throw videoClientException();
        } else if (equals(request, getAnalyticVideosSuccess)) {
            return analyticsVideoResponseSuccess();
        } else if (equals(request, getAnalyticsVideosSuccess)) {
            return analyticsVideosResponseSuccess();

        } else if (equals(request, getAnalyticLivesSuccess)) {
            return analyticsLiveResponseSuccess();
        } else if (equals(request, getAnalyticsLivesSuccess)) {
            return analyticsLivesResponseSuccess();

        } else if (equals(request, getAnalyticsEventsSuccess)) {
            return analyticEventsResponseSuccess();
        }

        throw new RuntimeException("Method not handled");
    }

    private JsonNode videoResponseSuccess() {
        return new JsonNode(
                " {\n" +
                        "            \"videoId\": \"viSuccess\",\n" +
                        "            \"title\": \"toto\",\n" +
                        "            \"description\": \"Test create description\",\n" +
                        "            \"public\": true,\n" +
                        "            \"panoramic\": false,\n" +
                        "            \"publishedAt\": \"2019-08-28T16:25:51+02:00\",\n" +
                        "            \"updatedAt\": \"2019-08-28T16:25:51+02:00\",\n" +
                        "            \"tags\": [],\n" +
                        "            \"metadata\": [],\n" +
                        "            \"source\": {\n" +
                        "                \"type\": \"upload\",\n" +
                        "                \"uri\": \"/videos/vi5iemB77Z5VmZSoNz94dr2O/source\"\n" +
                        "            },\n" +
                        "            \"assets\": {\n" +
                        "                \"iframe\": \"<iframe src=\\\"https://embed-staging.api.video/vod/vi5iemB77Z5VmZSoNz94dr2O\\\" width=\\\"100%\\\" height=\\\"100%\\\" frameborder=\\\"0\\\" scrolling=\\\"no\\\" allowfullscreen=\\\"\\\"></iframe>\",\n" +
                        "                \"player\": \"https://embed-staging.api.video/vod/vi5iemB77Z5VmZSoNz94dr2O\",\n" +
                        "                \"hls\": \"https://cdn-staging.api.video/vod/vi5iemB77Z5VmZSoNz94dr2O/hls/manifest.m3u8\",\n" +
                        "                \"thumbnail\": \"https://cdn-staging.api.video/vod/vi5iemB77Z5VmZSoNz94dr2O/thumbnail.jpg\"\n" +
                        "            }\n" +
                        "        }"
        );
    }

    private JsonNode captionResponseSuccess() {
        return new JsonNode(
                "{\n" +
                        "    \"uri\": \"/videos/vi2J7lHcIojfOhDDVv9yR9JN/captions/en\",\n" +
                        "    \"src\": \"https://cdn-staging.api.video/vod/vi2J7lHcIojfOhDDVv9yR9JN/token/f5ed3b3b-b40a-42b7-8ecd-959907fff188/captions/en.vtt\",\n" +
                        "    \"srclang\": \"en\",\n" +
                        "    \"default\": false\n" +
                        "}"
        );

    }

    private JsonNode analyticsVideosResponseSuccess() {
        return new JsonNode(
                "{\n" +
                        "    \"period\": \"2019-09-09\",\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"video\": {\n" +
                        "                \"videoId\": \"viSuccess\",\n" +
                        "                \"title\": \"updated video\",\n" +
                        "                \"metadata\": [\n" +
                        "                    {\n" +
                        "                        \"key\": \"author\",\n" +
                        "                        \"value\": \"kenny\"\n" +
                        "                    }\n" +
                        "                ],\n" +
                        "                \"tags\": [\n" +
                        "                    \"tata\",\n" +
                        "                    \"titi\"\n" +
                        "                ]\n" +
                        "            },\n" +
                        "            \"period\": \"2019-09-09\",\n" +
                        "            \"data\": [\n" +
                        "                {\n" +
                        "                    \"session\": {\n" +
                        "                        \"sessionId\": \"ps3uBmLoqYV2O4k9BOeSQ6Sm\",\n" +
                        "                        \"loadedAt\": \"2019-09-09 06:41:06.683+00\",\n" +
                        "                        \"endedAt\": \"2019-09-09 06:41:27.606+00\",\n" +
                        "                        \"metadatas\": [\n" +
                        "                            {\n" +
                        "                                \"key\": \"age\",\n" +
                        "                                \"value\": null\n" +
                        "                            }\n" +
                        "                        ]\n" +
                        "                    },\n" +
                        "                    \"location\": {\n" +
                        "                        \"country\": \"France\",\n" +
                        "                        \"city\": \"Vaulx-en-Velin\"\n" +
                        "                    },\n" +
                        "                    \"referrer\": {\n" +
                        "                        \"url\": \"unknown\",\n" +
                        "                        \"medium\": \"unknown\",\n" +
                        "                        \"source\": \"unknown\",\n" +
                        "                        \"searchTerm\": \"unknown\"\n" +
                        "                    },\n" +
                        "                    \"device\": {\n" +
                        "                        \"type\": \"smartphone\",\n" +
                        "                        \"vendor\": \"Huawei\",\n" +
                        "                        \"model\": \"P8 Lite (2017)\"\n" +
                        "                    },\n" +
                        "                    \"os\": {\n" +
                        "                        \"name\": \"unknown\",\n" +
                        "                        \"shortname\": \"unknown\",\n" +
                        "                        \"version\": \"unknown\"\n" +
                        "                    },\n" +
                        "                    \"client\": {\n" +
                        "                        \"type\": \"browser\",\n" +
                        "                        \"name\": \"Chrome Mobile\",\n" +
                        "                        \"version\": \"62.0\"\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"pagination\": {\n" +
                        "        \"currentPage\": 1,\n" +
                        "        \"pageSize\": 25,\n" +
                        "        \"pagesTotal\": 1,\n" +
                        "        \"itemsTotal\": 1,\n" +
                        "        \"currentPageItems\": 1,\n" +
                        "        \"links\": [\n" +
                        "            {\n" +
                        "                \"rel\": \"self\",\n" +
                        "                \"uri\": \"/analytics/videos?currentPage=1\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"first\",\n" +
                        "                \"uri\": \"/analytics/videos?currentPage=1\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"last\",\n" +
                        "                \"uri\": \"/analytics/videos?currentPage=1\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}"
        );

    }

    private JsonNode analyticsVideoResponseSuccess() {
        return new JsonNode(
                "{\n" +
                        "    \"video\": {\n" +
                        "        \"videoId\": \"viSuccess\",\n" +
                        "        \"title\": \"updated video\",\n" +
                        "        \"metadata\": [\n" +
                        "            {\n" +
                        "                \"key\": \"author\",\n" +
                        "                \"value\": \"kenny\"\n" +
                        "            }\n" +
                        "        ],\n" +
                        "        \"tags\": [\n" +
                        "            \"tata\",\n" +
                        "            \"titi\"\n" +
                        "        ]\n" +
                        "    },\n" +
                        "    \"period\": \"2019-09-09\",\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"session\": {\n" +
                        "                \"sessionId\": \"ps3uBmLoqYV2O4k9BOeSQ6Sm\",\n" +
                        "                \"loadedAt\": \"2019-09-09 06:41:06.683+00\",\n" +
                        "                \"endedAt\": \"2019-09-09 06:41:27.606+00\",\n" +
                        "                \"metadatas\": [\n" +
                        "                    {\n" +
                        "                        \"key\": \"age\",\n" +
                        "                        \"value\": null\n" +
                        "                    }\n" +
                        "                ]\n" +
                        "            },\n" +
                        "            \"location\": {\n" +
                        "                \"country\": \"France\",\n" +
                        "                \"city\": \"Vaulx-en-Velin\"\n" +
                        "            },\n" +
                        "            \"referrer\": {\n" +
                        "                \"url\": \"unknown\",\n" +
                        "                \"medium\": \"unknown\",\n" +
                        "                \"source\": \"unknown\",\n" +
                        "                \"searchTerm\": \"unknown\"\n" +
                        "            },\n" +
                        "            \"device\": {\n" +
                        "                \"type\": \"smartphone\",\n" +
                        "                \"vendor\": \"Huawei\",\n" +
                        "                \"model\": \"P8 Lite (2017)\"\n" +
                        "            },\n" +
                        "            \"os\": {\n" +
                        "                \"name\": \"unknown\",\n" +
                        "                \"shortname\": \"unknown\",\n" +
                        "                \"version\": \"unknown\"\n" +
                        "            },\n" +
                        "            \"client\": {\n" +
                        "                \"type\": \"browser\",\n" +
                        "                \"name\": \"Chrome Mobile\",\n" +
                        "                \"version\": \"62.0\"\n" +
                        "            }\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}"
        );

    }

    private JsonNode analyticsLivesResponseSuccess() {
        return new JsonNode(
                "{\n" +
                        "    \"period\": \"2019-09-09\",\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"video\": {\n" +
                        "                \"videoId\": \"viSuccess\",\n" +
                        "                \"title\": \"updated video\",\n" +
                        "                \"metadata\": [\n" +
                        "                    {\n" +
                        "                        \"key\": \"author\",\n" +
                        "                        \"value\": \"kenny\"\n" +
                        "                    }\n" +
                        "                ],\n" +
                        "                \"tags\": [\n" +
                        "                    \"tata\",\n" +
                        "                    \"titi\"\n" +
                        "                ]\n" +
                        "            },\n" +
                        "            \"period\": \"2019-09-09\",\n" +
                        "            \"data\": [\n" +
                        "                {\n" +
                        "                    \"session\": {\n" +
                        "                        \"sessionId\": \"ps3uBmLoqYV2O4k9BOeSQ6Sm\",\n" +
                        "                        \"loadedAt\": \"2019-09-09 06:41:06.683+00\",\n" +
                        "                        \"endedAt\": \"2019-09-09 06:41:27.606+00\",\n" +
                        "                        \"metadatas\": [\n" +
                        "                            {\n" +
                        "                                \"key\": \"age\",\n" +
                        "                                \"value\": null\n" +
                        "                            }\n" +
                        "                        ]\n" +
                        "                    },\n" +
                        "                    \"location\": {\n" +
                        "                        \"country\": \"France\",\n" +
                        "                        \"city\": \"Vaulx-en-Velin\"\n" +
                        "                    },\n" +
                        "                    \"referrer\": {\n" +
                        "                        \"url\": \"unknown\",\n" +
                        "                        \"medium\": \"unknown\",\n" +
                        "                        \"source\": \"unknown\",\n" +
                        "                        \"searchTerm\": \"unknown\"\n" +
                        "                    },\n" +
                        "                    \"device\": {\n" +
                        "                        \"type\": \"smartphone\",\n" +
                        "                        \"vendor\": \"Huawei\",\n" +
                        "                        \"model\": \"P8 Lite (2017)\"\n" +
                        "                    },\n" +
                        "                    \"os\": {\n" +
                        "                        \"name\": \"unknown\",\n" +
                        "                        \"shortname\": \"unknown\",\n" +
                        "                        \"version\": \"unknown\"\n" +
                        "                    },\n" +
                        "                    \"client\": {\n" +
                        "                        \"type\": \"browser\",\n" +
                        "                        \"name\": \"Chrome Mobile\",\n" +
                        "                        \"version\": \"62.0\"\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"pagination\": {\n" +
                        "        \"currentPage\": 1,\n" +
                        "        \"pageSize\": 25,\n" +
                        "        \"pagesTotal\": 1,\n" +
                        "        \"itemsTotal\": 1,\n" +
                        "        \"currentPageItems\": 1,\n" +
                        "        \"links\": [\n" +
                        "            {\n" +
                        "                \"rel\": \"self\",\n" +
                        "                \"uri\": \"/analytics/videos?currentPage=1\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"first\",\n" +
                        "                \"uri\": \"/analytics/videos?currentPage=1\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"last\",\n" +
                        "                \"uri\": \"/analytics/videos?currentPage=1\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}"
                        /*  "{\n" +
                                "    \"period\": \"2019-09-09\",\n" +
                                "    \"data\": [\n" +
                                "        {\n" +
                                "            \"live\": {\n" +
                                "                \"liveStreamId\": \"liSuccess\",\n" +
                                "                \"name\": \"updated video\",\n" +
                                "            \"period\": \"2019-09-09\",\n" +
                                "            \"data\": [\n" +
                                "                {\n" +
                                "                    \"session\": {\n" +
                                "                        \"sessionId\": \"ps3uBmLoqYV2O4k9BOeSQ6Sm\",\n" +
                                "                        \"loadedAt\": \"2019-09-09 06:41:06.683+00\",\n" +
                                "                        \"endedAt\": \"2019-09-09 06:41:27.606+00\",\n" +
                                "                    },\n" +
                                "                    \"location\": {\n" +
                                "                        \"country\": \"France\",\n" +
                                "                        \"city\": \"Vaulx-en-Velin\"\n" +
                                "                    },\n" +
                                "                    \"referrer\": {\n" +
                                "                        \"url\": \"unknown\",\n" +
                                "                        \"medium\": \"unknown\",\n" +
                                "                        \"source\": \"unknown\",\n" +
                                "                        \"searchTerm\": \"unknown\"\n" +
                                "                    },\n" +
                                "                    \"device\": {\n" +
                                "                        \"type\": \"smartphone\",\n" +
                                "                        \"vendor\": \"Huawei\",\n" +
                                "                        \"model\": \"P8 Lite (2017)\"\n" +
                                "                    },\n" +
                                "                    \"os\": {\n" +
                                "                        \"name\": \"unknown\",\n" +
                                "                        \"shortname\": \"unknown\",\n" +
                                "                        \"version\": \"unknown\"\n" +
                                "                    },\n" +
                                "                    \"client\": {\n" +
                                "                        \"type\": \"browser\",\n" +
                                "                        \"name\": \"Chrome Mobile\",\n" +
                                "                        \"version\": \"62.0\"\n" +
                                "                    }\n" +
                                "                }\n" +
                                "            ]\n" +
                                "        }\n" +


                                "],\n" +
                                "    \"pagination\": {\n" +
                                "        \"currentPage\": 1,\n" +
                                "        \"pageSize\": 25,\n" +
                                "        \"pagesTotal\": 0,\n" +
                                "        \"itemsTotal\": 0,\n" +
                                "        \"currentPageItems\": 0,\n" +
                                "        \"links\": [\n" +
                                "            {\n" +
                                "                \"rel\": \"first\",\n" +
                                "                \"uri\": \"/analytics/live-streams?currentPage=1\"\n" +
                                "            }\n" +
                                "        ]\n" +
                                "    }\n" +
                                "}"
               */);

    }

    private JsonNode analyticsLiveResponseSuccess() {
        return new JsonNode(
                "        {\n" +
                        "            \"live\": {\n" +
                        "                \"liveStreamId\": \"liSuccess\",\n" +
                        "                \"name\": \"updated video\",\n" +
                        "    },\n" +
                        "            \"period\": \"2019-09-09\",\n" +
                        "            \"data\": [\n" +
                        "                {\n" +
                        "                    \"session\": {\n" +
                        "                        \"sessionId\": \"ps3uBmLoqYV2O4k9BOeSQ6Sm\",\n" +
                        "                        \"loadedAt\": \"2019-09-09 06:41:06.683+00\",\n" +
                        "                        \"endedAt\": \"2019-09-09 06:41:27.606+00\",\n" +
                        "                    },\n" +
                        "                    \"location\": {\n" +
                        "                        \"country\": \"France\",\n" +
                        "                        \"city\": \"Vaulx-en-Velin\"\n" +
                        "                    },\n" +
                        "                    \"referrer\": {\n" +
                        "                        \"url\": \"unknown\",\n" +
                        "                        \"medium\": \"unknown\",\n" +
                        "                        \"source\": \"unknown\",\n" +
                        "                        \"searchTerm\": \"unknown\"\n" +
                        "                    },\n" +
                        "                    \"device\": {\n" +
                        "                        \"type\": \"smartphone\",\n" +
                        "                        \"vendor\": \"Huawei\",\n" +
                        "                        \"model\": \"P8 Lite (2017)\"\n" +
                        "                    },\n" +
                        "                    \"os\": {\n" +
                        "                        \"name\": \"unknown\",\n" +
                        "                        \"shortname\": \"unknown\",\n" +
                        "                        \"version\": \"unknown\"\n" +
                        "                    },\n" +
                        "                    \"client\": {\n" +
                        "                        \"type\": \"browser\",\n" +
                        "                        \"name\": \"Chrome Mobile\",\n" +
                        "                        \"version\": \"62.0\"\n" +
                        "                    }\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }\n"

        );
    }

    private JsonNode analyticEventsResponseSuccess() {
        return new JsonNode(
                "{\n" +
                        "    \"session\": {\n" +
                        "        \"sessionId\": \"sessionId\",\n" +
                        "        \"loadedAt\": \"2019-09-09T08:03:56.596000+00:00\",\n" +
                        "        \"endedAt\": \"2019-09-09T08:03:59.831000+00:00\"\n" +
                        "    },\n" +
                        "    \"resource\": {\n" +
                        "        \"type\": \"vod\",\n" +
                        "        \"id\": \"vi65MFpUcTqfnkEHW3oxve4m\"\n" +
                        "    },\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"type\": \"player_session_vod.loaded\",\n" +
                        "            \"emittedAt\": \"2019-09-09 08:03:56.596+00\",\n" +
                        "            \"at\": 0\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"pagination\": {\n" +
                        "        \"currentPage\": 1,\n" +
                        "        \"pageSize\": 1,\n" +
                        "        \"pagesTotal\": 6,\n" +
                        "        \"itemsTotal\": 6,\n" +
                        "        \"currentPageItems\": 1,\n" +
                        "        \"links\": [\n" +
                        "            {\n" +
                        "                \"rel\": \"self\",\n" +
                        "                \"uri\": \"/analytics/sessions/ps2TqogQEz2JsnJGV2nPulAp/events?pageSize=1&currentPage=1\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"first\",\n" +
                        "                \"uri\": \"/analytics/sessions/ps2TqogQEz2JsnJGV2nPulAp/events?pageSize=1&currentPage=1\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"next\",\n" +
                        "                \"uri\": \"/analytics/sessions/ps2TqogQEz2JsnJGV2nPulAp/events?pageSize=1&currentPage=2\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"last\",\n" +
                        "                \"uri\": \"/analytics/sessions/ps2TqogQEz2JsnJGV2nPulAp/events?pageSize=1&currentPage=6\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}"
        );

    }

    public JsonNode responseFailure() {
        return new JsonNode(
                "{\n" +
                        "    \"status\": 404,\n" +
                        "    \"type\": \"https://docs.api.video/5.1/problems/resource.not_found\",\n" +
                        "    \"title\": \"The requested resource could not be found.\",\n" +
                        "    \"name\": \"\"\n" +
                        "}"
        );

    }

    private JsonNode captionsResponseSuccess() {
        return new JsonNode(
                "[\n" +
                        "    {\n" +
                        "        \"uri\": \"/videos/vi2J7lHcIojfOhDDVv9yR9JN/captions/en\",\n" +
                        "        \"src\": \"https://cdn-staging.api.video/vod/vi2J7lHcIojfOhDDVv9yR9JN/token/01654210-40a4-44ac-a680-4cdba33a96d3/captions/en.vtt\",\n" +
                        "        \"srclang\": \"en\",\n" +
                        "        \"default\": false\n" +
                        "    },\n" +
                        " {\n" +
                        "        \"uri\": \"/videos/vi2J7lHcIojfOhDDVv9yR9JN/captions/en\",\n" +
                        "        \"src\": \"https://cdn-staging.api.video/vod/vi2J7lHcIojfOhDDVv9yR9JN/token/01654210-40a4-44ac-a680-4cdba33a96d3/captions/en.vtt\",\n" +
                        "        \"srclang\": \"en\",\n" +
                        "        \"default\": false\n" +
                        "    }" +
                        "]"
        );

    }

    private JsonNode playerResponseSuccess() {
        return new JsonNode(
                "  {\n" +
                        "            \"playerId\": \"plSuccess\",\n" +
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
                        "            \"language\": \"en\",\n" +
                        "            \"enableApi\": true,\n" +
                        "            \"enableControls\": true,\n" +
                        "            \"forceAutoplay\": false,\n" +
                        "            \"hideTitle\": true,\n" +
                        "            \"forceLoop\": false\n" +
                        "        }"
        );
    }

    private JsonNode playersResponseSuccess() {
        return new JsonNode(
                "{\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
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
                        "            \"linkHover\": \"rgba(155, 155, 155, .75)\",\n" +
                        "            \"linkActive\": \"rgba(25, 0, 0, .75)\",\n" +
                        "            \"trackPlayed\": \"rgba(25, 25, 25, .95)\",\n" +
                        "            \"trackUnplayed\": \"rgba(25, 25, 55, .1)\",\n" +
                        "            \"trackBackground\": \"rgba(10, 10, 10, 0.1)\",\n" +
                        "            \"backgroundTop\": \"rgba(172, 14, 145, 0.1)\",\n" +
                        "            \"backgroundBottom\": \"rgba(194, 195, 189, 0.11)\",\n" +
                        "            \"backgroundText\": \"rgba(25, 25, 25, .95)\",\n" +
                        "            \"language\": \"en\",\n" +
                        "            \"enableApi\": true,\n" +
                        "            \"enableControls\": true,\n" +
                        "            \"forceAutoplay\": false,\n" +
                        "            \"hideTitle\": true,\n" +
                        "            \"forceLoop\": false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"playerId\": \"pl2XKyREsidQdWaq2zbbofvZ\",\n" +
                        "            \"assets\": {\n" +
                        "                \"logo\": \"https://cdn-staging.api.video/player/pl2XKyREsidQdWaq2zbbofvZ/logo.png\",\n" +
                        "                \"link\": \"https://api.video\"\n" +
                        "            },\n" +
                        "            \"shapeMargin\": 10,\n" +
                        "            \"shapeRadius\": 10,\n" +
                        "            \"shapeAspect\": \"flat\",\n" +
                        "            \"shapeBackgroundTop\": \"rgba(150, 150, 150, .7)\",\n" +
                        "            \"shapeBackgroundBottom\": \"rgba(150, 150, 150, .8)\",\n" +
                        "            \"text\": \"rgba(155, 155, 155, .95)\",\n" +
                        "            \"link\": \"rgba(155, 0, 0, .95)\",\n" +
                        "            \"linkHover\": \"rgba(155, 155, 155, .75)\",\n" +
                        "            \"linkActive\": \"rgba(25, 0, 0, .75)\",\n" +
                        "            \"trackPlayed\": \"rgba(25, 25, 25, .95)\",\n" +
                        "            \"trackUnplayed\": \"rgba(25, 25, 55, .1)\",\n" +
                        "            \"trackBackground\": \"rgba(10, 10, 10, 0.10)\",\n" +
                        "            \"backgroundTop\": \"rgba(172, 14, 145, 0.1)\",\n" +
                        "            \"backgroundBottom\": \"rgba(194, 195, 189, 0.11)\",\n" +
                        "            \"backgroundText\": \"rgba(25, 25, 25, .95)\",\n" +
                        "            \"language\": \"en\",\n" +
                        "            \"enableApi\": true,\n" +
                        "            \"enableControls\": true,\n" +
                        "            \"forceAutoplay\": false,\n" +
                        "            \"hideTitle\": true,\n" +
                        "            \"forceLoop\": false\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"pagination\": {\n" +
                        "        \"currentPage\": 1,\n" +
                        "        \"pageSize\": 2,\n" +
                        "        \"pagesTotal\": 45,\n" +
                        "        \"itemsTotal\": 90,\n" +
                        "        \"currentPageItems\": 2,\n" +
                        "        \"links\": [\n" +
                        "            {\n" +
                        "                \"rel\": \"self\",\n" +
                        "                \"uri\": \"/players?pageSize=2\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"first\",\n" +
                        "                \"uri\": \"/players?pageSize=2\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"next\",\n" +
                        "                \"uri\": \"/players?currentPage=2&pageSize=2\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"last\",\n" +
                        "                \"uri\": \"/players?currentPage=45&pageSize=2\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}"
        );


    }

    private JsonNode liveResponseSuccess() {
        return new JsonNode(
                " {\n" +
                        "            \"liveStreamId\": \"li4CASHslkaCFQNTkr2HnE9p\",\n" +
                        "            \"streamKey\": \"fa9f364c-ce48-4077-9692-c7f7c2af783a\",\n" +
                        "            \"name\": \"test live\",\n" +
                        "            \"record\": false,\n" +
                        "            \"broadcasting\": false,\n" +
                        "            \"assets\": {\n" +
                        "                \"iframe\": \"<iframe src=\\\"https://embed-staging.api.video/live/li4CASHslkaCFQNTkr2HnE9p\\\" width=\\\"100%\\\" height=\\\"100%\\\" frameborder=\\\"0\\\" scrolling=\\\"no\\\" allowfullscreen=\\\"\\\"></iframe>\",\n" +
                        "                \"player\": \"https://embed-staging.api.video/live/li4CASHslkaCFQNTkr2HnE9p\",\n" +
                        "                \"hls\": \"https://live.api.video/li4CASHslkaCFQNTkr2HnE9p.m3u8\",\n" +
                        "                \"thumbnail\": \"https://cdn-staging.api.video/live/li4CASHslkaCFQNTkr2HnE9p/thumbnail.jpg\"\n" +
                        "            }\n" +
                        "        }"
        );
    }

    private JsonNode livesResponseSuccess() {
        return new JsonNode(
                "{\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"liveStreamId\": \"li4cqehuvXntnwEfT7QyDDTu\",\n" +
                        "            \"streamKey\": \"15179f45-64f5-4e48-948f-a8fd38fc7b9f\",\n" +
                        "            \"name\": \"test live\",\n" +
                        "            \"record\": false,\n" +
                        "            \"broadcasting\": false,\n" +
                        "            \"assets\": {\n" +
                        "                \"iframe\": \"<iframe src=\\\"https://embed-staging.api.video/live/li4cqehuvXntnwEfT7QyDDTu\\\" width=\\\"100%\\\" height=\\\"100%\\\" frameborder=\\\"0\\\" scrolling=\\\"no\\\" allowfullscreen=\\\"\\\"></iframe>\",\n" +
                        "                \"player\": \"https://embed-staging.api.video/live/li4cqehuvXntnwEfT7QyDDTu\",\n" +
                        "                \"hls\": \"https://live.api.video/li4cqehuvXntnwEfT7QyDDTu.m3u8\",\n" +
                        "                \"thumbnail\": \"https://cdn-staging.api.video/live/li4cqehuvXntnwEfT7QyDDTu/thumbnail.jpg\"\n" +
                        "            }\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"pagination\": {\n" +
                        "        \"currentPage\": 2,\n" +
                        "        \"pageSize\": 3,\n" +
                        "        \"pagesTotal\": 31,\n" +
                        "        \"itemsTotal\": 91,\n" +
                        "        \"currentPageItems\": 3,\n" +
                        "        \"links\": [\n" +
                        "            {\n" +
                        "                \"rel\": \"self\",\n" +
                        "                \"uri\": \"/live-streams?currentPage=2&pageSize=3\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"first\",\n" +
                        "                \"uri\": \"/live-streams?pageSize=3\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"previous\",\n" +
                        "                \"uri\": \"/live-streams?pageSize=3\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"next\",\n" +
                        "                \"uri\": \"/live-streams?currentPage=3&pageSize=3\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"last\",\n" +
                        "                \"uri\": \"/live-streams?currentPage=31&pageSize=3\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}"
        );

    }

    private JsonNode videoStatusResponseSuccess() {
        return new JsonNode(
                "{\n" +
                        "    \"ingest\": {\n" +
                        "        \"status\": \"uploaded\",\n" +
                        "        \"filesize\": 1607910,\n" +
                        "        \"receivedBytes\": []\n" +
                        "    },\n" +
                        "    \"encoding\": {\n" +
                        "        \"playable\": true,\n" +
                        "        \"qualities\": [\n" +
                        "            {\n" +
                        "                \"quality\": \"480p\",\n" +
                        "                \"status\": \"encoded\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"quality\": \"720p\",\n" +
                        "                \"status\": \"waiting\"\n" +
                        "            }\n" +
                        "        ],\n" +
                        "        \"metadata\": {\n" +
                        "            \"width\": 1280,\n" +
                        "            \"height\": 720,\n" +
                        "            \"bitrate\": 78.9189,\n" +
                        "            \"duration\": 61,\n" +
                        "            \"framerate\": 30,\n" +
                        "            \"samplerate\": 44100,\n" +
                        "            \"video_codec\": \"h264\",\n" +
                        "            \"audio_codec\": \"aac\",\n" +
                        "            \"aspect_ratio\": \"16:9\"\n" +
                        "        }\n" +
                        "    }\n" +
                        "}"
        );

    }

    private JsonNode videosResponseSuccess() {
        return new JsonNode(
                "{\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"videoId\": \"viSuccess\",\n" +
                        "            \"title\": \"Test Title\",\n" +
                        "            \"description\": \"Test create description\",\n" +
                        "            \"public\": true,\n" +
                        "            \"panoramic\": false,\n" +
                        "            \"publishedAt\": \"2019-08-28T16:25:51+02:00\",\n" +
                        "            \"updatedAt\": \"2019-08-28T16:25:51+02:00\",\n" +
                        "            \"tags\": [],\n" +
                        "            \"metadata\": [],\n" +
                        "            \"source\": {\n" +
                        "                \"type\": \"upload\",\n" +
                        "                \"uri\": \"/videos/vi5iemB77Z5VmZSoNz94dr2O/source\"\n" +
                        "            },\n" +
                        "            \"assets\": {\n" +
                        "                \"iframe\": \"<iframe src=\\\"https://embed-staging.api.video/vod/vi5iemB77Z5VmZSoNz94dr2O\\\" width=\\\"100%\\\" height=\\\"100%\\\" frameborder=\\\"0\\\" scrolling=\\\"no\\\" allowfullscreen=\\\"\\\"></iframe>\",\n" +
                        "                \"player\": \"https://embed-staging.api.video/vod/vi5iemB77Z5VmZSoNz94dr2O\",\n" +
                        "                \"hls\": \"https://cdn-staging.api.video/vod/vi5iemB77Z5VmZSoNz94dr2O/hls/manifest.m3u8\",\n" +
                        "                \"thumbnail\": \"https://cdn-staging.api.video/vod/vi5iemB77Z5VmZSoNz94dr2O/thumbnail.jpg\"\n" +
                        "            }\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"videoId\": \"vi2SRWJ6ipruaD9K73CJbP0V\",\n" +
                        "            \"title\": \"Test Title\",\n" +
                        "            \"description\": \"Test create description\",\n" +
                        "            \"public\": true,\n" +
                        "            \"panoramic\": false,\n" +
                        "            \"publishedAt\": \"2019-08-29T08:54:13+02:00\",\n" +
                        "            \"updatedAt\": \"2019-08-29T08:54:13+02:00\",\n" +
                        "            \"tags\": [],\n" +
                        "            \"metadata\": [],\n" +
                        "            \"source\": {\n" +
                        "                \"type\": \"upload\",\n" +
                        "                \"uri\": \"/videos/vi2SRWJ6ipruaD9K73CJbP0V/source\"\n" +
                        "            },\n" +
                        "            \"assets\": {\n" +
                        "                \"iframe\": \"<iframe src=\\\"https://embed-staging.api.video/vod/vi2SRWJ6ipruaD9K73CJbP0V\\\" width=\\\"100%\\\" height=\\\"100%\\\" frameborder=\\\"0\\\" scrolling=\\\"no\\\" allowfullscreen=\\\"\\\"></iframe>\",\n" +
                        "                \"player\": \"https://embed-staging.api.video/vod/vi2SRWJ6ipruaD9K73CJbP0V\",\n" +
                        "                \"hls\": \"https://cdn-staging.api.video/vod/vi2SRWJ6ipruaD9K73CJbP0V/hls/manifest.m3u8\",\n" +
                        "                \"thumbnail\": \"https://cdn-staging.api.video/vod/vi2SRWJ6ipruaD9K73CJbP0V/thumbnail.jpg\"\n" +
                        "            }\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"videoId\": \"vi7Q6vMxGRG2iF1cnF5GJoyE\",\n" +
                        "            \"title\": \"updated video\",\n" +
                        "            \"description\": \"description for updated video\",\n" +
                        "            \"public\": true,\n" +
                        "            \"panoramic\": false,\n" +
                        "            \"publishedAt\": \"2019-08-29T16:45:41+02:00\",\n" +
                        "            \"updatedAt\": \"2019-08-29T16:45:41+02:00\",\n" +
                        "            \"tags\": [\n" +
                        "                \"tata\",\n" +
                        "                \"titi\"\n" +
                        "            ],\n" +
                        "            \"metadata\": [\n" +
                        "                {\n" +
                        "                    \"key\": \"age\",\n" +
                        "                    \"value\": \"__age__\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"key\": \"author\",\n" +
                        "                    \"value\": \"kenny\"\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"source\": {\n" +
                        "                \"type\": \"upload\",\n" +
                        "                \"uri\": \"/videos/vi7Q6vMxGRG2iF1cnF5GJoyE/source\"\n" +
                        "            },\n" +
                        "            \"assets\": {\n" +
                        "                \"iframe\": \"<iframe src=\\\"https://embed-staging.api.video/vod/vi7Q6vMxGRG2iF1cnF5GJoyE\\\" width=\\\"100%\\\" height=\\\"100%\\\" frameborder=\\\"0\\\" scrolling=\\\"no\\\" allowfullscreen=\\\"\\\"></iframe>\",\n" +
                        "                \"player\": \"https://embed-staging.api.video/vod/vi7Q6vMxGRG2iF1cnF5GJoyE\",\n" +
                        "                \"hls\": \"https://cdn-staging.api.video/vod/vi7Q6vMxGRG2iF1cnF5GJoyE/hls/manifest.m3u8\",\n" +
                        "                \"thumbnail\": \"https://cdn-staging.api.video/vod/vi7Q6vMxGRG2iF1cnF5GJoyE/thumbnail.jpg\"\n" +
                        "            },\n" +
                        "            \"playerId\": \"pl6Py1UYMPM5NU9ogvaSDB6f\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"pagination\": {\n" +
                        "        \"currentPage\": 1,\n" +
                        "        \"pageSize\": 25,\n" +
                        "        \"pagesTotal\": 1,\n" +
                        "        \"itemsTotal\": 3,\n" +
                        "        \"currentPageItems\": 3,\n" +
                        "        \"links\": [\n" +
                        "            {\n" +
                        "                \"rel\": \"self\",\n" +
                        "                \"uri\": \"/videos?currentPage=1\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"first\",\n" +
                        "                \"uri\": \"/videos?currentPage=1\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"rel\": \"last\",\n" +
                        "                \"uri\": \"/videos?currentPage=1\"\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}"


        );
    }

    private ResponseException videoServerException() {
        return new ServerException("foo", new JsonNode(""), 500);
    }

    private ResponseException videoClientException() {
        return new ClientException("foo", new JsonNode(""), 400);
    }

    private boolean equals(RequestBuilder a, HttpRequest<?> b) {
        HttpRequest<?> areq = a.build();

        return areq.getUrl().equals(b.getUrl())
                && areq.getHttpMethod().equals(b.getHttpMethod());

    }
}
