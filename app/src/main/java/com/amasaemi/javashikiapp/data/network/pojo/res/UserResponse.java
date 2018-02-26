package com.amasaemi.javashikiapp.data.network.pojo.res;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.amasaemi.javashikiapp.data.network.pojo.sup.AvatarImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 24.12.2017.
 */

public class UserResponse {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nickname")
    @Expose
    @NonNull
    private String nickname;

    @SerializedName("avatar")
    @Expose
    @NonNull
    private String avatar;

    @SerializedName("image")
    @Expose
    @NonNull
    private AvatarImage image;

    @SerializedName("last_online_at")
    @Expose
    @Deprecated
    @NonNull
    private String lastOnlineAt;

    @SerializedName("name")
    @Expose
    @Nullable
    private String name;

    @SerializedName("sex")
    @Expose
    @Nullable
    private String sex;

    @SerializedName("full_years")
    @Expose
    @Nullable
    private int fullYears;

    @SerializedName("last_online")
    @Expose
    @NonNull
    private String lastOnline;

    @SerializedName("website")
    @Expose
    @Nullable
    private String website;

    @SerializedName("location")
    @Expose
    @Nullable
    private String location;

    @SerializedName("banned")
    @Expose
    private boolean isBanned;

    @SerializedName("about")
    @Expose
    @Nullable
    private String about;

    @SerializedName("about_html")
    @Expose
    @Deprecated
    @Nullable
    private String aboutHtml;

    @SerializedName("common_info")
    @Expose
    private String[] commonInfo = null;

    @SerializedName("show_comments")
    @Expose
    private boolean showComments;

    @SerializedName("in_friends")
    @Expose
    private boolean isFriends;

    @SerializedName("is_ignored")
    @Expose
    private boolean isIgnored;

    @SerializedName("stats")
    @Expose
    @NonNull
    private Stats stats;

    @SerializedName("style_id")
    @Expose
    @Deprecated
    private int styleId;

    public int getId() {
        return id;
    }

    @NonNull
    public String getNickname() {
        return nickname;
    }

    @NonNull
    public Uri getAvatar() {
        return image.getPreview();
    }

    @NonNull
    public String getStatus() {
        return lastOnline;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @NonNull
    public Stats getStats() {
        return stats;
    }

    public boolean isFriend() {
        return isFriends;
    }

    public class Stats {
        @SerializedName("statuses")
        @Expose
        private Statuses statuses;

        @SerializedName("full_statuses")
        @Expose
        @Deprecated
        private Statuses fullStatuses;

        @SerializedName("scores")
        @Expose
        private Statuses scores;

        @SerializedName("types")
        @Expose
        private Statuses types;

        @SerializedName("ratings")
        @Expose
        private Statuses ratings;

        @SerializedName("has_anime?")
        @Expose
        private boolean hasAnime;

        @SerializedName("has_manga?")
        @Expose
        private boolean hasManga;

        public Statuses getStatuses() {
            return statuses;
        }

        public Statuses getScores() {
            return scores;
        }

        public Statuses getTypes() {
            return types;
        }

        public Statuses getRatings() {
            return ratings;
        }

        public boolean isHasAnime() {
            return hasAnime;
        }

        public boolean isHasManga() {
            return hasManga;
        }

        public class Statuses {
            @SerializedName("anime")
            @Expose
            private Status[] anime = null;

            @SerializedName("manga")
            @Expose
            private Status[] manga = null;

            public Status[] getAnime() {
                return anime;
            }

            public Status[] getManga() {
                return manga;
            }

            public class Status {
                /**
                 * 0 - planned
                 * 1 - watching
                 * 2 - completed
                 * 3 - on_hold
                 * 4 - dropped
                 */
                @SerializedName("id")
                @Expose
                private int id;

                @SerializedName("name")
                @Expose
                private String name;

                @SerializedName(value = "size", alternate = { "value" })
                @Expose
                private int size;

                public int getId() {
                    return id;
                }

                public String getName() {
                    return name;
                }

                public int getSize() {
                    return size;
                }
            }
        }
    }
}
