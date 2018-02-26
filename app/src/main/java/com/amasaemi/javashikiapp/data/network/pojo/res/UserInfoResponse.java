package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.amasaemi.javashikiapp.data.network.pojo.sup.AvatarImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 23.12.2017.
 */

public class UserInfoResponse {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nickname")
    @Expose
    @NonNull
    private String nickname;

    @SerializedName("image")
    @Expose
    @NonNull
    private AvatarImage image;

    @SerializedName("last_online_at")
    @Expose
    @NonNull
    private String lastOnlineAt;

    @SerializedName("name")
    @Expose
    @Nullable
    private String name;

    @SerializedName("sex")
    @Expose
    @Nullable
    private String sex;

    @SerializedName("website")
    @Expose
    @Nullable
    private String website;

    @SerializedName("birth_on")
    @Expose
    @Nullable
    private String birthOn;

    public int getId() {
        return id;
    }

    @NonNull
    public String getNickname() {
        return nickname;
    }

    @NonNull
    public Uri getAvatar() {
        return image.getPreview();
    }

    @NonNull
    public String getLastOnlineAt() {
        return lastOnlineAt;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getSex() {
        return sex;
    }

    @Nullable
    public String getWebsite() {
        return website;
    }

    @Nullable
    public String getBirthOn() {
        return birthOn;
    }
}
