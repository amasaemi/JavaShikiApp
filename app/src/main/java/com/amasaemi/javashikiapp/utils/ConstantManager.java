package com.amasaemi.javashikiapp.utils;

import com.amasaemi.javashikiapp.R;

/**
 * Created by Alex on 01.02.2018.
 */

public interface ConstantManager {
    // -- URL's --
    String SHIKI_BASE = "https://shikimori.org/";
    String SHIKI_BASE_WITHOUT_DELIMITER = "https://shikimori.org";
    String HARUHARU_BASE = "http://haru-haru.paketrny.beget.tech/";
    String RESTORE_PASS_URL = "https://shikimori.org/users/password/new";
    String SMOTRET_ANIME_BASE = "https://smotret-anime.ru/";

    // -- Image's --
    String GUEST_AVATAR = "drawable://" + R.drawable.img_guest_avatar;
    String IMAGE_ERROR = "drawable://" + R.drawable.img_load_failed;

    // -- Header's --
    String USER_AGENT = "User-Agent";
    String HEADER_USERNAME = "X-User-Nickname";
    String HEADER_TOKEN = "X-User-Api-Access-Token";

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
