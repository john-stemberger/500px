package com.john.px500.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.john.px500.data.PixelRepository;
import com.john.px500.data.entity.Photo;

import java.util.List;

public class MainViewModel extends ViewModel {

    private LiveData<List<Photo>> photos;

    public MainViewModel() {
        photos = PixelRepository.getInstance().getPhotos();
    }

    public LiveData<List<Photo>> getPhotos() {
        return photos;
    }

}
