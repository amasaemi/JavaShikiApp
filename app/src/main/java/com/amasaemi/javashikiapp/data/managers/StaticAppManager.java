package com.amasaemi.javashikiapp.data.managers;

import com.amasaemi.javashikiapp.data.network.pojo.res.AuthResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.sup.Image;
import com.amasaemi.javashikiapp.modules.base.adapters.SimpleRecyclerAdapter;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 31.01.2018.
 */

public class StaticAppManager {
    private static final StaticAppManager ourInstance = new StaticAppManager();
    // профиль текущего пользователя
    private Profile userProfile;
    // токены доступа текущего пользователя
    private Tokens userTokens;
    // текущие настройки приложения
    private Settings userSettings = new Settings();

    public Profile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserInfoResponse userProfile) {
        if (userProfile != null) this.userProfile = new Profile(userProfile);
    }

    public Tokens getUserTokens() {
        return userTokens;
    }

    public void setUserTokens(Tokens userTokens) {
        if (userTokens != null) this.userTokens = userTokens;
    }

    public void setUserTokens(AuthResponse response) {
        if (response != null) this.userTokens = new Tokens(response);
    }

    public Settings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(Settings.StoredSettings settings) {
        if (settings != null) this.userSettings.setSettings(settings);
    }

    public boolean tokensHasAvailable() {
        return getUserTokens() != null && getUserTokens().hasAvailable();
    }

    /**
     * Метод "забывает" текущего пользователя
     */
    public void forgotUser() {
        userProfile = null;
        userTokens = null;
    }

    private StaticAppManager() { }

    public static StaticAppManager getInstance() {
        return ourInstance;
    }

    public class Settings {
        private StoredSettings mSettings = new StoredSettings();
        // стратегия в отношении хранения картинок в постоянной памяти
        private final DiskCacheStrategy discCacheStrategy = DiskCacheStrategy.RESOURCE;
        // стратегия хранения картинок в оперативной памяти (false - хранить, true - не хранить)
        private final boolean skipMemoryCacheStrategy = true;

        public Settings() { }

        private Settings(int listStyle, int listImageQuality, boolean animateSwapFragments) {
            mSettings.listStyle = listStyle;
            mSettings.listImageQuality = listImageQuality;
            mSettings.animateSwapFragments = animateSwapFragments;
        }

        public int getListStyle() {
            return mSettings.listStyle;
        }

        public void setListStyle(int listStyle) {
            mSettings.listStyle = listStyle;
        }

        public int getListImageQuality() {
            return mSettings.listImageQuality;
        }

        public void setListImageQuality(int listImageQuality) {
            mSettings.listImageQuality = listImageQuality;
        }

        public boolean hasAnimateSwapFragments() {
            return mSettings.animateSwapFragments;
        }

        public void setAnimateSwapFragments(boolean animateSwapFragments) {
            mSettings.animateSwapFragments = animateSwapFragments;
        }

        public DiskCacheStrategy getDiscCacheStrategy() {
            return discCacheStrategy;
        }

        public void setSettings(StoredSettings settings) {
            mSettings = settings;
        }

        public StoredSettings getStoredSettings() {
            return mSettings;
        }

        public boolean hasSkipMemoryCacheStrategy() {
            return skipMemoryCacheStrategy;
        }

        class StoredSettings {
            // стиль списков
            @SerializedName("list_style")
            @Expose
            private int listStyle = SimpleRecyclerAdapter.STYLE_LONG;
            // качество изображения списков
            @SerializedName("list_image_quality")
            @Expose
            private int listImageQuality = Image.QUALITY_LOW;
            // анимированное переключение фрагментов
            @SerializedName("animate_swap_fragments")
            @Expose
            private boolean animateSwapFragments = false;
        }
    }

    public class Profile {
        // id пользователя
        private int id = -1;
        // логин и ник пользователя
        private String login;
        // аватар пользователя
        private String avatar;

        private Profile(UserInfoResponse response) {
            this.id = response.getId();
            this.login = response.getNickname();
            this.avatar = response.getAvatar().toString();
        }

        public int getId() {
            return id;
        }

        public String getLogin() {
            return login;
        }

        public String getAvatar() {
            return avatar;
        }

        public boolean hasAvailable() {
            return id != -1 && login != null && avatar != null;
        }
    }

    public class Tokens {
        // токен авторизации пользователя
        @SerializedName("access_token")
        @Expose
        private String accessToken;
        // токен обновления токена авторизации
        @SerializedName("refresh_token")
        @Expose
        private String refreshToken;
        // время окончания действия токена
        @SerializedName("token_left_at")
        @Expose
        private long tokenLeftAt;

        public Tokens(AuthResponse response) {
            this.accessToken = response.getAccessToken();
            this.refreshToken = response.getRefreshToken();
            this.tokenLeftAt = response.tokenLeftAt();
        }

        public String getAccessToken() {
            return accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public boolean hasAvailable() {
            return accessToken != null && refreshToken != null;
        }
    }
}
