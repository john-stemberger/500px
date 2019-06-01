package com.john.px500.data.entity;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.List;

public class Photo {
    // I am exluding the following properties as I dont need them.
    // location
    // exclude_gads
    // converted_bits
    // camera
    // hi_res_upload
    // lens
    // for_critique
    // disliked
    // editors_choice_date
    // feature_date
    // editors_choice
    // status
    // feature
    // editored_by
    // collections_count
    // licensing_status
    // focal_length
    // favorites_count
    // profile
    // taken_at
    // request_to_buy_enabled
    // crop_version
    // shutter_speed
    // for_sale
    // comments
    // user_id - why is this part of the photo instead of part of the nested user object?
    // longitude
    // iso
    // for_sale_date
    //   "liked": false,
    //  "id": 307474999,
    //  "license_type": 0,
    //  "privacy": false,
    //  "sales_count": 0,
    //  image_format - already part of the Image object?
    //  "purchased": false,
    //  "licensing_suggested": false,
    //  "aperture": null,
    //   "positive_votes_count": 2128,
    //  "highest_rating": 99.8,
    //  "is_free_photo": false,
    //  "highest_rating_date": "2019-06-01T06:23:49-04:00",
    //  "converted": false,
    //  "latitude": null,
    //  "watermark": false,
    //  "votes_count": 2128,
    //  "licensing_requested": false,
    //  "voted": false,
    //  "license_requests_enabled": true,
    //  "critiques_callout_dismissed": false,

    private long id;
    private String name;
    private String description;
    @SerializedName("times_viewed")
    private int viewCount;
    private float rating;
    @SerializedName("created_at")
    private Date creation; // Why do you send this as a
    private int category; // probably want to convert this to an enum for type safety. Also, can a photo only exist in a single category? why is this not an array of categories
    private boolean privacy;
    private int width;
    private int height;
    @SerializedName("votes_count")
    private int numVotes;
    @SerializedName("comments_count")
    private int numComments;
    private boolean nsfw;
    private List<Image> images;
    @SerializedName("image_url")
    private List<String> imageUrl; // why is this an array?
    private String url;
    private User user;

    public String getImageUrl() {
        if (imageUrl != null && imageUrl.size() > 0) {
            return imageUrl.get(0);
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
