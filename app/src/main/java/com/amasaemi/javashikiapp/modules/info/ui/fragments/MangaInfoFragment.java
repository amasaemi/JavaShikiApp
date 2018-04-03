package com.amasaemi.javashikiapp.modules.info.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.modules.info.mvp.presenters.MangaInfoPresenter;
import com.amasaemi.javashikiapp.modules.info.mvp.presenters.ShikiTitleInfoPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;

public final class MangaInfoFragment extends BaseInfoFragment {
    public static final String TAG = MangaInfoFragment.class.getSimpleName();

    public static MangaInfoFragment getInstance(Bundle bundle) {
        MangaInfoFragment fragment = new MangaInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @InjectPresenter
    MangaInfoPresenter mPresenter;

    @Override
    protected ShikiTitleInfoPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // инициализируем меню в toolbar
        initMenu(R.string.combine_field_share_manga);
    }
}
