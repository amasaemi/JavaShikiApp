package com.amasaemi.javashikiapp.data.managers;

import android.net.Uri;

import com.amasaemi.javashikiapp.data.network.pojo.res.AuthResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.sup.Image;
import com.amasaemi.javashikiapp.modules.base.adapters.SimpleRecyclerAdapter;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Alex on 31.01.2018.
 */

public class StaticAppManager {
    private static final StaticAppManager ourInstance = new StaticAppManager();

    public static StaticAppManager getInstance() {
        return ourInstance;
    }

    // текущая авторизационная информация о пользователе
    private UserProfileInfo curUserProfileInfo;
    // авторизационная информация пользователя
    private UserAuthInfo mUserAuthInfo;

    // настройки
    // стиль списков
    private int listStyle = SimpleRecyclerAdapter.STYLE_LONG;
    // качество изображения списков
    private int listImageQuality = Image.QUALITY_LOW;
    // анимированное переключение фрагментов
    private boolean animateFragmentChange = false;
    // стратегия в отношении хранения картинок в постоянной памяти
    private DiskCacheStrategy discCacheStrategy = DiskCacheStrategy.RESOURCE;
    // стратегия хранения картинок в оперативной памяти (false - хранить, true - не хранить)
    private boolean skipMemoryCacheStrategy = true;

    public UserAuthInfo getUserAuthInfo() {
        return mUserAuthInfo;
    }

    public void setUserAuthInfo(UserAuthInfo userAuthInfo) {
        mUserAuthInfo = userAuthInfo;
    }

    public UserProfileInfo getCurrentUser() {
        return curUserProfileInfo;
    }

    public void setCurrentUser(UserProfileInfo curUserProfileInfo) {
        this.curUserProfileInfo = curUserProfileInfo;
    }

    public boolean profileHasAvailable() {
        return (curUserProfileInfo != null) && curUserProfileInfo.hasAvailable();
    }

    public boolean authTokenHasAvailable() {
        return (getUserAuthInfo() != null) && getUserAuthInfo().hasAvailable();
    }

    public int getListStyle() {
        return listStyle;
    }

    public void setListStyle(int listStyle) {
        this.listStyle = listStyle;
    }

    public DiskCacheStrategy getDiscCacheStrategy() {
        return discCacheStrategy;
    }

    public void setDiscCacheStrategy(DiskCacheStrategy discCacheStrategy) {
        this.discCacheStrategy = discCacheStrategy;
    }

    public boolean skipMemoryCacheStrategy() {
        return skipMemoryCacheStrategy;
    }

    public void setSkipMemoryCacheStrategy(boolean skipMemoryCacheStrategy) {
        this.skipMemoryCacheStrategy = skipMemoryCacheStrategy;
    }

    public boolean isAnimateFragmentChange() {
        return animateFragmentChange;
    }

    public void setAnimateFragmentChange(boolean animateFragmentChange) {
        this.animateFragmentChange = animateFragmentChange;
    }

    public int getListImageQuality() {
        return listImageQuality;
    }

    public void setListImageQuality(int listImageQuality) {
        this.listImageQuality = listImageQuality;
    }

    public static class UserProfileInfo {
        // id пользователя
        private int id = -1;
        // логин и ник пользователя
        private String login;
        // аватар пользователя
        private Uri avatar;
        // доступ к токенам пользователя
        private UserAuthInfo userTokenInfo;

        public UserProfileInfo(UserInfoResponse response, UserAuthInfo authInfo) {
            this.id = response.getId();
            this.login = response.getNickname();
            this.avatar = response.getAvatar();
            this.userTokenInfo = authInfo;
        }

        public UserProfileInfo(int id, String login, String authToken, String refreshToken, Uri avatar, int tokenLeftAt) {
            this.id = id;
            this.login = login;
            this.avatar = avatar;
            this.userTokenInfo = new UserAuthInfo(authToken, refreshToken, tokenLeftAt);
        }

        public int getId() {
            return id;
        }

        public String getLogin() {
            return login;
        }

        public String getAuthToken() {
            return (userTokenInfo != null) ? userTokenInfo.authToken : null;
        }

        public String getRefreshToken() {
            return (userTokenInfo != null) ? userTokenInfo.refreshToken : null;
        }

        public Uri getAvatar() {
            return avatar;
        }

        /**
         * Метод вернет true, если имеются id, логин и оба токена в наличии, а так же время
         * действия токена тоступа не истекло
         * @return
         */
        private boolean hasAvailable() {
            return id != -1 && getLogin() != null && getAuthToken() != null && getRefreshToken() != null
                    && System.currentTimeMillis() > ((userTokenInfo != null) ? userTokenInfo.tokenLeftAt : 0);
        }
    }

    public static class UserAuthInfo {
        // токен авторизации пользователя
        private String authToken;
        // токен обновления токена авторизации
        private String refreshToken;
        // время окончания действия токена
        private int tokenLeftAt;

        private UserAuthInfo(String authToken, String refreshToken, int tokenLeftAt) {
            this.authToken = authToken;
            this.refreshToken = refreshToken;
            this.tokenLeftAt = tokenLeftAt;
        }

        public UserAuthInfo(AuthResponse response) {
            this.authToken = response.getAccessToken();
            this.refreshToken = response.getRefreshToken();
            this.tokenLeftAt = response.tokenLeftAt();
        }

        public String getAuthToken() {
            return authToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public int tokenLeftAt() {
            return tokenLeftAt;
        }


        /**
         * Метод вернет true, если имеются id, логин и оба токена в наличии, а так же время
         * действия токена тоступа не истекло
         * @return
         */
        private boolean hasAvailable() {
            return getAuthToken() != null && getRefreshToken() != null
                    && System.currentTimeMillis() > tokenLeftAt;
        }
    }
}
