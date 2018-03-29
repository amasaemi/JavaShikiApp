package com.amasaemi.javashikiapp.modules.info.ui.activities;

import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.Log;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.modules.base.ui.activities.BaseActivity;
import com.amasaemi.javashikiapp.modules.base.ui.customs.DeactiveViewPager;
import com.amasaemi.javashikiapp.modules.base.ui.fragments.BaseFragment;

/**
 * Created by Alex on 12.03.2018.
 */

public class TitleInfoActivity extends BaseActivity {
    public static final String TITLE_ID = "title_id";
    public static final String TITLE_TYPE = "title_type";

    private static final String TAG = TitleInfoActivity.class.getSimpleName();
    // id текущего тайтла
    private int mTitleId = 0;
    // тип текущего тайтла
    private TitleType mTitleType = TitleType.NONE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // если активность была создана по ошибке - закрываем ее
        if (savedInstanceState == null && getIntent() == null) {
            Log.e(TAG, "Created activity without input information");
            finish();
        }

        DeactiveViewPager fragmentContainer = new DeactiveViewPager(this);
        // цепляем адаптер для просмотра страниц
        fragmentContainer.setAdapter(new FragmentViewerAdapter(getFragmentManager()));
        // назначаем view для активности
        setContentView(fragmentContainer);

        try {
            // получаем тип тайтла
            mTitleType = TitleType.valueOf(getIntent().getData().getQueryParameter(TITLE_TYPE));
            // получаем id тайтла. Если формат id тайтла имеет символы не равные цифру - обрезаем первый (буквенный) символ
            try {
                mTitleId = Integer.parseInt(getIntent().getData().getQueryParameter(TITLE_ID));
            } catch (NumberFormatException nfe) {
                mTitleId = Integer.parseInt(getIntent().getData().getQueryParameter(TITLE_ID));
            }

            mBundle = new Bundle();
            mBundle.putInt(TITLE_ID, mTitleId);
            mBundle.putString(TITLE_TYPE, mTitleType.name());
        } catch (NullPointerException npe) {
            // TODO: 12.03.2018 обработать ошибку
        }
    }

    private class FragmentViewerAdapter extends FragmentPagerAdapter {
        private FragmentViewerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseFragment getItem(int pos) {
            switch (pos) {
                case 0:
                    switch (mTitleType) {
                        case ANIME:
//                            return AnimeInfoFragment.getInstance(mBundle);
                        case MANGA:
                        case RANOBE:
//                            return MangaInfoFragment.getInstance(mBundle);
                        default: throw new NullPointerException(String.format("%s is not valid title type", mTitleType.name()));
                    }
//                case 1: return ActorsFragment.getInstance(null);
//                case 2: return TopicFragment.getInstance(null);
                default: throw new NullPointerException(String.format("%d is not valid index of adapter", pos));
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
