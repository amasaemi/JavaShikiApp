package com.amasaemi.javashikiapp.data.services.interfaces;

import com.amasaemi.javashikiapp.data.managers.StaticAppManager;
import com.amasaemi.javashikiapp.utils.ConstantManager;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alex on 01.02.2018.
 */

public abstract class BaseNetworkService {
    private final String USER_AGENT = "User-Agent";
    private final String HEADER_AUTH = "Authorization";

    protected final String SHIKI_BASE = "https://shikimori.org/";
    protected final String HARUHARU_BASE = "http://haru-haru.paketrny.beget.tech/";

    // json парсер
    protected Gson mGson = new Gson();
    // httpClient с заголовками и логгированием
    protected final OkHttpClient mHttpClient = new OkHttpClient.Builder()
            .addInterceptor((chain) -> {
                if (StaticAppManager.getInstance().authTokenHasAvailable()) {
                    return chain.proceed(chain.request().newBuilder()
                            .addHeader(USER_AGENT, ConstantManager.APP_NAME)
                            .addHeader(HEADER_AUTH, "Bearer " + StaticAppManager.getInstance().getUserAuthInfo().getAuthToken())
                            .build());
                } else {
                    return chain.proceed(chain.request().newBuilder()
                            .addHeader(USER_AGENT, ConstantManager.APP_NAME)
                            .build());
                }
            })
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build();

    protected <T> Callback<T> request(Success<T> success, Failure failure) {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        success.success(response.body());
                    else
                        failure.failure(new Throwable("Response body has null"));
                } else {
                    failure.failure(new Throwable(String.format("Error. Response code: %d, %s", response.code(), response.message())));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                failure.failure(t);
            }
        };
    }

    public interface Success<T> {
        void success(T body);
    }

    public interface Failure {
        void failure(Throwable throwable);
    }
}
