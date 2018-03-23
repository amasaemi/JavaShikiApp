package com.amasaemi.javashikiapp.data.services;

import com.amasaemi.javashikiapp.data.network.api.AuthApi;
import com.amasaemi.javashikiapp.data.network.pojo.res.AuthResponse;
import com.amasaemi.javashikiapp.data.services.interfaces.BaseNetworkService;
import com.amasaemi.javashikiapp.utils.ConstantManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 11.03.2018.
 */

public class AuthService extends BaseNetworkService {
    private AuthApi mAuthApi = new Retrofit.Builder()
            .baseUrl(SHIKI_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mHttpClient).build().create(AuthApi.class);

    /**
     * POST запрос /oauth/token
     */
    public void getAccessToken(String accessCode, Success<AuthResponse> success, Failure failure) {
        mAuthApi.getAccessToken(ConstantManager.GrantType.AUTH_TOKEN, ConstantManager.SHIKIMORI_APP_CODE,
                ConstantManager.APP_SECRET, accessCode, null, ConstantManager.SHIKI_AUTH_REDIRECT_URI)
        .enqueue(request(success, failure));
    }

    /**
     * POST запрос /oauth/token
     */
    public void refreshToken(String refrehsToken, Success<AuthResponse> success, Failure failure) {
        mAuthApi.getAccessToken(ConstantManager.GrantType.REFRESH_TOKEN, ConstantManager.SHIKIMORI_APP_CODE,
                ConstantManager.APP_SECRET, null, refrehsToken, null).enqueue(request(success, failure));;
    }
}
