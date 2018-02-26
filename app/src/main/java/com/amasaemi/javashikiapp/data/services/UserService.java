package com.amasaemi.javashikiapp.data.services;

import com.amasaemi.javashikiapp.data.network.api.UsersApi;
import com.amasaemi.javashikiapp.data.network.pojo.constants.MyList;
import com.amasaemi.javashikiapp.data.network.pojo.res.ClubsListResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.FavoriteResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.HistoryResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.MyListRatesResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserListResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserResponse;
import com.amasaemi.javashikiapp.data.network.pojo.sup.Notice;
import com.amasaemi.javashikiapp.data.services.interfaces.BaseNetworkService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 25.02.2018.
 */

public class UserService extends BaseNetworkService {
    private UsersApi mUsersApi = new Retrofit.Builder()
            .baseUrl(SHIKI_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mHttpClient).build().create(UsersApi.class);

    /**
     * GET запрос /api/users/whoami
     */
    public void getCurrentUser(Success<UserInfoResponse> success, Failure failure) {
        mUsersApi.getCurrentUser().enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/users/{id}
     * @param id ид пользователя
     */
    public void getUserInfo(int id, Success<UserResponse> success, Failure failure) {
        mUsersApi.getUserInfo(id).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/users/{id}/history
     * @param id ид пользователя
     * @param page страница поиска
     */
    public void getUserHistory(int id, int page, Success<List<HistoryResponse>> success, Failure failure) {
        mUsersApi.getHistory(id, page).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/users/{id}/favorites
     * @param id ид пользователя
     */
    public void getUserFavorites(int id, Success<FavoriteResponse> success, Failure failure) {
        mUsersApi.getFavorites(id).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/users/{id}/anime_rates
     * @param id ид пользователя
     * @param status статус в моем списке
     */
    public void getAnimeRates(int id, int page, MyList status, Success<List<MyListRatesResponse>> success, Failure failure) {
        mUsersApi.getAnimeRates(id, page, status).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/users/{id}/manga_rates
     * @param id ид пользователя
     * @param status статус в моем списке
     */
    public void getMangaRates(int id, int page, MyList status, Success<List<MyListRatesResponse>> success, Failure failure) {
        mUsersApi.getMangaRates(id, page, status).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/users
     * @param page страница поиска
     * @param query поисковый запрос
     */
    public void getUserList(int page, String query, Success<List<UserListResponse>> success, Failure failure) {
        mUsersApi.getUsersList(page, query).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/users/{id}/friends
     * @param id ид пользователя
     */
    public void getUserFriends(int id, Success<List<UserListResponse>> success, Failure failure) {
        mUsersApi.getUserFriends(id).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/users/{id}/clubs
     * @param id ид пользователя
     */
    public void getUserMembership(int id, Success<List<ClubsListResponse>> success, Failure failure) {
        mUsersApi.getUserMembership(id).enqueue(request(success, failure));
    }

    /**
     * POST запрос /api/friends/{id}
     * @param id ид пользователя
     */
    public void addToFriends(int id, Success<Notice> success, Failure failure) {
        mUsersApi.addToFriends(id).enqueue(request(success, failure));
    }

    /**
     * DELETE запрос /api/friends/{id}
     * @param id ид пользователя
     */
    public void deleteFromFriends(int id, Success<Notice> success, Failure failure) {
        mUsersApi.deleteFromFriends(id).enqueue(request(success, failure));
    }
}