package com.amasaemi.javashikiapp.data.network.pojo.constants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public enum UserRate {
    NONE,

    @SerializedName("user_id")
    @Expose
    USER_ID,

    @SerializedName("target_id")
    @Expose
    TARGET_ID,

    @SerializedName("target_type")
    @Expose
    TARGET_TYPE,

    @SerializedName("episodes")
    @Expose
    EPISODES,

    @SerializedName("chapters")
    @Expose
    CHAPTERS,

    @SerializedName("score")
    @Expose
    SCORE,

    @SerializedName("rewatches")
    @Expose
    REWATCHES,

    @SerializedName("status")
    @Expose
    STATUS;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
