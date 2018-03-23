package com.amasaemi.javashikiapp.data.managers;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.util.Log;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;

/**
 * Created by Alex on 02.02.2018.
 */

public class ActivityManager {
    private static final String TAG = ActivityManager.class.getSimpleName();

    /**
     * Метод открывает новую страничку Chrome Page
     * @param uri - ссылка
     */
    public static void openChromeTabPage(Context context, Uri uri) {
        try {
            new CustomTabsIntent.Builder()
                    .setStartAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .build()
                    .launchUrl(context, uri);

            Log.i(TAG, "start activity by url: " + uri);
        } catch (ActivityNotFoundException anf) {
            startActivity(context, uri);
        }
    }

    public static void startTitleInfoActivity(Context context, int titleId, TitleType titleType) {
        startActivity(context, Uri.parse(String.format("shiki://info?title_type=%s&title_id=%d", titleType.name(), titleId)));
    }

    public static void startUserActivity(Context context, int userId) {
        startActivity(context, Uri.parse(String.format("shiki://profile?user_id=%d", userId)));
    }

    public static void startClubActivity(Context context, int clubId) {
        startActivity(context, Uri.parse(String.format("shiki://club?club_id=%d", clubId)));
    }

    /**
     * Метод запускает активность и логгирует url
     */
    private static void startActivity(Context context, Uri uri) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
            Log.i(TAG, "start activity by url: " + uri);
        } catch (ActivityNotFoundException anf) {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.status_error)
                    .setMessage(R.string.error_activity_not_found)
                    .setPositiveButton(R.string.label_close, (dialogInterface, i) -> dialogInterface.dismiss())
                    .create().show();
        }
    }
}
