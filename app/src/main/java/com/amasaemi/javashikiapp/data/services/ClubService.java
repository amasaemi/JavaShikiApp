package com.amasaemi.javashikiapp.data.services;

import com.amasaemi.javashikiapp.data.network.api.ClubsApi;
import com.amasaemi.javashikiapp.data.network.pojo.res.ClubsListResponse;
import com.amasaemi.javashikiapp.data.services.interfaces.BaseNetworkService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 25.02.2018.
 */

public class ClubService extends BaseNetworkService {
    private ClubsApi mClubsApi = new Retrofit.Builder()
            .baseUrl(SHIKI_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mHttpClient).build().create(ClubsApi.class);

    /**
     * GET запрос /api/clubs
     * @param page страница поиска
     * @param query поисковый запрос, может быть null
     */
    public void getClubsList(int page, String query, Success<List<ClubsListResponse>> success, Failure failure) {
        mClubsApi.getClubsList(page, query).enqueue(request(success, failure));
    }
}
