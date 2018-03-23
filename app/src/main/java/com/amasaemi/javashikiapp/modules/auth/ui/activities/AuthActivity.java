package com.amasaemi.javashikiapp.modules.auth.ui.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.data.managers.PreferencesManager;
import com.amasaemi.javashikiapp.data.managers.StaticAppManager;
import com.amasaemi.javashikiapp.data.services.AuthService;
import com.amasaemi.javashikiapp.data.services.UserService;
import com.amasaemi.javashikiapp.databinding.ActivityAuthBinding;
import com.amasaemi.javashikiapp.modules.base.ui.activities.BaseActivity;
import com.amasaemi.javashikiapp.modules.info.ui.activities.TitleInfoActivity;
import com.amasaemi.javashikiapp.modules.list.ui.activities.ListActivity;
import com.amasaemi.javashikiapp.utils.ConstantManager;

/**
 * Created by Alex on 04.02.2018.
 */

public class AuthActivity extends BaseActivity {
    // доступ к view
    private ActivityAuthBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        // настраиваем цветовую схему у swiperefresh
        mBinding.loadIndicator.setColorScheme(
                R.color.refresh_1,
                R.color.refresh_2,
                R.color.refresh_3,
                R.color.refresh_4
        );
        // обработчик нажатия по кнопки авторизации
        mBinding.auth.setOnClickListener(view -> {
//            createAuthDialog();

            startActivity(new Intent(this, ListActivity.class));
            this.finish();
        });
        // обработчик нажатия по кнопке регистрации
        mBinding.registration.setOnClickListener(view -> {
            // Toast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show()
            startActivity(new Intent(this, TitleInfoActivity.class));
        });
        // обработчик нажатия по кнопке о приложении
        mBinding.about.setOnClickListener((view -> Toast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show()));
    }

    private void createAuthDialog() {
        // включаем индикатор загрузки и отключаем случайные нажатия по другим вьюшкам лайоута
        hasStartAuthDialog(true);
        // создаем webView, в котором будем отображать страничку авторизации
        WebView webView = new WebView(this);
        webView.setLayoutParams(new LinearLayout.LayoutParams(
                getResources().getDimensionPixelSize(R.dimen.size_320),
                getResources().getDimensionPixelSize(R.dimen.size_320)));
        // создаем диалог для webView
        AlertDialog authDialog = new AlertDialog.Builder(this)
                .setView(webView)
                .setNegativeButton(R.string.label_close, (di, i) -> {
                    // выключаем индикатор загрузки и включаем остальные вьюшки
                    hasStartAuthDialog(false);
                    di.dismiss();
                })
                .create();
        // вешаем слушатель на наш линк
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return !(request.getUrl().toString().equals("https://shikimori.org/oauth/authorize")
                        || request.getUrl().toString().equals("https://shikimori.org/users/sign_in"));
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (authDialog != null && !authDialog.isShowing()) authDialog.show();

                if (url.startsWith("https://shikimori.org/oauth/authorize/")) {
                    if (authDialog != null) authDialog.dismiss();

                    String authKey = url.substring(50, url.length());
                    auth(authKey);
                } else if (url.equals("https://shikimori.org/oauth/authorize")) {
                    if (authDialog != null) authDialog.dismiss();
                    // выключаем индикатор загрузки и включаем остальные вьюшки
                    hasStartAuthDialog(false);
                    showDialog(getString(R.string.status_error), getString(R.string.error_shiki_permission_denied))
                            .create().show();
                }
            }
        });
        // загружаем страничку с запросом на авторизацию
        webView.loadUrl(ConstantManager.SHIKIMORI_OAUTH_LINK);
    }

    private void auth(String authKey) {
        new AuthService().getAccessToken(authKey, (response) -> {
                // проверить успешность авторизации
                StaticAppManager.getInstance().setUserAuthInfo(new StaticAppManager.UserAuthInfo(response));
                checkAuth();
            }, (throwable) -> {
                hasStartAuthDialog(false);
                showDialog(getString(R.string.status_error), throwable.getMessage())
                        .create().show();
            });
    }

    private void checkAuth() {
        new UserService().getCurrentUser((response) -> {
                // записываем авторизационные данные пользователя
                StaticAppManager.getInstance().setCurrentUser(
                        new StaticAppManager.UserProfileInfo(response, StaticAppManager.getInstance().getUserAuthInfo()));
                // сохраняем новые авторизационные данные
                new PreferencesManager(this).saveAccessData(StaticAppManager.getInstance().getCurrentUser());

                startActivity(new Intent(this, ListActivity.class));
                this.finish();
            }, (t) -> {
                // скорее всего тут будет истекший токен
                new AuthService().refreshToken(StaticAppManager.getInstance().getCurrentUser().getRefreshToken(), (response) -> {
                            // сохраняем новые токены
                            StaticAppManager.getInstance().setUserAuthInfo(new StaticAppManager.UserAuthInfo(response));
                            // снова делаем проверку
                            checkAuth();
                        },
                        (throwable) -> {
                            showDialog(getString(R.string.status_error), throwable.getMessage())
                                    .create().show();
                        });
            });
    }

    private void hasStartAuthDialog(boolean state) {
        mBinding.loadIndicator.setRefreshing(state);
        mBinding.auth.setEnabled(!state);
        mBinding.registration.setEnabled(!state);
        mBinding.about.setEnabled(!state);
    }
}
