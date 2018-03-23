package com.amasaemi.javashikiapp.data.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by Alex on 31.01.2018.
 */

public class PreferencesManager {
    private final String APP_PREFERENCES = "app_preferences";
    private final String USER_PROFILE = "user_profile";
    private final String LIST_STYLE = "list_style";
    // контекст приложения
    private Context mContext;
    // доступ к хранилищю значений
    private SharedPreferences mPreferences;

    public PreferencesManager(Context context) {
        mContext = context;
        mPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    /**
     * Метод сохраняет настройки доступа в хранилище
     * @param userProfile - профиль текущего пользователя
     */
    public void saveAccessData(StaticAppManager.UserProfileInfo userProfile) {
        mPreferences.edit()
                .putString(USER_PROFILE, new Gson().toJson(userProfile))
                .apply();
    }

    /**
     * Метод загружает настройки доступа из хранилища
     */
    public boolean loadAccessData() {
        StaticAppManager.getInstance().setCurrentUser(
                new Gson().fromJson(mPreferences.getString(USER_PROFILE, null),
                        StaticAppManager.UserProfileInfo.class));

        return StaticAppManager.getInstance().profileHasAvailable();
    }

    /**
     * Метод удаляет сохраненные настройки доступа из хранилища
     */
    public void forgetAccessData() {
        mPreferences.edit().remove(USER_PROFILE).apply();
        StaticAppManager.getInstance().setCurrentUser(null);
    }

    /**
     * Метод сохраняет настройку отображения списка
     * @param style - стиль списка (0 - длинные карточки, 1 - короткие карточки)
     */
    public void saveListStyle(int style) {
        mPreferences.edit().putInt(LIST_STYLE, style).apply();
        StaticAppManager.getInstance().setListStyle(style);
    }

    public void saveSettings() {
        // TODO: 01.02.2018 Сохранять все текущие настройки
        // TODO: 01.02.2018 в SAM создать класс, хранящий все настройки
    }

    public void loadSettings() {
        StaticAppManager.getInstance().setListStyle(mPreferences.getInt(LIST_STYLE, 0));
    }
}
