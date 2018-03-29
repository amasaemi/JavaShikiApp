package com.amasaemi.javashikiapp.modules.base.ui.models;

import android.databinding.BaseObservable;
import android.net.Uri;

/**
 * Created by Alex on 02.02.2018.
 */

public abstract class SimpleShortCardModel extends BaseObservable {
    protected int id;

    public Runnable cardClick;
    public Runnable menuClick;
    public String ruName;
    public String enName;
    public Uri poster;
}
