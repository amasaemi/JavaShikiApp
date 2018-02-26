package com.amasaemi.javashikiapp.modules.base.ui.fragments;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.PreferencesManager;
import com.amasaemi.javashikiapp.data.managers.StaticAppManager;
import com.amasaemi.javashikiapp.databinding.FragmentBaseListBinding;
import com.amasaemi.javashikiapp.modules.base.adapters.DoubleRecyclerAdapter;
import com.amasaemi.javashikiapp.modules.base.adapters.SimpleRecyclerAdapter;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.ShikiPresenter;

/**
 * Created by Alex on 31.01.2018.
 */

public abstract class BaseListFragment extends BaseFragment {
    private final String RECYCLER_VIEW_POS = "recyclerpos";
    private final String SCROLL_LISTENER_ITEM_COUNT = "slitemcount";
    private final String SCROLL_LISTENER_LOADING = "slloading";

    protected FragmentBaseListBinding mBinding;
    // LayoutManager для RecyclerView
    private RecyclerView.LayoutManager mLayoutManager;
    // сохраняем последнюю позицию recyclerview
    private Parcelable mListPos;
    // храним стиль текущего элемента адаптера
    private boolean mShortListStyleNow = listStyleIsShort();
    // предыдущее количество элементов списка
    // вынесено отдельно, дабы можно было сохранить в saveInstanceState
    private int mScrollListenerPreviousItemCount = 0;
    // маркер доступности загрузки
    // вынесено отдельно, дабы можно было сохранить в saveInstanceState
    private boolean mScrollListenerLoading = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // размер дочерних view известен и фиксирован
        mBinding.container.setHasFixedSize(true);
        // фиксированный размер кэша
        mBinding.container.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        // количество элементов, которые хранятся в памяти без пересоздания
        mBinding.container.setItemViewCacheSize(100);
        // создаем адаптер
        setupRecyclerView();
        // настраиваем цветовую схему у swiperefresh
        mBinding.loadIndicator.setColorScheme(
                R.color.refresh_1,
                R.color.refresh_2,
                R.color.refresh_3,
                R.color.refresh_4
        );
        // ставим маркер о том, что фрагмент создан и будет запущен впервый раз
        mFragmentIsFirstRun = true;
        // если текущий фрагмент виден - инициализируем его
        if (getUserVisibleHint())
            initialFragment();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // если текущий фрагмент виден и не проинициализирован - инициализируем его
        if (isVisibleToUser && getView() != null)
            initialFragment();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        // проверяем, пересоздан ли фрагмент
        // и если да, то восстанавливаем информацию по позиции recyclerview,
        // предыдущее значение количества позиций в адаптере RecyclerView и маркер загрузки
        if (savedInstanceState != null) {
            mListPos = savedInstanceState.getParcelable(RECYCLER_VIEW_POS);
            if (mListPos != null)
                mLayoutManager.onRestoreInstanceState(mListPos);

            mScrollListenerPreviousItemCount = savedInstanceState.getInt(SCROLL_LISTENER_ITEM_COUNT, 0);
            mScrollListenerLoading = savedInstanceState.getBoolean(SCROLL_LISTENER_LOADING, false);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mBinding != null) {
            // сохраняем предыдущее значение количества позиций в адаптере RecyclerView
            outState.putInt(SCROLL_LISTENER_ITEM_COUNT, mScrollListenerPreviousItemCount);
            // сохраняем маркер загрузки
            outState.putBoolean(SCROLL_LISTENER_LOADING, mScrollListenerLoading);
            // сохраняем позицию recyclerview
            outState.putParcelable(RECYCLER_VIEW_POS, mLayoutManager.onSaveInstanceState());
        }
    }

    /**
     * Метод настраивает действие при достижении recyclerview'ом конца списка
     * @param endlessAction - действие, совершаемое при достижении конца списка
     * подгрузки следующего списка
     */
    protected final void serRecyclerEndlessListener(Runnable endlessAction) {
        mBinding.container.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int totalItemCount = 0;
            int lastVisibleItem = 0;
            int visibleThreshold = 4;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = recyclerView.getLayoutManager().getItemCount();

                if (totalItemCount > 0) {
                    if (recyclerView.getLayoutManager() instanceof LinearLayoutManager)
                        lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                    else if (recyclerView.getLayoutManager() instanceof GridLayoutManager)
                        lastVisibleItem = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                    else // TODO: 31.01.2018 Включить поддержку третьего менеджера для списка новостей
                        throw new NullPointerException("Unsupported RecyclerView LayoutManager");

                    // по поводу (totalItemCount < scrollListenerPreviousItemCount)
                    // если мы меняем параметры поиска, то значения остаются те же в слушаетли,
                    // надо их сбросить
                    if (totalItemCount != mScrollListenerPreviousItemCount) {
                        mScrollListenerLoading = true;
                        mScrollListenerPreviousItemCount = totalItemCount;
                    }

                    // логика подгрузки списка
                    if (mScrollListenerLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                        endlessAction.run();

                        mScrollListenerLoading = false;
                    }
                }
            }
        });
    }

    /**
     * Метод пересоздает менеджер для recyclerview
     */
    protected final void changeRecyclerManager() {
        // сохраняем новое состояние списка
        new PreferencesManager(getActivity())
                .saveListStyle(!listStyleIsShort() ? SimpleRecyclerAdapter.STYLE_SHORT : SimpleRecyclerAdapter.STYLE_LONG);

        stateVisibilityContainer(false);
        mListPos = mLayoutManager.onSaveInstanceState();

        setupRecyclerViewLayoutManager(null);
        setupRecyclerView();

        // восстанавливаем список
        initialFragment();

        mLayoutManager.onRestoreInstanceState(mListPos);
        stateVisibilityContainer(true);
    }

    /**
     * Метод настраивает layoutManager для RecyclerView
     * [0] portShortPhone минимальное количество коротких карточек для телефона
     * [1] portShortFab минимальное количество коротких карточек для фаблета
     * [2] portShortTab минимальное количество коротких карточек для планшета
     * [3] portLongPhone минимальное количество длинных карточек для телефона
     * [4] portLongFab минимальное количество длинных карточек для фаблета
     * [5] portLongTab минимальное количество длинных карточек для планшета
     * [6] landShortPhone минимальное количество коротких карточек для телефона
     * [7] landShortFab минимальное количество коротких карточек для фаблета
     * [8] landShortTab минимальное количество коротких карточек для планшета
     * [9] landLongPhone минимальное количество длинных карточек для телефона
     * [10] landLongFab минимальное количество длинных карточек для фаблета
     * [11] landLongTab минимальное количество длинных карточек для планшета
     */
    protected void setupRecyclerViewLayoutManager(int[] cardCount) {
        if (cardCount == null)
            cardCount = new int[] { 2, 3, 4, 1, 1, 2, 4, 5, 6, 1, 2, 3 };

        if (getOrientation() == Configuration.ORIENTATION_PORTRAIT) {
            if (listStyleIsShort()) {
                if (getScreenSize() < 5.5)
                    mLayoutManager = getGridLayoutManager(cardCount[0]);
                else if (getScreenSize() < 7.7)
                    mLayoutManager = getGridLayoutManager(cardCount[1]);
                else
                    mLayoutManager = getGridLayoutManager(cardCount[2]);
            } else {
                if (getScreenSize() < 7.7)
                    mLayoutManager = new LinearLayoutManager(getActivity());
                else
                    mLayoutManager = getGridLayoutManager(cardCount[5]);
            }
        } else {
            if (listStyleIsShort()) {
                if (getScreenSize() < 6)
                    mLayoutManager = getGridLayoutManager(cardCount[6]);
                else if (getScreenSize() < 8)
                    mLayoutManager = getGridLayoutManager(cardCount[7]);
                else
                    mLayoutManager = getGridLayoutManager(cardCount[8]);
            } else {
                if (getScreenSize() < 5.5)
                    mLayoutManager = getGridLayoutManager(cardCount[9]);
                else if (getScreenSize() < 7.7)
                    mLayoutManager = getGridLayoutManager(cardCount[10]);
                else
                    mLayoutManager = getGridLayoutManager(cardCount[11]);
            }
        }
        // присваиваем новый менеджер для RecyclerView
        mBinding.container.setLayoutManager(mLayoutManager);
    }

    /**
     * Метод устанавливает HEADER'у списка количество позиций, которое он может занять
     */
    protected GridLayoutManager getGridLayoutManager(int spanCount) {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), spanCount);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (mBinding.container.getAdapter().getItemViewType(position) == DoubleRecyclerAdapter.HEADER) ? spanCount : 1;
            }
        });

        return manager;
    }

    /**
     * Метод настраивает recyclerView
     * подключает оконный менеджер и адаптер
     */
    protected abstract void setupRecyclerView();

    /**
     * Метод возвращает презентер конкретного фрагмента
     * @return
     */
    protected abstract ShikiPresenter getPresenter();

    /**
     * Метод назначает адаптер для recyclerView
     * @param adapter
     */
    protected void createRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mBinding.container.setAdapter(adapter);
    }

    /**
     * Метод инициализирует фрагмент в случае первого запуска
     */
    public void initialFragment() {
        if (getPresenter() != null && getPresenter().hasInitialized() && (mFragmentIsFirstRun || mShortListStyleNow != listStyleIsShort())) {
            getPresenter().loadOrRestoreData();
            mFragmentIsFirstRun = false;
            mShortListStyleNow = listStyleIsShort();
        }
    }

    protected boolean listStyleIsShort() {
        return StaticAppManager.getInstance().getListStyle() == SimpleRecyclerAdapter.STYLE_SHORT;
    }

    @Override
    public void stateLoadIndicator(Boolean state) {
        mBinding.loadIndicator.setRefreshing(state);
    }

    @Override
    public void showRetrySnackbar(Runnable action) {
        Snackbar.make(mBinding.container, R.string.error_retry, Snackbar.LENGTH_SHORT)
                .setAction(R.string.label_retry, (_bar) -> action.run()).show();
    }

    @Override
    public void stateVisibilityContainer(Boolean state) {
        stateLoadIndicator(false);
        mBinding.container.setVisibility(state ? View.VISIBLE : View.INVISIBLE);
    }
}
