package com.amasaemi.javashikiapp.data.network.pojo.constants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 01.02.2018.
 */

public enum TitleType {
    @SerializedName("anime")
    @Expose
    ANIME,

    @SerializedName("manga")
    @Expose
    MANGA,
    RANOBE,
    NONE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
