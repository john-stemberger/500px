package com.john.px500.data.network;

import com.john.px500.data.network.Responses.PhotosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface PixelServer {
    @GET("photos")
    Call<PhotosResponse> photos(@Query("feature") String feature,
                                @Query("consumer_key") String key);
}
