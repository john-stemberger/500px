package com.john.px500.pattern.photo;

import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.john.px500.R;
import com.john.px500.data.entity.Photo;
import com.john.px500.pattern.ComponentViewModel;

public class PhotoViewModel<T extends PhotoViewHolder> extends ComponentViewModel<T> {

    protected String thumbnailUrl;
    protected String name;
    protected Photo photo;

    public PhotoViewModel(int type) {
        super(type);
    }

    /**
     * Model builder methods
     */

    public PhotoViewModel setThumbnail(@Nullable String thumbnail, @Nullable String name) {
        this.thumbnailUrl = thumbnail;
        this.name = name;
        return this;
    }

    public PhotoViewModel setPhoto(Photo p) {
        photo = p;
        return this;
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

    @Override
    public void bind(@NonNull T viewHolder) {
        super.bind(viewHolder);
        bindThumbnail(viewHolder);
        bindAccessibility(viewHolder);
    }
}
