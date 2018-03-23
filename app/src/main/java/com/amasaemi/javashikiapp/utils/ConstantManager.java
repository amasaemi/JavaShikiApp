package com.amasaemi.javashikiapp.utils;

import com.amasaemi.javashikiapp.R;

/**
 * Created by Alex on 01.02.2018.
 */

public interface ConstantManager {
    // App name
    String APP_NAME = "Shikimori android client by amasaemi";

    // -- URL's --
    String SHIKI_BASE = "https://shikimori.org/";
    String SHIKI_BASE_WITHOUT_DELIMITER = "https://shikimori.org";
    String HARUHARU_BASE = "http://haru-haru.paketrny.beget.tech/";
    String RESTORE_PASS_URL = "https://shikimori.org/users/password/new";
    String SMOTRET_ANIME_BASE = "https://smotret-anime.ru/";

    String SHIKIMORI_APP_CODE = "219254f50802ee4b9717b702b6c282ead346f803e19b1453fa1779e6446bdb4d";
    String SHIKIMORI_OAUTH_LINK = SHIKI_BASE + "oauth/authorize?client_id=" + SHIKIMORI_APP_CODE +
            "&redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&response_type=code";

    // Code's
    String APP_SECRET = "add8a42e42e5d51dbaa13a8618b83b4dfab03aef0ec4447c730f23012d595b24";
    String SHIKI_AUTH_REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

    // -- Image's --
    String GUEST_AVATAR = "drawable://" + R.drawable.img_guest_avatar;
    String IMAGE_ERROR = "drawable://" + R.drawable.img_load_failed;

    // -- Header's --
    String USER_AGENT = "User-Agent";
    String HEADER_USERNAME = "X-User-Nickname";
    String HEADER_TOKEN = "X-User-Api-Access-Token";

    interface GrantType {
        String AUTH_TOKEN = "authorization_code";
        String REFRESH_TOKEN = "refresh_token";
    }

    interface ExternalLinks {
        String OFFICIAL_SITE = "official_site";
        String WIKIPEDIA = "wikipedia";
        String ANN = "anime_news_network";
        String ANIME_DB = "anime_db";
        String MAL = "myanimelist";
        String WORLD_ART = "world_art";
        String KAGE_PROJECT = "kage_project";
        String READMANGA = "readmanga";
        String RURANOBE = "ruranobe";
    }
}
