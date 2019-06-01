package com.john.px500.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.john.px500.data.entity.Photo;
import com.john.px500.data.network.ApiManager;
import com.john.px500.data.network.Responses.PhotosResponse;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PixelRepository {

    private static final class LoadPhotosRunnable implements Runnable {
        private MutableLiveData<List<Photo>> photos;

        private LoadPhotosRunnable(@NonNull MutableLiveData<List<Photo>> liveData) {
            photos = liveData;
        }

        @Override
        public void run() {
            // NOTE: Dependency injection would work better but for now I am using singletons instead
            PhotosResponse response = ApiManager.getInstance().getPhotos("popular");
            if (response != null) {
                photos.postValue(response.getPhotos());
            } else {
                photos.postValue(null);
            }
        }
    }

    private static PixelRepository INSTANCE;

    public static PixelRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PixelRepository();
        }
        return INSTANCE;
    }

    private ExecutorService backgroundService;
    private MutableLiveData<List<Photo>> photos;

    private PixelRepository() {
        backgroundService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Photo>> getPhotos() {
        if (photos == null) {
            photos = new MutableLiveData<>();
            loadPhotos();
        }
        return photos;
    }

    private void loadPhotos() {
        backgroundService.submit(new LoadPhotosRunnable(photos));
    }

}
