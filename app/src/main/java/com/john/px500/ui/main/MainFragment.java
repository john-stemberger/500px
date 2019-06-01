package com.john.px500.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.john.px500.R;
import com.john.px500.data.entity.Photo;
import com.john.px500.pattern.ComponentAdapter;
import com.john.px500.pattern.ComponentViewModel;
import com.john.px500.pattern.photo.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment
        implements Observer<List<Photo>> {

    private MainViewModel viewModel;
    private RecyclerView list;
    private ComponentAdapter adapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);
        list = root.findViewById(R.id.photo_list);
        // TODO: replace with a new layout manager
        list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new ComponentAdapter();
        list.setAdapter(adapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getPhotos().observe(this, this);
    }

    /**
     * Observer<List<Photo>>
     */
    @Override
    public void onChanged(List<Photo> photos) {
        if (photos != null) {
            List<ComponentViewModel> models = new ArrayList<>();
            // convert the photos to view models
            for (Photo p : photos) {
                PhotoViewModel model = new PhotoViewModel(ComponentAdapter.COMPONENT_TYPE_PHOTO_TALL);
                model.setPhoto(p);
                model.setThumbnail(p.getImageUrl(), p.getName());
                models.add(model);
            }
            adapter.setViewModels(models);
            adapter.notifyDataSetChanged();
        } else {
            // add a zero case because we were not able to load photos.
            // skipping but it fits nicely into the pattern library.
        }

    }
}
