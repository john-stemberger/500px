package com.john.px500.pattern.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.john.px500.R;
import com.john.px500.pattern.BaseComponentAdapter;
import com.john.px500.pattern.BaseComponentViewHolder;
import com.john.px500.pattern.photo.PhotoViewHolder;

public class PhotoAdapter extends BaseComponentAdapter {
    public static final int COMPONENT_TYPE_PHOTO_TALL = getNextViewType();
    public static final int COMPONENT_TYPE_PHOTO_MEDIUM = getNextViewType();
    public static final int COMPONENT_TYPE_PHOTO_SHORT = getNextViewType();

    @NonNull
    @Override
    public BaseComponentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BaseComponentViewHolder viewHolder = null;
        if (viewType == COMPONENT_TYPE_PHOTO_SHORT) {
            // create a story book view holder
            viewHolder = new PhotoViewHolder<>(inflater.inflate(R.layout.photo_short, parent, false));
        } else if (viewType == COMPONENT_TYPE_PHOTO_MEDIUM) {
            // create a card catalog style view holder
            viewHolder = new PhotoViewHolder<>(inflater.inflate(R.layout.photo_medium, parent, false));
        } else if (viewType == COMPONENT_TYPE_PHOTO_TALL) {
            // create a card catalog style view holder
            viewHolder = new PhotoViewHolder<>(inflater.inflate(R.layout.photo_tall, parent, false));
        } else {
            viewHolder = super.onCreateViewHolder(parent, viewType);
        }
        return viewHolder;
    }
}
