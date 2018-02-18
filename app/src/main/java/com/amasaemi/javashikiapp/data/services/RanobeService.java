package com.amasaemi.javashikiapp.data.services;

import com.amasaemi.javashikiapp.data.network.api.RanobeApi;
import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.data.services.interfaces.BaseNetworkService;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 01.02.2018.
 */

public class RanobeService extends BaseNetworkService {
    private RanobeApi mRanobeApi = new Retrofit.Builder()
            .baseUrl(SHIKI_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mHttpClient).build().create(RanobeApi.class);

    /**
     * GET запрос /api/ranobes
     * @page - страница, целое число
     * @limit - количество элементов на странице (по умолчанию - 15)
     * @order - сортировка, возможно null
     * @status - статус аниме, возможно null
     * @season - манга сезон, возможно null
     * @genre - жанры, возможно null
     * @myList - статус манги в моем списке, возможно null
     */
    public void getRanobeList(int page, SearchingParams params, Success<List<TitleListItemResponse>> success, Failure failure) {
        mRanobeApi.getRanobeList(page, params.getOrder(), params.getStatuses(),
                params.getSeasons(), params.getGenres(), params.getMyLists(), params.getSearch())
                    .enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/ranobes/:id
     * @param ranobeId - ид ранобэ
     */
    public void getSelectedRanobe(int ranobeId, Success<TitleInfoResponse> success, Failure failure) {
        mRanobeApi.getSelectedRanobe(ranobeId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/ranobes/{id}/external_links
     * @param ranobeId - id ранобэ
     */
    public void getExternalLinks(int ranobeId, Success<List<TitleInfoResponse.ExternalLinksResponse>> success, Failure failure) {
        mRanobeApi.getExternalLinks(ranobeId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/ranobes/{id}/status (список связанных ранобэ)
     * @param ranobeId - id ранобэ
     */
    public void getRelated(int ranobeId, Success<List<TitleInfoResponse.RelatedResponse>> success, Failure failure) {
        mRanobeApi.getRelated(ranobeId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/animes/{id}/similar (список похожих манг)
     * @param ranobeId - id ранобэ
     */
    public void getSimilar(int ranobeId, Success<List<TitleListItemResponse>> success, Failure failure) {
        mRanobeApi.getSimilar(ranobeId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/ranobes/{id}/roles
     * @param ranobeId - id ранобэ
     */
    public void getRoles(int ranobeId, Success<List<TitleInfoResponse.RolesResponse>> success, Failure failure) {
        mRanobeApi.getRoles(ranobeId).enqueue(request(success, failure));
    }
}
