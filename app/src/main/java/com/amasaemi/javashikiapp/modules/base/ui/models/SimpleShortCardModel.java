package com.amasaemi.javashikiapp.modules.base.ui.models;

import android.databinding.BaseObservable;
import android.net.Uri;

import com.amasaemi.javashikiapp.data.network.pojo.res.CalendarResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;

/**
 * Created by Alex on 02.02.2018.
 */

public abstract class SimpleShortCardModel extends BaseObservable {
    protected int id;

    public Runnable cardClick;
    public Runnable menuClick;
    public String ruName;
    public String enName;
    public Uri poster;

    public SimpleShortCardModel(int id, String title, String subtitle, Uri poster, Runnable cardClick, Runnable menuClick) {
        this.id = id;
        this.ruName = title;
        this.enName = subtitle;
        this.poster = poster;
        this.cardClick = cardClick;
        this.menuClick = menuClick;
    }

    public SimpleShortCardModel(TitleListItemResponse model, Runnable cardClick, Runnable menuClick) {
        this.id = model.getId();
        this.ruName = model.getRuName();
        this.enName = model.getEnName();
        this.poster = model.getPoster();
        this.cardClick = cardClick;
        this.menuClick = menuClick;
    }

    public SimpleShortCardModel(CalendarResponse model, Runnable cardClick, Runnable menuClick) {
        this.id = model.getAnime().getId();
        this.ruName = model.getAnime().getRuName();
        this.enName = model.getAnime().getEnName();
        this.poster = model.getAnime().getPoster();
        this.cardClick = cardClick;
        this.menuClick = menuClick;
    }

    public SimpleShortCardModel(TitleInfoResponse.RelatedResponse model, Runnable cardClick, Runnable menuClick) {
        this.id = model.getTitle().getId();
        this.ruName = model.getTitle().getRuName();
        this.enName = model.getTitle().getEnName();
        this.poster = model.getTitle().getPoster();
        this.cardClick = cardClick;
        this.menuClick = menuClick;
    }
}
