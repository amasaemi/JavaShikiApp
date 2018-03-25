package com.amasaemi.javashikiapp.data.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.amasaemi.javashikiapp.data.network.pojo.sup.Image;
import com.amasaemi.javashikiapp.modules.base.adapters.SimpleRecyclerAdapter;
import com.google.gson.Gson;

/**
 * Created by Alex on 31.01.2018.
 */

public class PreferencesManager {
    private final String APP_PREFERENCES = "app_preferences";
    private final String USER_AUTH_TOKENS = "user_tokens";
    private final String USER_SETTINGS = "user_settings";
    // доступ к хранилищю значений
    private SharedPreferences mPreferences;

    public PreferencesManager(Context context) {
        mPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    /**
     * Метод сохраняет настройки доступа в хранилище
     * @param userProfile - профиль текущего пользователя
     */
    public void saveAccessData(StaticAppManager.Tokens userProfile) {
        mPreferences.edit()
                .putString(USER_AUTH_TOKENS, new Gson().toJson(userProfile))
                .apply();
    }

    /**
     * Метод загружает настройки доступа из хранилища
     */
    public boolean loadAccessData() {
        StaticAppManager.getInstance().setUserTokens(new Gson().fromJson(mPreferences.getString(USER_AUTH_TOKENS, null),
                StaticAppManager.Tokens.class));

        return StaticAppManager.getInstance().tokensHasAvailable();
    }

    /**
     * Метод удаляет сохраненные настройки доступа из хранилища
     */
    public void forgetAccessData() {
        mPreferences.edit().remove(USER_AUTH_TOKENS).apply();
        StaticAppManager.getInstance().forgotUser();
    }

    /**
     * Метод сохраняет все настройки приложения
     */
    public void saveSettings() {
        mPreferences.edit()
                .putString(USER_SETTINGS, new Gson().toJson(StaticAppManager.getInstance().getUserSettings().getStoredSettings()))
                .apply();
    }

    /**
     * Метод загружает все настройки приложения
     */
    public void loadSettings() {
        StaticAppManager.getInstance().setUserSettings(new Gson().fromJson(mPreferences.getString(USER_SETTINGS, null),
                StaticAppManager.Settings.StoredSettings.class));
    }
}
