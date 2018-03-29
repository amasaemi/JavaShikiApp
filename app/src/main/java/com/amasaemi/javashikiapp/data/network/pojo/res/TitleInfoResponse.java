package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.amasaemi.javashikiapp.data.network.pojo.constants.Kind;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Rating;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Related;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Role;
import com.amasaemi.javashikiapp.data.network.pojo.constants.Status;
import com.amasaemi.javashikiapp.data.network.pojo.constants.TitleType;
import com.amasaemi.javashikiapp.data.network.pojo.sup.Image;
import com.amasaemi.javashikiapp.utils.ConstantManager;
import com.amasaemi.javashikiapp.utils.ErrorReport;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class TitleInfoResponse {
    // формат даты выхода следующей серии
    // string -> date
    private final String NEXT_EP_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    // формат даты начала показа и окончания на входе
    // string -> date
    private final String AIRED_ON_DATE_PATTERN = "yyyy-MM-dd";

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

    @SerializedName("episodes_aired")
    @Expose
    private int episodesAired;

    @SerializedName("volumes")
    @Expose
    private int volumes;

    @SerializedName("chapters")
    @Expose
    private int chapters;

    @SerializedName("aired_on")
    @Expose
    private String airedOn;

    @SerializedName("released_on")
    @Expose
    private String releasedOn;

    @SerializedName("rating")
    @Expose
    private Rating rating;

    @SerializedName("english")
    @Expose
    private String[] english = null;

    @SerializedName("japanese")
    @Expose
    private String[] japanese = null;

    @SerializedName("synonyms")
    @Expose
    private String[] synonyms = null;

    @SerializedName("duration")
    @Expose
    private int duration;

    @SerializedName("score")
    @Expose
    private float score;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("description_html")
    @Expose
    private String descriptionHtml;

    @SerializedName("description_source")
    @Expose
    private String descriptionSource;

    @SerializedName("favoured")
    @Expose
    private boolean favoured;

    @SerializedName("anons")
    @Expose
    private boolean anons;

    @SerializedName("ongoing")
    @Expose
    private boolean ongoing;

    @SerializedName("thread_id")
    @Expose
    private int threadId;

    @SerializedName("topic_id")
    @Expose
    private int topicId;

    @SerializedName("rates_scores_stats")
    @Expose
    private List<TitleRate> ratesScores = null;

    @SerializedName("rates_statuses_stats")
    @Expose
    private List<TitleRate> ratesStatuses = null;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("next_episode_at")
    @Expose
    private String nextEpisodeAt;

    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;

    @SerializedName("studios")
    @Expose
    private List<Studio> studios = null;

    @SerializedName("publishers")
    @Expose
    private List<Studio> publishers = null;

    @SerializedName("user_rate")
    @Expose
    private UserRateResponse userRate;

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

    @Nullable
    public String getJpName() {
        try {
            return japanese[0];
        } catch (NullPointerException npe) {
            return null;
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
     * Метод вернет оценку тайтла
     * @return
     */
    public float getScore() {
        return score;
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
     * Метод вернет продолжительность эпизода в минутах
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Метод вернет описание тайтла
     * @return
     */
    @Nullable
    public String getDescription() {
        return (!descriptionHtml.contains("Нет описания") ? descriptionHtml : null);
    }

    /**
     * Метод вернет дату начала показа аниме / выхода манги
     * Формата: Февраль 2016
     * @return
     */
    @Nullable
    public Date getAiredOn() {
        try {
            return new SimpleDateFormat(AIRED_ON_DATE_PATTERN, Locale.getDefault()).parse(airedOn);
        } catch (ParseException pe) {
            ErrorReport.sendReport(pe);
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
            return new SimpleDateFormat(AIRED_ON_DATE_PATTERN, Locale.getDefault()).parse(releasedOn);
        } catch (ParseException pe) {
            ErrorReport.sendReport(pe);
            return null;
        }
    }

    /**
     * Метод вернет дату выхода следующего эпизода
     * @return
     */
    public Date getNextEpisodeAt() {
        try {
            return new SimpleDateFormat(NEXT_EP_DATE_PATTERN, Locale.getDefault()).parse(nextEpisodeAt);
        } catch (ParseException pe) {
            ErrorReport.sendReport(pe);
            return null;
        }
    }

    /**
     * Метод возвращает жанры
     * @return
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * Метод вернет список студий / издательств
     * @return
     */
    public List<Studio> getStudios() {
        List<Studio> returnedStudios = new LinkedList<Studio>();

        if (studios != null && !studios.isEmpty()) returnedStudios.addAll(studios);
        if (publishers != null && !publishers.isEmpty()) returnedStudios.addAll(publishers);
        return (!returnedStudios.isEmpty()) ? returnedStudios : null;
    }

    /**
     * Метод вернет возрастной рейтинг
     * @return
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Метод возвращает статус онгоинга
     * @return
     */
    public boolean isOngoing() {
        return ongoing;
    }

    /**
     * Метод возвращает true, если тайтл содержится в избранном текущего пользователя
     * @return
     */
    public boolean hasFavoured() {
        return favoured;
    }

    /**
     * Метод возвращает userRate текущего пользователя
     * @return
     */
    public UserRateResponse getUserRate() {
        return userRate;
    }

    /**
     * Метод возвращает тип тайтла (аниме, манга, ранобэ, не определено)
     * @return
     */
    @NonNull
    public TitleType getTitleType() {
        switch (getKind()) {
            case TV:
                return TitleType.ANIME;
            case MANGA:
                return TitleType.MANGA;
            case NOVEL:
                return TitleType.RANOBE;
            default:
                return TitleType.NONE;
        }
    }

    class TitleRate {
        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("value")
        @Expose
        private int value;

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }

    public class Genre {
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("russian")
        @Expose
        private String russian;

        @SerializedName("kind")
        @Expose
        private TitleType kind;

        public int getGenreId() {
            return id;
        }

        public String getGenreName() {
            return (Locale.getDefault().getLanguage().equals("ru")) ? russian : name;
        }

        public TitleType getGenreKind() {
            return kind;
        }
    }

    class Studio {
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("filtered_name")
        @Expose
        private String filteredName;

        @SerializedName("real")
        @Expose
        private boolean real;

        @SerializedName("image")
        @Expose
        private String image;

        public int getStudioId() {
            return id;
        }

        public String getStudioName() {
            return name;
        }

        public String getStudioImage() {
            return image;
        }
    }

    public class ScreenhostResponse {
        @SerializedName("original")
        @Expose
        private String original;

        @SerializedName("preview")
        @Expose
        private String preview;

        public String getOriginal() {
            if (original.startsWith(ConstantManager.SHIKI_BASE_WITHOUT_DELIMITER))
                return original;
            else
                return ConstantManager.SHIKI_BASE_WITHOUT_DELIMITER + original;
        }

        public String getPreview() {
            if (preview.startsWith(ConstantManager.SHIKI_BASE_WITHOUT_DELIMITER))
                return preview;
            else
                return ConstantManager.SHIKI_BASE_WITHOUT_DELIMITER + preview;
        }
    }

    public class ExternalLinksResponse {
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("kind")
        @Expose
        private String kind;

        @SerializedName("url")
        @Expose
        private String url;

        public String getKind() {
            return kind;
        }

        public String getUrl() {
            return url;
        }
    }

    public class VideoResponse {
        @SerializedName("id")
        @Expose
        private int id;

        @SerializedName("image_url")
        @Expose
        private String imageUrl;

        @SerializedName("player_url")
        @Expose
        private String url;

        @SerializedName("name")
        @Expose
        private String title;

        @SerializedName("kind")
        @Expose
        private String kind;

        @SerializedName("hosting")
        @Expose
        private String hosting;

        public String getPoster() {
            return imageUrl;
        }

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return (title == null || title.isEmpty()) ? null : title;
        }

        public String getKind() {
            switch (kind) {
                case "op": return "opening";
                case "ed": return "ending";
                case "pv": return "promotional video";
                case "other": return "other";
                default: return "video";
            }
        }

        public String getHostingColor() {
            try {
                switch (hosting) {
                    case "vk": return "#BBDEFB";
                    case "youtube": return "#FFCDD2";
                    case "smotret_anime": return "#C8E6C9";
                    default: return "#FFE0B2";
                }
            } catch (NullPointerException npe) {
                return "#FFE0B2";
            }
        }
    }

    public class RelatedResponse {
        @SerializedName("relation")
        @Expose
        private Related relation;

        @SerializedName("anime")
        @Expose
        private TitleListItemResponse anime;

        @SerializedName("manga")
        @Expose
        private TitleListItemResponse manga;

        public Related getRelation() {
            return relation;
        }

        public TitleListItemResponse getTitle() {
            return (anime != null) ? anime : manga;
        }

        public TitleType getTitleType() {
            return (anime != null) ? anime.getTitleType() : (manga != null) ? manga.getTitleType() : TitleType.NONE;
        }
    }

    public class RolesResponse extends TitleInfoResponse {
        @SerializedName("role")
        @Expose
        private Role role;

        public Role getRole() {
            return role;
        }
    }
}