package com.amasaemi.javashikiapp.data.services;

import com.amasaemi.javashikiapp.data.network.api.MangaApi;
import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
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

public class MangaService extends BaseNetworkService {
    private MangaApi mMangaApi = new Retrofit.Builder()
            .baseUrl(SHIKI_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mHttpClient).build().create(MangaApi.class);

    /**
     * GET запрос /api/mangas
     * @page - страница, целое число
     * @limit - количество элементов на странице (по умолчанию - 16)
     * @order - сортировка, возможно null
     * @kind - тип манги, возможно null
     * @status - статус аниме, возможно null
     * @season - манга сезон, возможно null
     * @genre - жанры, возможно null
     * @myList - статус манги в моем списке, возможно null
     */
    public void getMangaList(int page, SearchingParams params, Success<List<TitleListItemResponse>> success, Failure failure) {
        mMangaApi.getMangaList(page, params.getOrder(), params.getKinds(), params.getStatuses(),
                params.getSeasons(), params.getGenres(), params.getMyLists(), params.getSearch())
                    .enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/mangas/:id
     * @param mangaId - id манги
     */
    public void getSelectedManga(int mangaId, Success<TitleInfoResponse> success, Failure failure) {
        mMangaApi.getSelectedManga(mangaId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/mangas/{id}/external_links
     * @param mangaId - id манги
     */
    public void getExternalLinks(int mangaId, Success<List<TitleInfoResponse.ExternalLinksResponse>> success, Failure failure) {
        mMangaApi.getExternalLinks(mangaId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/mangas/{id}/status (список связанных манг)
     * @param mangaId - id манги
     */
    public void getRelated(int mangaId, Success<List<TitleInfoResponse.RelatedResponse>> success, Failure failure) {
        mMangaApi.getRelated(mangaId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/animes/{id}/similar (список похожих манг)
     * @param mangaId - id манги
     */
    public void getSimilar(int mangaId, Success<List<TitleListItemResponse>> success, Failure failure) {
        mMangaApi.getSimilar(mangaId).enqueue(request(success, failure));
    }

    /**
     * GET запрос /api/mangas/{id}/roles
     * @param mangaId - id манги
     */
    public void getRoles(int mangaId, Success<List<TitleInfoResponse.RolesResponse>> success, Failure failure) {
        mMangaApi.getRoles(mangaId).enqueue(request(success, failure));
    }
}
