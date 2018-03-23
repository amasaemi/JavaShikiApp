package com.amasaemi.javashikiapp.data.network.api;

import com.amasaemi.javashikiapp.data.network.pojo.res.AuthResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Alex on 11.03.2018.
 */

public interface AuthApi {
    /**
     * Метод вернет объект с токеном доступа пользователя
     * @param grantType тип запроса - получение / обновление токена
     * @param clientId id клиентского приложения
     * @param clientSecret код клиентского приложения
     * @param code код авторизации, если @grantType - authorization_code
     * @param redirectUri адрес переадресации
     * @return
     */
    @POST("/oauth/token")
    Call<AuthResponse> getAccessToken(@Query("grant_type") String grantType,
                                      @Query("client_id") String clientId,
                                      @Query("client_secret") String clientSecret,
                                      @Query("code") String code,
                                      @Query("refresh_token") String refreshToken,
                                      @Query("redirect_uri") String redirectUri);
}
