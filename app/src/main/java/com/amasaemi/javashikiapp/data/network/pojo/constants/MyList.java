package com.amasaemi.javashikiapp.data.network.pojo.constants;

import android.content.Context;

import com.amasaemi.javashikiapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 01.02.2018.
 */

public enum MyList {
    @SerializedName(value = "null", alternate = { "null", "none", "" })
    @Expose
    NONE,

    @SerializedName("planned")
    @Expose
    PLANNED,

    @SerializedName("watching")
    @Expose
    WATCHING,

    @SerializedName("rewatching")
    @Expose
    REWATCHING,

    @SerializedName("completed")
    @Expose
    COMPLETED,

    @SerializedName("on_hold")
    @Expose
    ON_HOLD,

    @SerializedName("dropped")
    @Expose
    DROPPED;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public String toLocalString(Context context, TitleType titleType) {
        int labelArray = 0;

        switch (titleType) {
            case ANIME:
                labelArray = R.array.anime_mylist;

            case MANGA:
            case RANOBE:
                labelArray = R.array.manga_mylist;
        }

        if (labelArray != 0)
            return context.getResources().getStringArray(labelArray)[this.ordinal()];
        else
            return context.getString(R.string.none);

    }
}
