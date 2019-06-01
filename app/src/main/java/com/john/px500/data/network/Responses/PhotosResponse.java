package com.john.px500.data.network.Responses;

import com.google.gson.annotations.SerializedName;
import com.john.px500.data.entity.Photo;

import java.util.List;

public class PhotosResponse {
    // @500Px: please update your documentation. the response values do not match the example for the get photos request.
    // it does not include image_url

    private String feature;
    // ignoring for now
    //private Filter mFilters
    @SerializedName("current_page")
    private int page;
    @SerializedName("total_pages")
    private int numPages;
    @SerializedName("total_items")
    private int numItems;

    private List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }

    @Override
    public String toString() {
        return "PhotosResponse{" +
                "feature='" + feature + '\'' +
                ", page=" + page +
                ", numPages=" + numPages +
                ", numItems=" + numItems +
                ", photos=" + photos +
                '}';
    }
}
