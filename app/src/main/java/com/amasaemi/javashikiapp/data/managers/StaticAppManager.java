package com.amasaemi.javashikiapp.data.managers;

import android.net.Uri;

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
    private UserAuthInfo curUserAuthInfo;
    // краткий профиль текущего пользователя
    private ShortUserProfile curUserProfile;

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
    private boolean skipMemoryCacheStrategy = false;

    /**
     * Метод возвращает профиль текущего пользователя
     * @return
     */
    public ShortUserProfile getCurrentUser() {
        return curUserProfile;
    }

    /**
     * Метод настраивает профиль авторизированного пользователя
     * @param userProfile
     */
    public void setCurrentUser(ShortUserProfile userProfile) {
        this.curUserProfile = curUserProfile;
    }

    /**
     * Метод вернет true, если текущий пользователь авторизирован в приложении
     * @return
     */
    public boolean hasAuth() {
        return (curUserProfile != null) ? (curUserProfile.getId() != -1)
                : getUserLogin() != null && getUserToken() != null;
    }

    /**
     * Метод вернет логин текущего пользователя
     * @return
     */
    public String getUserLogin() {
        return (curUserProfile != null) ? curUserProfile.getLogin()
                : (curUserAuthInfo != null) ? curUserAuthInfo.getLogin() : null;
    }

    /**
     * Метод вернет токен текущего пользователя
     * @return
     */
    public String getUserToken() {
        return (curUserProfile != null) ? curUserProfile.getToken()
                : (curUserAuthInfo != null) ? curUserAuthInfo.getToken() : null;
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

    class UserAuthInfo {
        private String login;
        private String token;

        public UserAuthInfo(String login, String token) {
            this.login = login;
            this.token = token;
        }

        public String getLogin() {
            return login;
        }

        public String getToken() {
            return token;
        }
    }

    public class ShortUserProfile {
        private int id = -1;
        private String login;
        private String token;
        private Uri avatar;
        private String name;
        private String sex;
        private String birth;

        public ShortUserProfile(int id, String login, String token, Uri avatar, String name, String sex, String birth) {
            this.id = id;
            this.login = login;
            this.token = token;
            this.avatar = avatar;
            this.name = name;
            this.sex = sex;
            this.birth = birth;
        }

        // TODO: 01.02.2018 Доделать второй конструктор
//        constructor(userAuthInfo: UserAuthInfo, response: UserInfoResponse)
//                : this(response.id, userAuthInfo.userLogin, userAuthInfo.userToken, response.avatar, response.name, response.sex, response.birthOn)

        public int getId() {
            return id;
        }

        public String getLogin() {
            return login;
        }

        public String getToken() {
            return token;
        }

        public Uri getAvatar() {
            return avatar;
        }

        public String getName() {
            return (name != null) ? name : login;
        }

        public String getSex() {
            return sex;
        }

        public String getBirth() {
            return birth;
        }
    }
}
