package com.amasaemi.javashikiapp.data.network.api;

import com.amasaemi.javashikiapp.data.network.pojo.res.ClubsListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alex on 25.02.2018.
 */

public interface ClubsApi {
    /**
     * Метод запрашивает список клубов
     * @param search поисковой запрос
     * @return ответ от сервера
     */
    @GET("/api/clubs?limit=30")
    Call<List<ClubsListResponse>> getClubsList(@Query("page") int page, @Query("search") String search);
}
