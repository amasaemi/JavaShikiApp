package com.amasaemi.javashikiapp.app;

import android.app.Application;

/**
 * Created by Alex on 31.01.2018.
 */

public class ShikiApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: 31.01.2018 Загружаем настройки
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        // TODO: 31.01.2018 Обрабатываем малое количество памяти
    }
}
