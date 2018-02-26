package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.amasaemi.javashikiapp.data.network.pojo.constants.Kind;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Status;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 23.12.2017.
 */

public class MyListRatesResponse extends UserRateResponse {
    /**
     * {
     * "id": Int,
     * "score": Int,
     * "status": MyList,
     * "text": String?,
     * "text_html": String,
     * "episodesCount": Int?,
     * "chapters": Int?,
     * "volumes": Int?,
     * "rewatches": Int,
     * "user": SimpleUserResponse,
     * "anime": AnimeListResponse,
     * "manga": MangaListResponse
     * }
     */

    @SerializedName("anime")
    @Expose
    @Nullable
    private TitleListItemResponse anime;

    @SerializedName("manga")
    @Expose
    @Nullable
    private TitleListItemResponse manga;

    public int getTitleId() {
        return (getCurrentTitle() != null) ? getCurrentTitle().getId() : -1;
    }

    public String getEnName() {
        return getCurrentTitle().getEnName();
    }

    public String getRuName() {
        return getCurrentTitle().getRuName();
    }

    public Uri getPoster() {
        return getCurrentTitle().getPoster();
    }

    public Kind getKind() {
        return (getCurrentTitle() != null) ? getCurrentTitle().getKind() : Kind.NONE;
    }

    public int[] getEpisodes() {
        int episodes[] = new int[3];
        episodes[0] = getWatches();
        episodes[1] = getCurrentTitle().getEpisodes()[0];
        episodes[2] = getCurrentTitle().getEpisodes()[1];
        return episodes;
    }

    public Status getTitleStatus() {
        return (getCurrentTitle() != null) ? getCurrentTitle().getStatus() : Status.NONE;
    }

    public TitleType getTitleType() {
        return getCurrentTitle().getTitleType();
    }

    private TitleListItemResponse getCurrentTitle() {
        return (anime != null) ? anime : manga;
    }
}
