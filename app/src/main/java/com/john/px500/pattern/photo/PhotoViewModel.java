package com.john.px500.pattern.photo;

import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.john.px500.R;
import com.john.px500.data.entity.Photo;
import com.john.px500.pattern.ComponentAdapter;
import com.john.px500.pattern.ComponentViewModel;

import java.lang.ref.WeakReference;

public class PhotoViewModel<T extends PhotoViewHolder> extends ComponentViewModel<T>
        implements View.OnClickListener {


    public interface PhotoClickListener {
        void onPhotoClick(PhotoViewModel viewModel);
    }

    protected String thumbnailUrl;
    protected String name;
    protected Photo photo;
    protected WeakReference<PhotoClickListener> listener;

    public PhotoViewModel(int type) {
        super(type);
        listener = new WeakReference<>(null);
    }

    /**
     * Model builder methods
     */

    public PhotoViewModel setClickListener(@Nullable PhotoClickListener l) {
        listener = new WeakReference<>(l);
        return this;
    }

    public PhotoViewModel setThumbnail(@Nullable String thumbnail, @Nullable String name) {
        this.thumbnailUrl = thumbnail;
        this.name = name;
        return this;
    }

    public PhotoViewModel setPhoto(Photo p) {
        photo = p;
        return this;
    }

    public Photo getPhoto() {
        return photo;
    }

    /**
     * Model binder methods
     */

    /**
     * bind the thumbnail to the view holder. We separate this for easy extensions to the view model
     * at a later date
     *
     * @param viewHolder the view holder to bind the image to
     */
    protected void bindThumbnail(@NonNull T viewHolder) {
        if (TextUtils.isEmpty(thumbnailUrl)) {
            // NOTE: alternately we could load "404 image not found" image if design would prefer that
            viewHolder.mThumbnail.setUrl(null);
            viewHolder.mThumbnail.setVisibility(View.GONE);
        } else {
            viewHolder.mThumbnail.setVisibility(View.VISIBLE);
            viewHolder.mThumbnail.setUrl(thumbnailUrl);
        }
    }

    protected void bindAccessibility(T viewHolder) {
        StringBuilder builder = new StringBuilder();
        Resources res = viewHolder.itemView.getResources();
        if (TextUtils.isEmpty(name)) {
            builder.append(res.getString(R.string.unnamed_work));
        } else {
            builder.append(name);
        }
        viewHolder.itemView.setContentDescription(builder.toString());
    }

    protected void bindClickListener(@NonNull T viewHolder) {
        viewHolder.itemView.setOnClickListener(this);
    }

    @Override
    public void bind(@NonNull T viewHolder) {
        super.bind(viewHolder);
        bindThumbnail(viewHolder);
        bindAccessibility(viewHolder);
        bindClickListener(viewHolder);
    }

    @Override
    public void bindActionListeners(ComponentAdapter componentAdapter) {
        super.bindActionListeners(componentAdapter);
        setClickListener(componentAdapter.getPhotoViewModelListener());
    }

    /**
     * View.OnClickListener
     */
    @Override
    public void onClick(View v) {
        PhotoClickListener l = listener.get();
        if (l != null) {
            l.onPhotoClick(this);
        }
    }


}
