package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.amasaemi.javashikiapp.utils.ConstantManager;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alex on 23.12.2017.
 */

public class FavoriteResponse {
    /**
     * GET /api/users/{id}/favourites
     *
     * {
     * "animes": Array<FavoriteItemResponse>,
     * "mangas": Array<FavoriteItemResponse>,
     * "characters": Array<FavoriteItemResponse>,
     * "people": Array<FavoriteItemResponse>,
     * "mangakas": Array<FavoriteItemResponse>,
     * "seyu": Array<FavoriteItemResponse>,
     * "producers": Array<FavoriteItemResponse>
     * }
     */

    @SerializedName("animes")
    @Expose
    @Nullable
    private List<FavoriteItemResponse> animes;

    @SerializedName("mangas")
    @Expose
    @Nullable
    private List<FavoriteItemResponse> mangas;

    @SerializedName("characters")
    @Expose
    @Nullable
    private List<FavoriteItemResponse> characters;

    @SerializedName("people")
    @Expose
    @Nullable
    private List<FavoriteItemResponse> people;

    @SerializedName("mangakas")
    @Expose
    @Nullable
    private List<FavoriteItemResponse> mangakas;

    @SerializedName("seyu")
    @Expose
    @Nullable
    private List<FavoriteItemResponse> seyu;

    @SerializedName("producers")
    @Expose
    @Nullable
    private List<FavoriteItemResponse> producers;

    @Nullable
    public List<FavoriteItemResponse> getAnimes() {
        return animes;
    }

    @Nullable
    public List<FavoriteItemResponse> getMangas() {
        return mangas;
    }

    @Nullable
    public List<FavoriteItemResponse> getCharacters() {
        return characters;
    }

    @Nullable
    public List<FavoriteItemResponse> getPeople() {
        return people;
    }

    @Nullable
    public List<FavoriteItemResponse> getMangakas() {
        return mangakas;
    }

    @Nullable
    public List<FavoriteItemResponse> getSeyu() {
        return seyu;
    }

    @Nullable
    public List<FavoriteItemResponse> getProducers() {
        return producers;
    }

    public boolean isEmptyFavourite() {
        assert animes != null;
        assert mangas != null;
        assert characters != null;
        assert people != null;
        assert mangakas != null;
        assert seyu != null;
        assert producers != null;

        return animes.isEmpty() && mangas.isEmpty() && characters.isEmpty() && people.isEmpty() &&
                mangakas.isEmpty() && seyu.isEmpty() && producers.isEmpty();
    }

    public class FavoriteItemResponse {
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        @NonNull
        private String enName;

        @SerializedName("russian")
        @Expose
        @NonNull
        private String ruName;

        @SerializedName("image")
        @Expose
        @NonNull
        private String poster = "/assets/globals/missing_avatar/preview.png";

        public int getId() {
            return id;
        }

        @NonNull
        public String getEnName() {
            return enName;
        }

        @NonNull
        public String getRuName() {
            return ruName;
        }

        @NonNull
        public String getPoster() {
            return ConstantManager.SHIKI_BASE + poster.replace("x64", "x96"); // preview
        }
    }
}
