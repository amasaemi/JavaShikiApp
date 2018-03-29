package com.amasaemi.javashikiapp.modules.community.models;

import android.content.Context;
import android.databinding.BaseObservable;
import android.net.Uri;

import com.amasaemi.javashikiapp.data.managers.ActivityManager;
import com.amasaemi.javashikiapp.data.network.pojo.res.ClubsListResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserListResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.ViewModel;

/**
 * Created by Alex on 22.02.2018.
 */

public class CommunityCardModel extends BaseObservable implements ViewModel {
    protected int id;

    public Runnable cardClick;
    public Runnable menuClick;
    public String title;
    public Uri avatar;

    public CommunityCardModel(Context context, UserListResponse model) {
        this.id = model.getId();
        this.cardClick = () -> ActivityManager.startUserActivity(context, model.getId());
        this.title = model.getNickname();
        this.avatar = model.getAvatar();
    }

    public CommunityCardModel(Context context, ClubsListResponse model) {
        this.id = model.getId();
        this.cardClick = () -> ActivityManager.startClubActivity(context, model.getId());
        this.title = model.getName();
        this.avatar = model.getPoster();
    }
}
