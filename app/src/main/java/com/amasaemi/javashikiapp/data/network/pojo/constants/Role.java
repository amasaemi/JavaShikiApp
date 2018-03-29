package com.amasaemi.javashikiapp.data.network.pojo.constants;

import android.content.Context;

import com.amasaemi.javashikiapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public enum  Role {
    @SerializedName("Main")
    @Expose
    MAIN,

    @SerializedName("Supporting")
    @Expose
    SUPPORTING,

    @SerializedName("null")
    @Expose
    NONE,;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public String toLocalString(Context context) {
        try {
            return context.getResources().getStringArray(R.array.role)[this.ordinal()];
        } catch (NullPointerException npe) {
            return context.getResources().getStringArray(R.array.role)[2];
        }
    }
}
