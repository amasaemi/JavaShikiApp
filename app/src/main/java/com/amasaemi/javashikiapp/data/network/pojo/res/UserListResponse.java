package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.net.Uri;

import com.amasaemi.javashikiapp.data.network.pojo.sup.AvatarImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 22.02.2018.
 */

public class UserListResponse {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("image")
    @Expose
    private AvatarImage image;

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public Uri getAvatar() {
        return image.getSmall();
    }
}
