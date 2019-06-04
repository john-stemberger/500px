package com.john.px500.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.john.px500.R;
import com.john.px500.pattern.ComponentAdapter;
import com.john.px500.pattern.ComponentViewModel;
import com.john.px500.pattern.decoration.MarginDecoration;

import java.util.List;

public class MainFragment extends Fragment
        implements Observer<List<ComponentViewModel>> {

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
        // All the images the api returns seems to be square and quite low rez. I am just gonna use a grid layout manager for now
        // ideally I would use a custom layout manager to provide the nice staggered width format the app gives
        list.setLayoutManager(new GridLayoutManager(getContext(), MainViewModel.GRID_SPAN_COUNT));
        adapter = new ComponentAdapter();
        list.addItemDecoration(new MarginDecoration());
        list.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public void onChanged(List<ComponentViewModel> componentViewModels) {
        if (componentViewModels != null) {
            adapter.setViewModels(componentViewModels);
            adapter.notifyDataSetChanged();
        } else {
            // add a zero case because we were not able to load photos.
            // skipping but it fits nicely into the pattern library.
        }

    }
}
