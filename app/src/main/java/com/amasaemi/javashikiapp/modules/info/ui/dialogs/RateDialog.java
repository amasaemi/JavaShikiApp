package com.amasaemi.javashikiapp.modules.info.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Alex on 25.03.2018.
 */

public class RateDialog extends Dialog {

    public RateDialog(@NonNull Context context) {
        super(context);
    }

    public RateDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RateDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
