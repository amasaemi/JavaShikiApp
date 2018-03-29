package com.amasaemi.javashikiapp.data.network.pojo.constants;

import android.content.Context;

import com.amasaemi.javashikiapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public enum Related {
    @SerializedName("Adaptation")
    @Expose
    ADAPTATION,

    @SerializedName("Summary")
    @Expose
    SUMMARY,

    @SerializedName("Sequel")
    @Expose
    SEQUEL,

    @SerializedName("Alternative version")
    @Expose
    ALTERNATIVE,

    @SerializedName("Other")
    @Expose
    OTHER,

    @SerializedName("Spin-off")
    @Expose
    SPINOFF,

    @SerializedName("Side story")
    @Expose
    SIDESTORY,

    @SerializedName("Alternative setting")
    @Expose
    ALTERNATIVESETTING,

    @SerializedName("Parent story")
    @Expose
    PARENTSTORY,

    @SerializedName("Prequel")
    @Expose
    PREQUEL,

    @SerializedName("Character")
    @Expose
    CHARACTER,

    @SerializedName("null")
    @Expose
    NONE;

    @Override
    public String toString() {
        switch (this) {
            case ADAPTATION:
                return "Adaptation";
            case SUMMARY:
                return "Summary";
            case SEQUEL:
                return "Sequel";
            case ALTERNATIVE:
                return "Alternative version";
            case OTHER:
                return "Other";
            case SPINOFF:
                return "Spin-off";
            case SIDESTORY:
                return "Side story";
            case ALTERNATIVESETTING:
                return "Alternative setting";
            case PARENTSTORY:
                return "Parent story";
            case PREQUEL:
                return "Prequel";
            case CHARACTER:
                return "Character";
            default:
                return "none";
        }
    }

    public String toLocalString(Context context) {
        switch (this) {
            case NONE:
                return context.getString(R.string.none);

            default:
                return context.getResources().getStringArray(R.array.related)[this.ordinal()];
        }
    }
}
