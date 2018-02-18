package com.amasaemi.javashikiapp.data.network.pojo.constants;

import android.content.Context;

import com.amasaemi.javashikiapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 01.02.2018.
 */

public enum Order {
    @SerializedName("popularity")
    @Expose
    BY_POPULARITY,

    @SerializedName("ranked")
    @Expose
    BY_RANKED,

    @SerializedName("name")
    @Expose
    BY_NAME,

    @SerializedName("aired_on")
    @Expose
    BY_AIRED_ON,

    @SerializedName("status")
    @Expose
    BY_STATUS;

    @Override
    public String toString() {
        return this.name().substring(3, this.name().length()).toLowerCase();
    }

    public String toLocalString(Context context) {
        return context.getResources().getStringArray(R.array.order)[this.ordinal()];
    }
}
