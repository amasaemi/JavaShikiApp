package com.amasaemi.javashikiapp.modules.info.ui.fragments;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.ActivityManager;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.databinding.FragmentInfoBinding;
import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseFragment;
import com.amasaemi.javashikiapp.modules.info.mvp.presenters.ShikiTitleInfoPresenter;
import com.amasaemi.javashikiapp.modules.info.ui.activities.TitleInfoActivity;
import com.amasaemi.javashikiapp.modules.info.ui.models.TitleInfoModel;
import com.amasaemi.javashikiapp.modules.info.mvp.views.ShikiInfoView;
import com.amasaemi.javashikiapp.modules.info.ui.dialogs.RateDialog;
import com.amasaemi.javashikiapp.utils.ConstantManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.List;

/**
 * Created by Alex on 14.03.2018.
 */

public abstract class BaseInfoFragment extends BaseFragment implements ShikiInfoView {
    protected FragmentInfoBinding mBinding;
    // id тайтла, с которым работаем
    private int mTitleId = -1;
    // модель текущего тайтла
    protected TitleInfoModel mTitleModel;
    // кастомное всплывающее меню
    protected PopupMenu mMenu;
    // диалог с настройками тайтла в списке пользователя
    private RateDialog mRateDialog;

    /**
     * Метод возвращает презентер
     */
    protected abstract ShikiTitleInfoPresenter getPresenter();

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // слушатель кнопки "назад"
        mBinding.backButton.setOnClickListener((btn) -> getActivity().onBackPressed());
        // слушатель схлопывания collapsingtoolbar
        mBinding.appbar.addOnOffsetChangedListener((layout, offset) -> {
            float alpha = 1 - ((float)(layout.getTotalScrollRange() + offset)) / 100;
            mBinding.headerToolbar.setAlpha(alpha);
        });
        // получаем id тайтла из интента
        mTitleId = getArguments().getInt(TitleInfoActivity.TITLE_ID, -1);
        // загружаем данные либо восстанавливаем их
        if (savedInstanceState == null) {
            getPresenter().getTitleById(mTitleId);
        } else {
            getPresenter().loadOrRestoreData();
        }
    }

    @Override
    public void setTitleInfo(TitleInfoResponse response) {
        new Thread(() -> {
            mTitleModel = new TitleInfoModel(getActivity(), response);
            // устанавливаем модель для view
            mBinding.setModel(mTitleModel);
            // если описание не пустое, включаем кнопку "показать описание полностью"
            mBinding.showAllButton.setVisibility(mTitleModel.emptyDescription ? View.GONE : View.VISIBLE);
            // назначаем слушатель на кнопку "показать описание полностью"
            mBinding.showAllButton.setOnClickListener((view) -> {
                mBinding.descriptionTextView.setMaxLines(500);
                view.setVisibility(View.GONE);
            });
            // добавляем слушатель на нажатию по сезону
            initSeasonClick();

            // настраиваем RateDialog
            // TODO: 29.03.2018 настроить ratedialog

            stateVisibilityContainer(true);
        }).run();
    }

    @Override
    public final void setVideosMenu(List<TitleInfoResponse.VideoResponse> response) {
        new Thread(() -> {
            // TODO: 25.03.2018
        }).run();
    }

    @Override
    public final void setRelatedMenu(List<TitleInfoResponse.RelatedResponse> response) {
        new Thread(() -> {
            // TODO: 25.03.2018
        }).run();
    }

    @Override
    public final void setSimilarMenu(List<TitleListItemResponse> response) {
        new Thread(() -> {
            // TODO: 25.03.2018
        }).run();
    }

    @Override
    public final void setExternalLinksMenu(List<TitleInfoResponse.ExternalLinksResponse> response) {
        new Thread(() -> {
            // getItem(2), где 2 - порядковый номер элемента "Другие источники"
            SubMenu subMenu = mMenu.getMenu().getItem(2).getSubMenu();

            for (TitleInfoResponse.ExternalLinksResponse link : response) {
                switch (link.getKind()) {
                    case ConstantManager.ExternalLinks.OFFICIAL_SITE:
                        subMenu.add(getResources().getStringArray(R.array.external_links)[0])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.WIKIPEDIA:
                        subMenu.add(getResources().getStringArray(R.array.external_links)[1])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.ANN:
                        subMenu.add(getResources().getStringArray(R.array.external_links)[2])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.ANIME_DB:
                        subMenu.add(getResources().getStringArray(R.array.external_links)[3])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.MAL:
                        subMenu.add(getResources().getStringArray(R.array.external_links)[4])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.WORLD_ART:
                        subMenu.add(getResources().getStringArray(R.array.external_links)[5])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.KAGE_PROJECT:
                        subMenu.add(getResources().getStringArray(R.array.external_links)[6])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.READMANGA:
                        subMenu.add(getResources().getStringArray(R.array.external_links)[7])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;

                    case ConstantManager.ExternalLinks.RURANOBE:
                        subMenu.add(getResources().getStringArray(R.array.external_links)[8])
                                .setOnMenuItemClickListener((menuItem) -> {
                                    ActivityManager.openChromeTabPage(getActivity(), Uri.parse(link.getUrl()));
                                    return true;
                                });
                        break;
                }
            }
        }).run();
    }

    @Override
    public final void setScreenshotsMenu(List<TitleInfoResponse.ScreenhostResponse> response) {
        new Thread(() -> {
            mBinding.poster.setOnClickListener((view) -> {
                view.setEnabled(false);

                Fresco.initialize(view.getContext());
                new ImageViewer.Builder(view.getContext(), response)
                        .setStartPosition(0)
                        // TODO: 29.03.2018 добавить функционал "поделиться изображением"
                        //.setOverlayView()
                        .setOnDismissListener(() -> {
                            Fresco.shutDown();
                            view.setEnabled(true);
                        })
                        .setFormatter((screenshots) -> ((TitleInfoResponse.ScreenhostResponse) screenshots).getOriginal())
                        .show();
            });
        }).run();
    }

    @Override
    public final void stateLoadIndicator(Boolean state) {
        mBinding.loadIndicator.setVisibility(state ? View.VISIBLE : View.GONE);
    }

    @Override
    public final void stateVisibilityContainer(Boolean state) {
        stateLoadIndicator(false);

        mBinding.container.setVisibility(state ? View.VISIBLE : View.GONE);
        // TODO: 25.03.2018 добавить emptyView
    }

    @Override
    public final void showRetrySnackbar(Runnable action) {
        // TODO: 29.03.2018 исправить надписи
        Snackbar.make(mBinding.getRoot(), R.string.error_retry, Snackbar.LENGTH_SHORT)
                .setAction(R.string.label_retry, (view) -> action.run())
                .show();
    }

    protected void initMenu(int shareStringId) {
        mMenu = new PopupMenu(getActivity(), mBinding.menuButton);
        mMenu.inflate(R.menu.menu_info);
        // слушатель нажатий кнопок в меню
        mMenu.setOnMenuItemClickListener((menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_title_share:
                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_VIEW);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(shareStringId,
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

    protected void initSeasonClick() {
        // слушатель на нажатии на сезон
        if (mTitleModel.hasReleased || mTitleModel.hasOngoing) {
            String mess;

            switch (mTitleModel.titleType) {
                case ANIME:
                    mess = (mTitleModel.hasOngoing) ? getString(R.string.combine_field_anime_ongoing_airtime, mTitleModel.airedStart, mTitleModel.airedFinish)
                            : getString(R.string.combine_field_anime_released_airtime, mTitleModel.airedStart, mTitleModel.airedFinish);
                    break;

                case MANGA:
                    mess = (mTitleModel.hasOngoing) ? getString(R.string.combine_field_manga_ongoing_airtime, mTitleModel.airedStart, mTitleModel.airedFinish)
                            : getString(R.string.combine_field_manga_released_airtime, mTitleModel.airedStart, mTitleModel.airedFinish);
                    break;

                case RANOBE:
                    mess = (mTitleModel.hasOngoing) ? getString(R.string.combine_field_ranobe_ongoing_airtime, mTitleModel.airedStart, mTitleModel.airedFinish)
                            : getString(R.string.combine_field_ranobe_released_airtime, mTitleModel.airedStart, mTitleModel.airedFinish);
                    break;

                default:
                    throw new NullPointerException(mTitleModel.titleType.toString() + " has bad TitleType");
            }

            mBinding.seasonField.setOnClickListener((btn) -> Snackbar.make(mBinding.container,
                    mess, Snackbar.LENGTH_SHORT).show());
        }
    }
}
