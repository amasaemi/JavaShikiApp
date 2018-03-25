package com.amasaemi.javashikiapp.modules.base.ui.customs;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.amasaemi.javashikiapp.data.managers.StaticAppManager;

/**
 * Created by Alex on 31.01.2018.
 */

public class DeactiveViewPager extends ViewPager {
    public DeactiveViewPager(Context context) {
        super(context);
    }

    public DeactiveViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return false;
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, StaticAppManager.getInstance().getUserSettings().hasAnimateSwapFragments());
    }
}
