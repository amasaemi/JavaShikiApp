package com.amasaemi.javashikiapp.modules.base.binding;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.StaticAppManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Alex on 04.02.2018.
 */

public class ShikiDataBindingAdapters {
    @BindingAdapter("android:onClick")
    public static void click(View view, Runnable runnable) {
        view.setOnClickListener((v) -> runnable.run());
    }

    @BindingAdapter("roundImg")
    public static void roundImg(ImageView view, Uri url) {
        Glide.with(view.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .circleCrop()
                        .diskCacheStrategy(StaticAppManager.getInstance().getUserSettings().getDiscCacheStrategy())
                        .skipMemoryCache(StaticAppManager.getInstance().getUserSettings().hasSkipMemoryCacheStrategy()))
                .into(view);
    }

    @BindingAdapter("android:src")
    public static void src(ImageView view, Uri url) {
        Glide.with(view.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(StaticAppManager.getInstance().getUserSettings().getDiscCacheStrategy())
                        .skipMemoryCache(StaticAppManager.getInstance().getUserSettings().hasSkipMemoryCacheStrategy())
                        .error(R.drawable.img_load_failed))
                .into(view);
    }

    @BindingAdapter("parselableText")
    public static void parselableText(TextView view, String text) {
        // TODO: 26.03.2018 пропарсить avesomeText'ом
    }
}
