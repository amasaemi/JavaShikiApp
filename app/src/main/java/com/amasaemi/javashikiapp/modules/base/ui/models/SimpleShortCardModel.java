package com.amasaemi.javashikiapp.modules.base.ui.models;

import android.net.Uri;

/**
 * Created by Alex on 02.02.2018.
 */

public abstract class SimpleShortCardModel {
    protected int id;

    public Runnable cardClick;
    public String ruName;
    public String enName;
    public Uri poster;
}
