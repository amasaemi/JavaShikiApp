package com.amasaemi.javashikiapp.modules.base.ui.customs;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Александр on 02.10.2017.
 */

public final class PosterImageView extends android.support.v7.widget.AppCompatImageView {
    public PosterImageView(Context context) {
        super(context);
    }
    public PosterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public PosterImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        // если высота известна, а ширина нет - настраиваем ширину, иначе, если высота не известна
        // а ширина известна - настраиваем высоту
        if (width != 0 && height == 0) height = (int) (width * 1.5);
        else if (height != 0 && width == 0) width = (int) (height / 1.5);

        setMeasuredDimension(width, height);
    }
}
