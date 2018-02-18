package com.amasaemi.javashikiapp.data.services;

import android.os.Build;

import com.amasaemi.javashikiapp.data.managers.StaticAppManager;
import com.amasaemi.javashikiapp.data.network.api.StatisticApi;
import com.amasaemi.javashikiapp.data.services.interfaces.BaseNetworkService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 04.02.2018.
 */

public class StatisticManager extends BaseNetworkService {
    public static final String INPUT_IN_APP = "input in app";

    private StatisticApi mStatisticApi = new Retrofit.Builder()
            .baseUrl(HARUHARU_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mHttpClient).build().create(StatisticApi.class);

    public void commitEvent(String event) {
        mStatisticApi.commitEvent("s3da79sj6ip9", StaticAppManager.getInstance().getUserLogin(),
                String.format("%s, %s, %s", Build.MANUFACTURER, Build.MODEL, Build.VERSION.CODENAME), event,
                new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date()));
    }
}
