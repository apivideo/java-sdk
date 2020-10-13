package video.api.java.sdk.infrastructure.unirest.account;

import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import video.api.java.sdk.domain.account.Account;
import video.api.java.sdk.infrastructure.unirest.serializer.JsonDeserializer;

public class AccountDeserializer implements JsonDeserializer<Account> {
    public Account deserialize(JSONObject data) throws JSONException {
        JSONObject jsonQuota = data.getJSONObject("quota");

        return new Account(
                new Account.Quota(
                        getIntOrNull(jsonQuota, "quotaRemaining"),
                        getIntOrNull(jsonQuota, "quotaUsed"),
                        getIntOrNull(jsonQuota, "quotaTotal")
                ),
                convertJsonArrayToStringList(data.getJSONArray("features"))
        );
    }

    private Integer getIntOrNull(JSONObject data, String key) {
        return data.has(key) && !data.isNull(key) ? data.getInt(key) : null;
    }

}
