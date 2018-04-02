package com.amasaemi.javashikiapp.modules.info.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.databinding.FragmentInfoBinding;
import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseFragment;
import com.amasaemi.javashikiapp.modules.info.mvp.presenters.ShikiTitleInfoPresenter;
import com.amasaemi.javashikiapp.modules.info.ui.activities.TitleInfoActivity;
import com.amasaemi.javashikiapp.modules.info.ui.models.TitleInfoModel;
import com.amasaemi.javashikiapp.modules.info.mvp.views.ShikiInfoView;
import com.amasaemi.javashikiapp.modules.info.ui.dialogs.RateDialog;
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
            // слушатель на нажатии на сезон
            mBinding.seasonField.setOnClickListener((btn) -> Toast.makeText(btn.getContext(),
                    btn.getContext().getString(R.string.combine_field_airtime, mTitleModel.airedStart, mTitleModel.airedFinish),
                    Toast.LENGTH_SHORT).show());
            // подробная информация о выходе следующего эпизода
            if (mTitleModel.nextEpisode != null)
                mBinding.airtimeField.setOnClickListener((btn) -> Toast.makeText(btn.getContext(),
                        btn.getContext().getString(R.string.combine_field_next_ep_date, mTitleModel.nextEpisode),
                        Toast.LENGTH_SHORT).show());
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
}
