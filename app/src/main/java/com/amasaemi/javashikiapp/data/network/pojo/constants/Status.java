package com.amasaemi.javashikiapp.data.network.pojo.constants;

import android.content.Context;

import com.amasaemi.javashikiapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 01.02.2018.
 */

public enum Status {
    @SerializedName("anons")
    @Expose
    ANONS,

    @SerializedName("ongoing")
    @Expose
    ONGOING,

    @SerializedName("released")
    @Expose
    RELEASED,

    @SerializedName(value = "null", alternate = { "null", "none", "" })
    @Expose
    NONE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public String toLocalString(Context context) {
        switch (this) {
            case ANONS:
            case ONGOING:
            case RELEASED:
                return context.getResources().getStringArray(R.array.status)[this.ordinal()];

            default:
                return context.getString(R.string.none);
        }
    }
}
