package com.amasaemi.javashikiapp.modules.list.ui.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.ActivityManager;
import com.amasaemi.javashikiapp.data.managers.PreferencesManager;
import com.amasaemi.javashikiapp.data.managers.StaticAppManager;
import com.amasaemi.javashikiapp.modules.auth.ui.activities.AuthActivity;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.ViewModel;

/**
 * Created by Alex on 04.02.2018.
 */

public class NavigationHeaderModel implements ViewModel {
    private final Uri GUESS_AVATAR = Uri.parse(String.format("drawable://%d", R.drawable.img_load_failed));

    public String username;
    public Uri avatar;
    public Runnable avatarClick;
    public Runnable logoutClick;

    public NavigationHeaderModel(Context context, StaticAppManager.Profile userProfile) {
        if (userProfile != null) {
            username = userProfile.getLogin();
            avatar = Uri.parse(userProfile.getAvatar());
        } else {
            username = context.getString(R.string.label_guest);
            avatar = GUESS_AVATAR;
        }

        avatarClick = () -> {
            if (StaticAppManager.getInstance().getUserProfile().hasAvailable())
                ActivityManager.startUserActivity(context, StaticAppManager.getInstance().getUserProfile().getId());
        };
        logoutClick = () -> {
            new PreferencesManager(context).forgetAccessData();
            context.startActivity(new Intent(context, AuthActivity.class));
            ((Activity) context).finish();
        };
    }
}
