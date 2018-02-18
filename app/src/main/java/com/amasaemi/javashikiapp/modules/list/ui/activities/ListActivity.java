package com.amasaemi.javashikiapp.modules.list.ui.activities;

import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.StaticAppManager;
import com.amasaemi.javashikiapp.databinding.ActivityListBinding;
import com.amasaemi.javashikiapp.databinding.LayoutNavigationHeaderBinding;
import com.amasaemi.javashikiapp.modules.base.ui.activities.BaseActivity;
import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseFragment;
import com.amasaemi.javashikiapp.modules.list.ui.fragments.AboutFragment;
import com.amasaemi.javashikiapp.modules.list.ui.fragments.AnimeListFragment;
import com.amasaemi.javashikiapp.modules.list.ui.fragments.CalendarFragment;
import com.amasaemi.javashikiapp.modules.list.ui.fragments.CommunityFragment;
import com.amasaemi.javashikiapp.modules.list.ui.fragments.MangaListFragment;
import com.amasaemi.javashikiapp.modules.list.ui.fragments.NewsFragment;
import com.amasaemi.javashikiapp.modules.list.ui.fragments.RanobeListFragment;
import com.amasaemi.javashikiapp.modules.list.ui.fragments.SettingsFragment;
import com.amasaemi.javashikiapp.modules.list.ui.models.NavigationHeaderModel;

/**
 * Created by Alex on 04.02.2018.
 */

public class ListActivity extends BaseActivity {
    // доступ к view
    private ActivityListBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        // цепляем адаптер для просмотра страниц
        mBinding.fragmentContainer.setAdapter(new FragmentViewerAdapter(getFragmentManager()));
        // инициализируем элементы управления
        initControlViews();
    }

    /**
     * Метод настраивает основные элементы управления
     */
    private void initControlViews() {
        setSupportActionBar(mBinding.toolbar);
        // задаем начальный заголовок
        setTitle(mBinding.fragmentContainer.getAdapter().getPageTitle(0));
        // слушатель смены страниц для обновления title
        mBinding.fragmentContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int pos) {
                setTitle(mBinding.fragmentContainer.getAdapter().getPageTitle(pos));
            }

            @Override
            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {
                return;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                return;
            }
        });
        // поведение actionBarDrawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout, mBinding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // настраиваем header у navigationDrawer
        LayoutNavigationHeaderBinding header = DataBindingUtil.bind(mBinding.listNavigation.getHeaderView(0));
        // настраиваем заголовок относительно того, авторизован пользователь, или по "гостевому" сценарию
        header.setModel(new NavigationHeaderModel(this, StaticAppManager.getInstance().getCurrentUser()));
        // настраиваем меню navigation view
        mBinding.listNavigation.setNavigationItemSelectedListener((menuItem) -> {
            mBinding.drawerLayout.closeDrawers();

            switch (menuItem.getItemId()) {
                case R.id.navigation_anime_catalog:
                    mBinding.fragmentContainer.setCurrentItem(0);
                    break;

                case R.id.navigation_manga_catalog:
                    mBinding.fragmentContainer.setCurrentItem(1);
                    break;

                case R.id.navigation_ranobe_catalog:
                    mBinding.fragmentContainer.setCurrentItem(2);
                    break;

                case R.id.navigation_calendar:
                    mBinding.fragmentContainer.setCurrentItem(3);
                    break;

                case R.id.navigation_news:
                    mBinding.fragmentContainer.setCurrentItem(4);
                    break;

                case R.id.navigation_social:
                    mBinding.fragmentContainer.setCurrentItem(5);
                    break;

                case R.id.navigation_settings:
                    mBinding.fragmentContainer.setCurrentItem(6);
                    break;

                case R.id.navigation_about:
                    mBinding.fragmentContainer.setCurrentItem(7);
                    break;
            }

            return true;
        });
    }

    @Override
    public void onBackPressed() {
        // проверяем, закрыт ли drawerLayout
        if (mBinding.drawerLayout.isDrawerOpen(Gravity.START))
            mBinding.drawerLayout.closeDrawers();
        // проверяем, что условий для несрабатывания super.onBackPressed() во фрагментах нету
        else if (((BaseFragment)((FragmentPagerAdapter)mBinding.fragmentContainer.getAdapter())
                .getItem(mBinding.fragmentContainer.getCurrentItem()))
                .onBackPressed())
            super.onBackPressed();
    }

    private class FragmentViewerAdapter extends FragmentPagerAdapter {
        private FragmentViewerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseFragment getItem(int pos) {
            switch (pos) {
                case 0: return AnimeListFragment.getInstance(null);
                case 1: return MangaListFragment.getInstance(null);
                case 2: return RanobeListFragment.getInstance(null);
                case 3: return CalendarFragment.getInstance(null);
                case 4: return NewsFragment.getInstance(null);
                case 5: return CommunityFragment.getInstance(null);
                case 6: return SettingsFragment.getInstance(null);
                case 7: return AboutFragment.getInstance(null);
                default: throw new NullPointerException(String.format("%d is not valid index of adapter", pos));
            }
        }

        @Override
        public CharSequence getPageTitle(int pos) {
            return getResources().getStringArray(R.array.list_activity_titles)[pos];
        }

        @Override
        public int getCount() {
            return 8;
        }
    }
}
