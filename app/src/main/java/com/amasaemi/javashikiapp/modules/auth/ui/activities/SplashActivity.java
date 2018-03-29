package com.amasaemi.javashikiapp.modules.auth.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.amasaemi.javashikiapp.data.managers.PreferencesManager;
import com.amasaemi.javashikiapp.data.managers.StaticAppManager;
import com.amasaemi.javashikiapp.data.services.UserService;
import com.amasaemi.javashikiapp.modules.base.ui.activities.BaseActivity;
import com.amasaemi.javashikiapp.modules.list.ui.activities.ListActivity;

/**
 * Created by Alex on 26.02.2018.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (StaticAppManager.getInstance().tokensHasAvailable()) {
            // проверяем верность сохраненных данных
            new UserService().getCurrentUser((response) -> {
                StaticAppManager.getInstance().setUserProfile(response);
                startActivity(new Intent(this, ListActivity.class));
            }, (t) -> {
                mPreferencesManager.forgetAccessData();
                startActivity(new Intent(this, AuthActivity.class));
            });
        } else {
            startActivity(new Intent(this, AuthActivity.class));
        }

        this.finish();
    }
}
