package com.amasaemi.javashikiapp.modules.base.models;

import com.amasaemi.javashikiapp.modules.base.adapters.interfaces.ViewModel;

/**
 * Created by Alex on 31.01.2018.
 */

public class BaseSimpleShortCard implements ViewModel {
    private int mId;
    private Runnable mCardClick;
    private String mTitle;
    private String mSubtitle;

    public BaseSimpleShortCard(int id, Runnable cardClick, String title, String subtitle) {
        mId = id;
        mCardClick = cardClick;
        mTitle = title;
        mSubtitle = subtitle;
    }

    public Runnable getCardClick() {
        return mCardClick;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSubtitle() {
        return mSubtitle;
    }
}