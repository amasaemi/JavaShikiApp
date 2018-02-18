package com.amasaemi.javashikiapp.data.network.pojo.constants;

import android.content.Context;

import com.amasaemi.javashikiapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 01.02.2018.
 */

public enum Kind {
    @SerializedName("tv")
    @Expose
    TV,

    @SerializedName("movie")
    @Expose
    MOVIE,

    @SerializedName("ova")
    @Expose
    OVA,

    @SerializedName("ona")
    @Expose
    ONA,

    @SerializedName("special")
    @Expose
    SPECIAL,

    @SerializedName("music")
    @Expose
    MUSIC,

    @SerializedName("manga")
    @Expose
    MANGA,

    @SerializedName("manhwa")
    @Expose
    MANHWA,

    @SerializedName("manhua")
    @Expose
    MANHUA,

    @SerializedName("one_shot")
    @Expose
    ONE_SHOT,

    @SerializedName("doujin")
    @Expose
    DOUJIN,

    @SerializedName("novel")
    @Expose
    NOVEL,

    @SerializedName(value = "null", alternate = { "null", "none", "" })
    @Expose
    NONE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public String toLocalString(Context context) {
        switch (this) {
            case TV:
            case MOVIE:
            case OVA:
            case ONA:
            case SPECIAL:
            case MUSIC:
                return context.getResources().getStringArray(R.array.anime_kind)[this.ordinal()];

            case MANGA:
            case MANHWA:
            case MANHUA:
            case DOUJIN:
            case ONE_SHOT:
                return context.getResources().getStringArray(R.array.manga_kind)[this.ordinal() - 6];

            case NOVEL:
                // return context.getString(R.string.caption_ranobe)
                return ""; // TODO: 01.02.2018 брать из ресурсов

            default:
                return context.getString(R.string.none);
        }
    }
}
