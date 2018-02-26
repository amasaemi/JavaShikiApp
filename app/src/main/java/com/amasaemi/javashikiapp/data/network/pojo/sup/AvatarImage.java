package com.amasaemi.javashikiapp.data.network.pojo.sup;

import android.net.Uri;

import com.amasaemi.javashikiapp.R;
import com.amasaemi.javashikiapp.utils.ConstantManager;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 22.02.2018.
 */

public class AvatarImage {
    // качество изображения
    public static final int QUALITY_HIGH = 2;
    public static final int QUALITY_MIDDLE = 1;
    public static final int QUALITY_LOW = 0;

    private final String SHIKIMORI_BASE_REFERENCE = "https://shikimori.org";
    private final Uri SHIKIMORI_NULL_REFERENCE = Uri.parse("https://shikimori.orgnull");
    private final Uri PLACEHOLDER = Uri.parse(String.format("drawable://%d", R.drawable.img_load_failed));

    @SerializedName("x160")
    @Expose
    private String original;

    @SerializedName("x148")
    @Expose
    private String preview;

    @SerializedName("x64")
    @Expose
    private String small;

    public Uri getOriginal() {
        return (original.contains("missing")) ? PLACEHOLDER : Uri.parse(original);
    }

    public Uri getPreview() {
        return (preview.contains("missing")) ? PLACEHOLDER : Uri.parse(preview);
    }

    public Uri getSmall() {
        return (small.contains("missing")) ? PLACEHOLDER : Uri.parse(small);
    }
}
