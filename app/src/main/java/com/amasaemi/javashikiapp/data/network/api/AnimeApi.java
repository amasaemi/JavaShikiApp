package com.amasaemi.javashikiapp.data.network.api;

import com.amasaemi.javashikiapp.data.network.pojo.constants.Order;
import com.amasaemi.javashikiapp.data.network.pojo.res.CalendarResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Alex on 01.02.2018.
 */

public interface AnimeApi {
    /**
     * Метод запрашивает список аниме
     * @param page номер страницы
     * @param order сортировка
     * @param kind тип (TV, OVA, ONA, ..)
     * @param status статус (онгоинг, вышло, запланировано)
     * @param season сезон
     * @param rating возрастной рейтинг
     * @param genre жанры
     * @param myList статус в "моем списке"
     * @param search поисковой запрос
     * @return ответ от сервера
     */
    @GET("/api/animes?limit=16")
    Call<List<TitleListItemResponse>>
        getAnimeList(@Query("page") int page,
                     @Query("order") Order order,
                     @Query("type") String kind,
                     @Query("status") String status,
                     @Query("season") String season,
                     @Query("rating") String rating,
                     @Query("genre") String genre,
                     @Query("mylist") String myList,
                     @Query("search") String search);

    /**
     * Метод запрашивает календарь аниме
     * @return ответ от сервера
     */
    @GET("/api/calendar")
    Call<List<CalendarResponse>> getCalendar();

    /**
     * Метод запрашивает описание запрошенного аниме
     * @param id id выбранного аниме
     * @return ответ от сервера
     */
    @GET("/api/animes/{id}")
    Call<TitleInfoResponse> getSelectedAnime(@Path("id") int id);

    /**
     * Метод запрашивает внешние ссылки запрошенного аниме
     * @param id id выбранного аниме
     * @return ответ от сервера
     */
    @GET("/api/animes/{id}/external_links")
    Call<List<TitleInfoResponse.ExternalLinksResponse>> getExternalLinks(@Path("id") int id);

    /**
     * Метод запрашивает скриншоты запрошенного аниме
     * @param id id выбранного аниме
     * @return ответ от сервера
     */
    @GET("/api/animes/{id}/screenshots")
    Call<List<TitleInfoResponse.ScreenhostResponse>> getScreenhots(@Path("id") int id);

    /**
     * Метод запрашивает видео запрошенного аниме
     * @param id id выбранного аниме
     * @return ответ от сервера
     */
    @GET("/api/animes/{id}/videos")
    Call<List<TitleInfoResponse.VideoResponse>> getVideos(@Path("id") int id);

    /**
     * Метод запрашивает связанные тайтлы запрошенного аниме
     * @param id id выбранного аниме
     * @return ответ от сервера
     */
    @GET("/api/animes/{id}/related")
    Call<List<TitleInfoResponse.RelatedResponse>> getRelated(@Path("id") int id);

    /**
     * Метод запрашивает похожие аниме запрошенного аниме
     * @param id id выбранного аниме
     * @return ответ от сервера
     */
    @GET("/api/animes/{id}/similar")
    Call<List<TitleListItemResponse>> getSimilar(@Path("id") int id);

    /**
     * Метод запрашивает список ролей в аниме
     * @param id id выбранного аниме
     * @return ответ от сервера
     */
    @GET("/api/animes/{id}/roles")
    Call<List<TitleInfoResponse.RolesResponse>> getRoles(@Path("id") int id);
}
