package com.amasaemi.javashikiapp.data.network.api;

import com.amasaemi.javashikiapp.data.network.pojo.constants.Order;
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

public interface MangaApi {
    /**
     * Метод запрашивает список манги
     * @param page номер страницы
     * @param order сортировка
     * @param kind тип (manga, manhua, ..)
     * @param status статус (онгоинг, вышло, запланировано)
     * @param season сезон
     * @param genre жанры
     * @param myList статус в "моем списке"
     * @return ответ от сервера
     */
    @GET("/api/mangas?limit=16")
    Call<List<TitleListItemResponse>>
        getMangaList(@Query("page") int page,
                     @Query("order") Order order,
                     @Query("type") String kind,
                     @Query("status") String status,
                     @Query("season") String season,
                     @Query("genre") String genre,
                     @Query("mylist") String myList,
                     @Query("search") String search);

    /**
     * Метод запрашивает описание запрошенной манги
     * @param id id выбранной манги
     * @return ответ от сервера
     */
    @GET("/api/mangas/{id}")
    Call<TitleInfoResponse> getSelectedManga(@Path("id") int id);

    /**
     * Метод запрашивает внешние ссылки запрошенной манги
     * @param id id выбранной манги
     * @return ответ от сервера
     */
    @GET("/api/mangas/{id}/external_links")
    Call<List<TitleInfoResponse.ExternalLinksResponse>> getExternalLinks(@Path("id") int id);

    /**
     * Метод запрашивает связанные тайтлы запрошенной манги
     * @param id id выбранной манги
     * @return ответ от сервера
     */
    @GET("/api/mangas/{id}/related")
    Call<List<TitleInfoResponse.RelatedResponse>> getRelated(@Path("id") int id);

    /**
     * Метод запрашивает похожие манги запрошенной манги
     * @param id id выбранной манги
     * @return ответ от сервера
     */
    @GET("/api/mangas/{id}/similar")
    Call<List<TitleListItemResponse>> getSimilar(@Path("id") int id);

    /**
     * Метод запрашивает список ролей в манге
     * @param id id выбранной манги
     * @return ответ от сервера
     */
    @GET("/api/mangas/{id}/roles")
    Call<List<TitleInfoResponse.RolesResponse>> getRoles(@Path("id") int id);
}
