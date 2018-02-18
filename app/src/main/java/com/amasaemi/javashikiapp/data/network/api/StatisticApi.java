package com.amasaemi.javashikiapp.data.network.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alex on 04.02.2018.
 */

public interface StatisticApi {
    @GET("/event.php")
    Call<Void> commitEvent(@Query("key") String key,
                           @Query("username") String username,
                           @Query("device") String device,
                           @Query("action") String action,
                           @Query("time") String date);
}
