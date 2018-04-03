package com.amasaemi.javashikiapp.modules.info.ui.fragments;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.SubMenu;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.ActivityManager;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Rating;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.modules.info.mvp.presenters.AnimeInfoPresenter;
import com.amasaemi.javashikiapp.modules.info.mvp.presenters.ShikiTitleInfoPresenter;
import com.amasaemi.javashikiapp.utils.ConstantManager;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

public final class AnimeInfoFragment extends BaseInfoFragment {
    public static final String TAG = AnimeInfoFragment.class.getSimpleName();

    public static AnimeInfoFragment getInstance(Bundle bundle) {
        AnimeInfoFragment fragment = new AnimeInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @InjectPresenter
    AnimeInfoPresenter mPresenter;

    @Override
    protected ShikiTitleInfoPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // инициализируем меню в toolbar
        initMenu(R.string.combine_field_share_anime);
    }

    @Override
    public void setTitleInfo(TitleInfoResponse response) {
        super.setTitleInfo(response);
        // подсказки при клике на "возрастной рейтинг"
        if (response.getRating() != null && response.getRating() != Rating.NONE)
            mBinding.ratingField.setOnClickListener((btn) -> Snackbar.make(mBinding.container,
                    response.getRating().getRatingHint(btn.getContext()), Snackbar.LENGTH_SHORT).show());
        // подробная информация о выходе следующего эпизода
        if (mTitleModel.nextEpisode != null)
            mBinding.airtimeField.setOnClickListener((btn) -> Snackbar.make(mBinding.container,
                    btn.getContext().getString(R.string.combine_field_next_ep_date, mTitleModel.nextEpisode),
                    Snackbar.LENGTH_SHORT).show());
    }
}