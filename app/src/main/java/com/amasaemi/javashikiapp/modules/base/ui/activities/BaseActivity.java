package com.amasaemi.javashikiapp.modules.base.ui.activities;

import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.amasaemi.javashikiapp.R;

/**
 * Created by Alex on 31.01.2018.
 */

public class BaseActivity extends AppCompatActivity {
    // ключ, необходимый для сохранения и возобновления последнего фрагмента активности
    protected final String LAST_POS = "lastpos";
    // бандл, через который передаем информацию на фрагмент
    protected Bundle mBundle;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home: onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Метод возрвщает true, если интернет соединение доступно
     */
    final protected boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnected();
    }

    final public AlertDialog.Builder showDialog(String title, String message) {
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.label_ok, (di, i) -> di.dismiss());
    }
}
