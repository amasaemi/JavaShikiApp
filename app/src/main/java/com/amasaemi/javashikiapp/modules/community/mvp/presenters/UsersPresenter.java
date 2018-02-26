package com.amasaemi.javashikiapp.modules.community.mvp.presenters;

import com.amasaemi.javashikiapp.data.network.pojo.req.SearchingParams;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleListItemResponse;
import com.amasaemi.javashikiapp.data.network.pojo.res.UserListResponse;
import com.amasaemi.javashikiapp.data.services.AnimeService;
import com.amasaemi.javashikiapp.data.services.UserService;
import com.amasaemi.javashikiapp.modules.base.mvp.presenters.BaseListPresenter;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

/**
 * Created by Alex on 22.02.2018.
 */
@InjectViewState
public class UsersPresenter extends BaseListPresenter<UserListResponse> {
    // сервис
    private UserService mService = new UserService();
    // поисковой запрос
    private String mQuery;

    public UsersPresenter() {
        mPresenterIsReady = true;
    }

    @Override
    public void loadOrRestoreData() {
        if (mPresenterIsReady) {
            if (!mList.isEmpty())
                getViewState().fillAdapter(mList);
            else
                getUsersList();
        } else {
            throw new NullPointerException(String.format("%s presenter is not ready", this.getClass().getSimpleName()));
        }
    }

    public void getUsersList(String query) {
        mCurrentPage = 1;
        mQuery = query;

        clearAdapter();
        getUsersList();
    }

    private void getUsersList() {
        getViewState().stateLoadIndicator(true);
        // загружаем список аниме
        mService.getUserList(mCurrentPage, mQuery, this::fillAdapter, this::parseError);
    }

    public void loadNextPage() {
        mCurrentPage = (mQuery == null) ? 1 : mCurrentPage + 1;
        getUsersList();
    }

    @Override
    protected void fillAdapter(List<UserListResponse> list) {
        // пагинация списка
        // если список не пуст, то возвращает все элементы без последнего
        // иначе возвращаем список от 0 до 0, и обрабатываем пустой список
        super.fillAdapter(list.subList(0, (!list.isEmpty()) ? list.size() - 1 : list.size()));
    }
}