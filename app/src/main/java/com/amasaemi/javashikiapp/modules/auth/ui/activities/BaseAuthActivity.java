package com.amasaemi.javashikiapp.modules.auth.ui.activities;

import com.amasaemi.javashikiapp.data.managers.StaticAppManager;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserInfoResponse;
import com.amasaemi.javashikiapp.modules.base.ui.activities.BaseActivity;

/**
 * Created by Alex on 25.03.2018.
 */

public abstract class BaseAuthActivity extends BaseActivity {
    /**
     * Метод устанавливает профиль текущего пользователя
     * @param response ответ от сервера с данными пользователя
     */
    protected void instanceUserProfile(UserInfoResponse response) {
        StaticAppManager.getInstance().setUserProfile(response);
    }

    /**
     * Метод очищает данные о текущем пользователе
     */
    protected void forgotCurrentUser() {
        mPreferencesManager.forgetAccessData();
    }
}
