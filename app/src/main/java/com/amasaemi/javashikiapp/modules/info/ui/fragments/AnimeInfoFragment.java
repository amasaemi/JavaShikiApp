package com.amasaemi.javashikiapp.modules.info.ui.fragments;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SubMenu;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.ActivityManager;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.modules.info.mvp.presenters.AnimeInfoPresenter;
import com.amasaemi.javashikiapp.modules.info.mvp.presenters.ShikiTitleInfoPresenter;
import com.amasaemi.javashikiapp.utils.ConstantManager;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

public class AnimeInfoFragment extends BaseInfoFragment {
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

        mMenu = new PopupMenu(getActivity(), mBinding.menuButton);
        mMenu.inflate(R.menu.menu_info);
        // слушатель нажатий кнопок в меню
        mMenu.setOnMenuItemClickListener((menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_title_share:
                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_VIEW);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.combine_field_share_anime,
                                (mTitleModel.ruName != null) ? mTitleModel.ruName : mTitleModel.enName, mTitleModel.getUrl()));
                        startActivity(Intent.createChooser(shareIntent, getString(R.string.label_share)));
                    } catch (ActivityNotFoundException anf) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle(R.string.status_error)
                                .setMessage(R.string.error_activity_not_found)
                                .setPositiveButton(R.string.label_close, (di, i) -> di.dismiss())
                                .create().show();
                    }
                    break;

                case R.id.menu_title_browser:
                    ActivityManager.openChromeTabPage(getActivity(), mTitleModel.getUrl());
                    break;
            }

            return true;
        }));
        // слушатель нажатия по кнопке меню
        mBinding.menuButton.setOnClickListener((btn) -> mMenu.show());
    }

    @Override
    public void setTitleInfo(TitleInfoResponse response) {
        super.setTitleInfo(response);
        // подсказки при клике на "возрастной рейтинг"
        mBinding.ratingField.setOnClickListener((btn) -> {
            Toast.makeText(btn.getContext(), response.getRating().getRatingHint(btn.getContext()), Toast.LENGTH_SHORT).show(); });
    }

    @Override
    public final void setExternalLinksMenu(List<TitleInfoResponse.ExternalLinksResponse> response) {
        new Thread(() -> {
            // getItem(2), где 2 - порядковый номер элемента "Другие источники"
            SubMenu subMenu = mMenu.getMenu().getItem(2).getSubMenu();

            for (TitleInfoResponse.ExternalLinksResponse link : response) {
                switch (link.getKind()) {
                    case ConstantManager.ExternalLinks.OFFICIAL_SITE:
                        subMenu.add(getResources().getStringArray(R.array.anime_external_links)[0])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.WIKIPEDIA:
                        subMenu.add(getResources().getStringArray(R.array.anime_external_links)[1])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.ANN:
                        subMenu.add(getResources().getStringArray(R.array.anime_external_links)[2])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.ANIME_DB:
                        subMenu.add(getResources().getStringArray(R.array.anime_external_links)[3])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.MAL:
                        subMenu.add(getResources().getStringArray(R.array.anime_external_links)[4])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.WORLD_ART:
                        subMenu.add(getResources().getStringArray(R.array.anime_external_links)[5])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.KAGE_PROJECT:
                        subMenu.add(getResources().getStringArray(R.array.anime_external_links)[6])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;
                }
            }
        }).run();
    }
}