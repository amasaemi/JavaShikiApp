package com.amasaemi.javashikiapp.data.network.pojo.constants;

import android.content.Context;

import com.amasaemi.javashikiapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 01.02.2018.
 */

public enum Rating {
    @SerializedName("g")
    @Expose
    G,

    @SerializedName("pg")
    @Expose
    PG,

    @SerializedName("pg_13")
    @Expose
    PG_13,

    @SerializedName("r")
    @Expose
    R,

    @SerializedName("r_plus")
    @Expose
    R_PLUS,

    @SerializedName("rx")
    @Expose
    RX,

    @SerializedName(value = "null", alternate = { "null", "none", "" })
    @Expose
    NONE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public String toLocalString(Context context) {
        switch (this) {
            case G:
            case PG:
            case PG_13:
            case R:
            case R_PLUS:
            case RX:
                return context.getResources().getStringArray(com.amasaemi.javashikiapp.R.array.rating)[this.ordinal()];

            default:
                return context.getString(com.amasaemi.javashikiapp.R.string.none);
        }
    }
}
