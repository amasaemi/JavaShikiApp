package com.amasaemi.javashikiapp.data.network.pojo.req;

import android.text.TextUtils;

import com.amasaemi.javashikiapp.data.network.pojo.constants.Kind;
import com.amasaemi.javashikiapp.data.network.pojo.constants.MyList;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Order;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Rating;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Status;
import com.amasaemi.javashikiapp.data.network.pojo.res.TitleInfoResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 01.02.2018.
 */

public class SearchingParams {
    private Order mOrder = Order.BY_POPULARITY;
    private List<Kind> mKinds = null;
    private List<Status> mStatuses = null;
    private List<String> mSeasons = null;
    private List<Rating> mRatings = null;
    private List<TitleInfoResponse.Genre> mGenres = null;
    private List<MyList> mMyLists = null;
    private String mSearch = null;

    public SearchingParams() { }

    public SearchingParams(Order order, List<Kind> kinds, List<Status> statuses,
                           List<String> seasons, List<Rating> ratings,
                           List<TitleInfoResponse.Genre> genres, List<MyList> myLists, String search) {
        mOrder = order;
        mKinds = kinds;
        mStatuses = statuses;
        mSeasons = seasons;
        mRatings = ratings;
        mGenres = genres;
        mMyLists = myLists;
        mSearch = search;
    }

    public SearchingParams(String search) {
        mSearch = search;
    }

    public Order getOrder() {
        return mOrder;
    }

    public String getKinds() {
        return (mKinds != null && !mKinds.isEmpty()) ? TextUtils.join(",", mKinds) : null;
    }

    public String getStatuses() {
        return (mStatuses != null && !mStatuses.isEmpty()) ? TextUtils.join(",", mStatuses) : null;
    }

    public String getSeasons() {
        return (mSeasons != null && !mSeasons.isEmpty()) ? TextUtils.join(",", mSeasons) : null;
    }

    public String getRatings() {
        return (mRatings != null && !mRatings.isEmpty()) ? TextUtils.join(",", mRatings) : null;
    }

    public String getGenres() {
        return (mGenres != null && !mGenres.isEmpty()) ? TextUtils.join(",", mGenres) : null;
    }

    public String getMyLists() {
        return (mMyLists != null && !mMyLists.isEmpty()) ? TextUtils.join(",", mMyLists) : null;
    }

    public String getSearch() {
        return mSearch;
    }
}
