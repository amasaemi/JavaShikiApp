package com.amasaemi.javashikiapp.modules.info.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.SimpleRecyclerAdapter;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.ShikiPresenter;
import com.amasaemi.javashikiapp.modules.base.mvp.views.ShikiListView;
import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseListFragment;
import com.amasaemi.javashikiapp.modules.info.mvp.presenters.RelatedInfoPresenter;
import com.amasaemi.javashikiapp.modules.info.ui.activities.TitleInfoActivity;
import com.amasaemi.javashikiapp.modules.list.ui.models.ListCardModel;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.LinkedList;
import java.util.List;

public final class RelatedInfoFragment extends BaseListFragment implements ShikiListView<TitleInfoResponse.RelatedResponse> {
    public static final String TAG = RelatedInfoFragment.class.getSimpleName();

    public static RelatedInfoFragment getInstance(Bundle bundle) {
        RelatedInfoFragment fragment = new RelatedInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    // id тайтла, с которым работаем
    private int mTitleId = -1;
    // titleType тайтла, с которым работаем
    private TitleType mTitleType = TitleType.NONE;

    @InjectPresenter
    RelatedInfoPresenter mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // получаем id тайтла из интента
        mTitleId = getArguments().getInt(TitleInfoActivity.TITLE_ID, -1);
        // получаем titleType тайтла из интента
        mTitleType = TitleType.valueOf(getArguments().getString(TitleInfoActivity.TITLE_TYPE, TitleType.NONE.name()));
        // инициализируем презентер
        mPresenter.initPresenter(mTitleId, mTitleType);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setupRecyclerView() {
        // устанавливаем менеджер recyclerView
        setupRecyclerViewLayoutManager(null);
        int itemLayout = listStyleIsShort() ? R.layout.card_simple_short : R.layout.card_list_item_long;
        // назначаем адаптер
        createRecyclerViewAdapter(new SimpleRecyclerAdapter<ListCardModel>(itemLayout));
    }

    @Override
    protected ShikiPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void fillAdapter(List<TitleInfoResponse.RelatedResponse> list) {
        new Thread(() -> {
            // FIXME: 06.04.2018 при пересоздании фрагмента (поворот экрана) getAdapter == null
//            if (mBinding.container.getAdapter() == null)
//                setupRecyclerView();
            SimpleRecyclerAdapter<ListCardModel> adapter = (SimpleRecyclerAdapter<ListCardModel>) mBinding.container.getAdapter();
            adapter.addItems(mapTo(list));

            stateVisibilityContainer(!adapter.isEmpty());
        }).run();
    }

    @Override
    public void clearAdapter() {
        ((SimpleRecyclerAdapter) mBinding.container.getAdapter()).clearItems();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    /**
     * Метод приводит элементы list к ListCardModel
     * @param list - входной список
     * @return
     */
    // TODO: 05.04.2018 заменить на relatedCardModel
    private List<ListCardModel> mapTo(List<TitleInfoResponse.RelatedResponse> list) {
        List<ListCardModel> mapResult = new LinkedList<>();

        for (TitleInfoResponse.RelatedResponse i : list)
            mapResult.add(new ListCardModel(getActivity(), i));

        return mapResult;
    }
}
