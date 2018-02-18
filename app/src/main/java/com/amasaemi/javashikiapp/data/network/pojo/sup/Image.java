package com.amasaemi.javashikiapp.data.network.pojo.sup;
import android.net.Uri;

import com.amasaemi.javashikiapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Optional;

public class Image {
    // качество изображения
    public static final int QUALITY_HIGH = 2;
    public static final int QUALITY_MIDDLE = 1;
    public static final int QUALITY_LOW = 0;

    private final String SHIKIMORI_BASE_REFERENCE = "https://shikimori.org";
    private final Uri SHIKIMORI_NULL_REFERENCE = Uri.parse("https://shikimori.orgnull");
    private final Uri PLACEHOLDER = Uri.parse(String.format("drawable://%d", R.drawable.img_load_failed));

    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("preview")
    @Expose
    private String preview;
    @SerializedName("x96")
    @Expose
    private String x96;
    @SerializedName("x48")
    @Expose
    private String x48;

    public Uri getOriginal() {
        Uri imageLink = Uri.parse(SHIKIMORI_BASE_REFERENCE + original);
        return (!imageLink.equals(SHIKIMORI_NULL_REFERENCE)) ? imageLink : PLACEHOLDER;
    }

    public Uri getPreview() {
        Uri imageLink = Uri.parse(SHIKIMORI_BASE_REFERENCE + preview);
        return (!imageLink.equals(SHIKIMORI_NULL_REFERENCE)) ? imageLink : PLACEHOLDER;
    }

    public Uri getSmall() {
        Uri imageLink = Uri.parse(SHIKIMORI_BASE_REFERENCE + x96);
        return (!imageLink.equals(SHIKIMORI_NULL_REFERENCE)) ? imageLink : PLACEHOLDER;
    }
}
