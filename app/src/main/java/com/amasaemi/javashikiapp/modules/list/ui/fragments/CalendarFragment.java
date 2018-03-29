package com.amasaemi.javashikiapp.modules.list.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.network.pojo.res.CalendarResponse;
import com.amasaemi.javashikiapp.modules.base.adapters.DoubleRecyclerAdapter;
import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.ViewModel;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.ShikiPresenter;
import com.amasaemi.javashikiapp.modules.base.mvp.views.ShikiListView;
import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseListFragment;
import com.amasaemi.javashikiapp.modules.list.mvp.presenters.CalendarPresenter;
import com.amasaemi.javashikiapp.modules.list.ui.models.CalendarCardModel;
import com.amasaemi.javashikiapp.modules.list.ui.models.CalendarDelimiterModel;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alex on 02.02.2018.
 */

public class CalendarFragment extends BaseListFragment implements ShikiListView<CalendarResponse> {
    public static final String TAG = CalendarFragment.class.getSimpleName();

    public static CalendarFragment getInstance(Bundle bundle) {
        CalendarFragment fragment = new CalendarFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @InjectPresenter
    CalendarPresenter mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        // настраиваем цветовую схему у swiperefresh
        mBinding.loadIndicator.setColorScheme(
                R.color.refresh_1,
                R.color.refresh_2,
                R.color.refresh_3,
                R.color.refresh_4
        );
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // добавляем элемент меню сортировки
        menu.add(Menu.NONE, 1, Menu.NONE, "sort")
                .setIcon(R.drawable.icon_sort)
                .setOnMenuItemClickListener((item) -> { changeRecyclerManager(); return true; })
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public ShikiPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void setupRecyclerView() {
        // устанавливаем менеджер recyclerView
        setupRecyclerViewLayoutManager(null);
        // id ресурса заголовка
        int headerLayout = R.layout.card_calendar_delimiter;
        // id ресурса обычного элемента
        int itemLayout = listStyleIsShort() ? R.layout.card_simple_short : R.layout.card_calendar_item_long;
        // назначаем адаптер
        createRecyclerViewAdapter(new DoubleRecyclerAdapter(headerLayout, itemLayout));
    }

    @Override
    public void fillAdapter(List<CalendarResponse> list) {
        new Thread(() -> {
            DoubleRecyclerAdapter adapter = (DoubleRecyclerAdapter) mBinding.container.getAdapter();
            adapter.setItems(new CalendarParser().parse(list));

            stateVisibilityContainer(!adapter.isEmpty());
        }).run();
    }

    @Override
    public void clearAdapter() {
        ((DoubleRecyclerAdapter) mBinding.container.getAdapter()).clearItems();
    }

    private class CalendarParser {
        private List<ViewModel> parse(List<CalendarResponse> list) {
            // days[0] сегодня
            // days[1] завтра
            // days[2] 3й день недели
            // days[3] 4й день недели
            // days[4] 5й день недели
            // days[5] 6й день недели
            // days[6] 7й день недели
            boolean[] mDays = new boolean[7];

            List<ViewModel> parseResult = new LinkedList<>();
            Calendar calendar = Calendar.getInstance();

            for (CalendarResponse item : list) {
                calendar.setTime(item.getNextEpisodeAt());

                // если дата текущей model >= чем 7 дней спустя от этой даты, прерываем цикл
                // т.к мы парсим только на неделю вперед.
                if (calendar.get(Calendar.DAY_OF_YEAR) >= getDate(7))
                    break;

                for (int i = 0; i < 7; i++) {
                    if (calendar.get(Calendar.DAY_OF_YEAR) == getDate(i)) {
                        if (!mDays[i]) {
                            parseResult.add(new CalendarDelimiterModel(item));
                            mDays[i] = true;
                        }

                        parseResult.add(new CalendarCardModel(getActivity(), item));
                        break;
                    }
                }
            }

            return parseResult;
        }

        /**
         * Метод прибавляет к текущей дате [addDay] дней
         * @return количество дней в году
         */
        private int getDate(int addDays) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, addDays);
            return calendar.get(Calendar.DAY_OF_YEAR);
        }
    }
}
