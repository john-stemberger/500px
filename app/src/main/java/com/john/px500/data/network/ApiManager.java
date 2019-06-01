package com.john.px500.data.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.john.px500.BuildConfig;
import com.john.px500.data.network.Responses.PhotosResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * API manager is responsible for fetching data from the network.
 * This makes it easier for persistent data fetching from the repo as the repo can simply toggle
 * between the ApiManager and a future RoomManager which would fetch data from the local ROOM database
 */
public class ApiManager {

    public static final String TAG = ApiManager.class.getSimpleName();

    private static final String DATE_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String VERSION = "v1/";
    private static final String API_HOST = "https://api.500px.com/" + VERSION;

    private PixelServer api;

    public ApiManager() {
        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT_STRING)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(PixelServer.class);
    }

    public PhotosResponse getPhotos(@NonNull String feature) {
        // NOTE: in this case we use the client token but we could use oauth instead by adding a check
        // before each of these methods and auto logging in

        Call call = api.photos(feature, BuildConfig.API_KEY);
        try {
            Response<PhotosResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            Log.d(TAG, "IO Exception e: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
