package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.support.annotation.NonNull;

import com.amasaemi.javashikiapp.data.network.pojo.constants.MyList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 01.02.2018.
 */

public class UserRateResponse {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("score")
    @Expose
    private int score = 0;

    @SerializedName("status")
    @Expose
    private MyList status = MyList.NONE;

    @SerializedName("episodes")
    @Expose
    private int ep = -1;

    @SerializedName("chapters")
    @Expose
    private int chapt = -1;

    @SerializedName("volumes")
    @Expose
    private int vol = -1;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("text_html")
    @Expose
    private String textHtml;

    @SerializedName("rewatches")
    @Expose
    private int rewatches;

    /**
     * Метод возвращает id userRate
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Метод возвращает оценку пользователя
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Метод возвращает статус в пользовательском рейтинге
     * @return
     */
    @NonNull
    public MyList getStatus() {
        return (status != null) ? status : MyList.NONE;
    }

    /**
     * Метод возвращает количество просмотренных эпизодов / прочитанных глав
     * @return
     */
    public int getWatches() {
        return (ep > -1) ? ep : (chapt > -1) ? chapt : 0;
    }

    /**
     * Метод возвращает количество перепросмотрев / перечитываний
     * @return
     */
    public int getRewatches() {
        return rewatches;
    }
}
