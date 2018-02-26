package com.amasaemi.javashikiapp.data.network.api;

import com.amasaemi.javashikiapp.data.network.pojo.constants.MyList;
import com.amasaemi.javashikiapp.data.network.pojo.res.ClubsListResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.FavoriteResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.HistoryResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.MyListRatesResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserListResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserResponse;
import com.amasaemi.javashikiapp.data.network.pojo.sup.Notice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Alex on 22.02.2018.
 */

public interface UsersApi {
    /**
     * Метод запрашивает информацию по авторизированному пользователю
     * @return ответ от сервера
     */
    @GET("/api/users/whoami")
    Call<UserInfoResponse> getCurrentUser();

    /**
     * Метод запрашивает информацию по пользователю
     * @param id id пользователя
     * @return ответ от сервера
     */
    @GET("/api/users/{id}")
    Call<UserResponse> getUserInfo(@Path("id") int id);

    /**
     * Метод запрашивает историю пользователя
     * @param id id пользователя
     * @param page страница запроса
     * @return ответ от сервера
     */
    @GET("/api/users/{id}/history?limit=16")
    Call<List<HistoryResponse>> getHistory(@Path("id") int id, @Query("page") int page);

    /**
     * Метод запрашивает избранное пользователя
     * @param id id пользователя
     * @return ответ от сервера
     */
    @GET("/api/users/{id}/favourites")
    Call<FavoriteResponse> getFavorites(@Path("id") int id);

    /**
     * Метод запрашивает список аниме пользователя
     * @param id id пользователя
     * @param page страница запроса
     * @param status статусы в списке пользователя (запланировано, отложено, ..)
     * @return ответ от сервера
     */
    @GET("/api/users/{id}/anime_rates?limit=16")
    Call<List<MyListRatesResponse>> getAnimeRates(@Path("id") int id, @Query("page") int page, @Query("status") MyList status);

    /**
     * Метод запрашивает список манги пользователя
     * @param id id пользователя
     * @param page страница запроса
     * @param status статусы в списке пользователя (запланировано, отложено, ..)
     * @return ответ от сервера
     */
    @GET("/api/users/{id}/manga_rates?limit=16")
    Call<List<MyListRatesResponse>> getMangaRates(@Path("id") int id, @Query("page") int page, @Query("status") MyList status);

    /**
     * Метод запрашивает список пользователей
     * @param page страница запроса
     * @return ответ от сервера
     */
    @GET("/api/users?limit=30")
    Call<List<UserListResponse>> getUsersList(@Query("page") int page, @Query("search") String search);

    /**
     * Метод запрашивает список друзей пользователя
     * @param id id пользователя
     * @return ответ от сервера
     */
    @GET("/api/users/{id}/friends")
    Call<List<UserListResponse>> getUserFriends(@Path("id") int id);

    /**
     * Метод запрашивает список клубов пользователя
     * @param id id пользователя
     * @return ответ от сервера
     */
    @GET("/api/users/{id}/clubs")
    Call<List<ClubsListResponse>> getUserMembership(@Path("id") int id);

    /**
     * Метод добавляет пользователя в друзья
     * @param id id пользователя
     * @return ответ от сервера
     */
    @POST("/api/friends/{id}")
    Call<Notice> addToFriends(@Path("id") int id);

    /**
     * Метод удаляет пользователя из друзей
     * @param id id пользователя
     * @return ответ от сервера
     */
    @DELETE("/api/friends/{id}")
    Call<Notice> deleteFromFriends(@Path("id") int id);
}
