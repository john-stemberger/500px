package com.john.px500.data.entity;

import com.google.gson.annotations.SerializedName;

public class Image {
    private String format;
    private int size;
    private String url;
    @SerializedName("https_url")
    private String secureUrl;
}
