package com.amasaemi.javashikiapp.data.services;

import com.amasaemi.javashikiapp.data.network.api.AnimeApi;
import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
import com.amasaemi.javashikiapp.data.network.pojo.res.CalendarResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.data.services.interfaces.BaseNetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 01.02.2018.
 */

public class AnimeService extends BaseNetworkService {
    private AnimeApi mAnimeApi = new Retrofit.Builder()
            .baseUrl(SHIKI_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mHttpClient).build().create(AnimeApi.class);

    /**
     * GET запрос /api/animes
     * @param page - страница, целое число
     * @params - количество элементов на странице (по умолчанию - 16)
     * @order - сортировка, возможно null
     * @kind - тип аниме, возможно null
     * @status - статус аниме, возможно null
     * @season - аниме сезон, возможно null
     * @rating - возрастной рейтинг, возможно null
     * @genre - жанры, возможно null
     * @myList - статус аниме в моем списке, возможно null
     */
    public void getAnimeList(int page, SearchingParams params, Success<List<TitleListItemResponse>> success, Failure failure) {
        mAnimeApi.getAnimeList(page, params.getOrder(), params.getKinds(), params.getStatuses(),
                params.getSeasons(), params.getRatings(), params.getGenres(), params.getMyLists(), params.getSearch())
                    .enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/calendar
     */
    public void getCalendar(Success<List<CalendarResponse>> success, Failure failure) {
        mAnimeApi.getCalendar().enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/animes/{id}
     * @param animeId - id аниме
     */
    public void getSelectedAnime(int animeId, Success<TitleInfoResponse> success, Failure failure) {
        mAnimeApi.getSelectedAnime(animeId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/animes/{id}/external_links
     * @param animeId - id аниме
     */
    public void getExternalLinks(int animeId, Success<List<TitleInfoResponse.ExternalLinksResponse>> success, Failure failure) {
        mAnimeApi.getExternalLinks(animeId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/animes/{id}/screenshots
     * @param animeId - id аниме
     */
    public void getScreenhots(int animeId, Success<List<TitleInfoResponse.ScreenhostResponse>> success, Failure failure) {
        mAnimeApi.getScreenhots(animeId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/animes/{id}/videos
     * @param animeId - id аниме
     */
    public void getVideos(int animeId, Success<List<TitleInfoResponse.VideoResponse>> success, Failure failure) {
        mAnimeApi.getVideos(animeId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/animes/{id}/status (список связанных аниме)
     * @param animeId - id аниме
     */
    public void getRelated(int animeId, Success<List<TitleInfoResponse.RelatedResponse>> success, Failure failure) {
        mAnimeApi.getRelated(animeId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/animes/{id}/similar (список похожих аниме)
     * @param animeId - id аниме
     */
    public void getSimilar(int animeId, Success<List<TitleListItemResponse>> success, Failure failure) {
        mAnimeApi.getSimilar(animeId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/animes/{id}/roles
     * @param animeId - id аниме
     */
    public void getRoles(int animeId, Success<List<TitleInfoResponse.RolesResponse>> success, Failure failure) {
        mAnimeApi.getRoles(animeId).enqueue(request(success, failure));
    }
}
