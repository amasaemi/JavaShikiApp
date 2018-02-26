package com.amasaemi.javashikiapp.data.network.pojo.sup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 22.02.2018.
 */

public class Notice {
    @SerializedName("notice")
    @Expose
    private String notice;

    public String getNotice() {
        return notice;
    }
}
