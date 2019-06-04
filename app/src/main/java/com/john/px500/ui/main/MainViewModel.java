package com.john.px500.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.john.px500.data.PixelRepository;
import com.john.px500.data.entity.Photo;
import com.john.px500.pattern.ComponentAdapter;
import com.john.px500.pattern.ComponentViewModel;
import com.john.px500.pattern.photo.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    // TODO: to support tablets we would probably want to change this to a dimen resource based on the screen
    // size but for now I am going to assume it is fixed
    public static final int GRID_SPAN_COUNT = 2;
    private static final int MARGIN = 24;
    private static final int HALF_MARGIN = MARGIN / 2;
    private LiveData<List<Photo>> photos;

    public MainViewModel() {
        photos = PixelRepository.getInstance().getPhotos();
    }

    public LiveData<List<ComponentViewModel>> getPhotos() {
        return Transformations.map(photos,
                new Function<List<Photo>, List<ComponentViewModel>>() {
                    @Override
                    public List<ComponentViewModel> apply(List<Photo> input) {
                        return convertPhotosToComponentViewModels(input);
                    }
                }
        );
    }

    private List<ComponentViewModel> convertPhotosToComponentViewModels(List<Photo> input) {
        List<ComponentViewModel> models = new ArrayList<>();
        int lastRowIndex = input.size() / GRID_SPAN_COUNT;
        // convert the photos to view models
        for (int i = 0; i < input.size(); i++) {
            Photo p = input.get(i);
            ComponentViewModel model = convertPhotoToViewModel(p);
            int left;
            int top;
            int bottom;
            int right;
            if (i % GRID_SPAN_COUNT == 0) {
                // left. so half margin goes into the middle
                left = MARGIN;
                right = HALF_MARGIN;
            } else {
                // right side
                left = HALF_MARGIN;
                right = MARGIN;
            }
            if (i < GRID_SPAN_COUNT) {
                // at the top so ensure the top is the full margin
                top = MARGIN;
            } else {
                top = HALF_MARGIN;
            }
            // This may cause problems with paging as the last component isn't actually the last component
            if (i / GRID_SPAN_COUNT == lastRowIndex - 1) {
                // we are in the last row. add a full bottom margin
                bottom = MARGIN;
            } else {
                bottom = HALF_MARGIN;
            }
            model.setMargins(left, top, right, bottom);
            models.add(model);
        }
        return models;
    }

    private PhotoViewModel convertPhotoToViewModel(Photo p) {
        PhotoViewModel model = new PhotoViewModel(ComponentAdapter.COMPONENT_TYPE_PHOTO_SHORT);
        model.setPhoto(p);
        model.setThumbnail(p.getImageUrl(), p.getName());
        return model;
    }
}
