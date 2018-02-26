package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.amasaemi.javashikiapp.data.network.pojo.sup.Image;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 23.12.2017.
 */

public class ClubsListResponse {
    /**
     *   {
     *      "id": Int,
     *      "nickname": String,
     *      "avatar": String,
     *      "logo": Image,
     *      "is_censored": Boolean
     *   }
     */

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    @NonNull
    private String name;

    @SerializedName("logo")
    @Expose
    @NonNull
    private Image image;

    @SerializedName("is_censored")
    @Expose
    private boolean isCensored;

    public Uri getPoster() {
        return image.getSmall();
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public boolean isCensored() {
        return isCensored;
    }
}
