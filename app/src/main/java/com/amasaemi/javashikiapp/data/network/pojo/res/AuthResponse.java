package com.amasaemi.javashikiapp.data.network.pojo.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 11.03.2018.
 */

public class AuthResponse {
    // токен доступа
    @SerializedName("access_token")
    @Expose
    private String accessToken;

    // тип токена
    @SerializedName("token_type")
    @Expose
    private String tokenType;

    // период, в течении которого будет доступен токен
    @SerializedName("expires_in")
    @Expose
    private int expiresIn;

    // токен обновления токена доступа
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    // время создания токена
    @SerializedName("created_at")
    @Expose
    private int createdAt;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public int tokenLeftAt() {
        return createdAt + expiresIn;
    }
}
