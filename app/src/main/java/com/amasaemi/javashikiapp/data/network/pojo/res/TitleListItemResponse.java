package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.amasaemi.javashikiapp.data.managers.StaticAppManager;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Kind;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Status;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.data.network.pojo.sup.Image;
import com.amasaemi.javashikiapp.utils.ErrorReport;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TitleListItemResponse {
    // формат даты начала показа и окончания на входе
    // string -> date
    private final String DATE_PATTERN = "yyyy-MM-dd";

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("russian")
    @Expose
    private String russian;

    @SerializedName("image")
    @Expose
    private Image image;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("kind")
    @Expose
    private Kind kind;

    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("episodes")
    @Expose
    private int episodes;

    @SerializedName("volumes")
    @Expose
    private int volumes;

    @SerializedName("chapters")
    @Expose
    private int chapters;

    @SerializedName("episodes_aired")
    @Expose
    private int episodesAired;

    @SerializedName("aired_on")
    @Expose
    private String airedOn;

    @SerializedName("released_on")
    @Expose
    private String releasedOn;

    /**
     * Метод вернет id текущего тайтла
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Метод вернет название тайтла на romaji
     * @return
     */
    @NonNull
    public String getEnName() {
        return name;
    }

    /**
     * Метод вернет название тайтла на русском
     * @return
     */
    @Nullable
    public String getRuName() {
        return (Locale.getDefault().getLanguage().equals("ru")) ? russian : null;
    }

    /**
     * Метод вернет постер, в зависимости от настроек качества
     * @return
     */
    public Uri getPoster() {
        switch (StaticAppManager.getInstance().getUserSettings().getListImageQuality()) {
            case Image.QUALITY_MIDDLE: return getPreviewPoster();
            case Image.QUALITY_HIGH: return getOriginalPoster();
            default: return getSmallPoster();
        }
    }

    /**
     * Метод вернет постер оригинального размера
     * @return
     */
    @NonNull
    public Uri getOriginalPoster() {
        return image.getOriginal();
    }

    /**
     * Метод вернет превью постера
     * @return
     */
    @NonNull
    public Uri getPreviewPoster() {
        return image.getPreview();
    }

    /**
     * Метод вернет постер малого размера
     * @return
     */
    @NonNull
    public Uri getSmallPoster() {
        return image.getSmall();
    }

    /**
     * Метод вернет тип текущего тайтла
     * @return
     */
    @NonNull
    public Kind getKind() {
        return (kind != null) ? kind : Kind.NONE;
    }

    /**
     * Метод вернет статус текущего тайтла
     * @return
     */
    @NonNull
    public Status getStatus() {
        return (status != null) ? status : Status.NONE;
    }

    /**
     * Метод вернет массив элементов, где
     * [0] - количество всех эпизодов / глав
     * [1] - если онгоинг - количество вышедших серий, иначе 0 / количество томов
     * @return
     */
    @NonNull
    public int[] getEpisodes() {
        switch (getTitleType()) {
            case ANIME:
                return new int[] { episodes, episodesAired };

            case MANGA:
            case RANOBE:
                return new int[] { chapters, volumes };

            default:
                throw new NullPointerException(String.format("%s is not supported TitleType", getTitleType()));
        }
    }

    /**
     * Метод вернет дату начала показа аниме / выхода манги
     * Формата: Февраль 2016
     * @return
     */
    @Nullable
    public Date getAiredOn() {
        try {
            return new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(airedOn);
        } catch (ParseException pe) {
            ErrorReport.sendReport(String.format("%s %s", this.getClass().getSimpleName(), pe.getMessage()));
            return null;
        } catch (NullPointerException npe) {
            return null;
        }
    }

    /**
     * Метод вернет дату окончания показа аниме / выхода манги
     * Формата: Февраль 2016
     * @return
     */
    @Nullable
    public Date getReleasedOn() {
        try {
            return new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).parse(releasedOn);
        } catch (ParseException pe) {
            ErrorReport.sendReport(String.format("%s %s", this.getClass().getSimpleName(), pe.getMessage()));
            return null;
        }
    }

    /**
     * Метод возвращает тип тайтла (аниме, манга, ранобэ, не определено)
     * @return
     */
    @NonNull
    public TitleType getTitleType() {
        switch (getKind()) {
            case TV:
            case MOVIE:
            case OVA:
            case ONA:
            case SPECIAL:
            case MUSIC:
                return TitleType.ANIME;
            case MANGA:
            case MANHUA:
            case MANHWA:
            case DOUJIN:
            case ONE_SHOT:
                return TitleType.MANGA;
            case NOVEL:
                return TitleType.RANOBE;
            default:
                return TitleType.NONE;
        }
    }

    public boolean isOngoing() {
        return getStatus() == Status.ONGOING;
    }
}
