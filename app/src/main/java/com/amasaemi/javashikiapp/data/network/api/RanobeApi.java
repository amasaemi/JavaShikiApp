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

public interface RanobeApi {
    /**
     * Метод запрашивает список ранобэ
     * @param page номер страницы
     * @param order сортировка
     * @param status статус (онгоинг, вышло, запланировано)
     * @param season сезон
     * @param genre жанры
     * @param myList статус в "моем списке"
     * @return ответ от сервера
     */
    @GET("/api/ranobe?limit=16")
    Call<List<TitleListItemResponse>>
        getRanobeList(@Query("page") int page,
                     @Query("order") Order order,
                     @Query("status") String status,
                     @Query("season") String season,
                     @Query("genre") String genre,
                     @Query("mylist") String myList,
                     @Query("search") String search);

    /**
     * Метод запрашивает описание выбранного ранобэ
     * @param id id выбранного ранобэ
     * @return ответ от сервера
     */
    @GET("/api/ranobe/{id}")
    Call<TitleInfoResponse> getSelectedRanobe(@Path("id") int id);

    /**
     * Метод запрашивает внешние ссылки выбранного ранобэ
     * @param id id выбранного ранобэ
     * @return ответ от сервера
     */
    @GET("/api/ranobe/{id}/external_links")
    Call<List<TitleInfoResponse.ExternalLinksResponse>> getExternalLinks(@Path("id") int id);

    /**
     * Метод запрашивает связанные тайтлы выбранного ранобэ
     * @param id id выбранной ранобэ
     * @return ответ от сервера
     */
    @GET("/api/ranobe/{id}/related")
    Call<List<TitleInfoResponse.RelatedResponse>> getRelated(@Path("id") int id);

    /**
     * Метод запрашивает похожие ранобэ выбранного ранобэ
     * @param id id выбранного ранобэ
     * @return ответ от сервера
     */
    @GET("/api/ranobe/{id}/similar")
    Call<List<TitleListItemResponse>> getSimilar(@Path("id") int id);

    /**
     * Метод запрашивает список ролей в ранобэ
     * @param id id выбранного ранобэ
     * @return ответ от сервера
     */
    @GET("/api/ranobe/{id}/roles")
    Call<List<TitleInfoResponse.RolesResponse>> getRoles(@Path("id") int id);
}
