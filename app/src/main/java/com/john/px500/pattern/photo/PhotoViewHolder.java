package com.john.px500.pattern.photo;

import android.view.View;

import androidx.annotation.NonNull;

import com.john.px500.R;
import com.john.px500.pattern.BaseComponentViewHolder;
import com.john.px500.util.Asserts;
import com.john.px500.widget.NetworkImageView;

public class PhotoViewHolder<T extends PhotoViewModel> extends BaseComponentViewHolder {
    protected final NetworkImageView mThumbnail;

    public PhotoViewHolder(@NonNull View itemView) {
        super(itemView);
        mThumbnail = itemView.findViewById(R.id.thumbnail);
        Asserts.thatTrue(mThumbnail != null, "layout does not conform to definition [missing thumbnail]");
    }

    @Override
    public void unbind() {
        // this is really the important one as images can be quite large
        mThumbnail.setUrl(null);
        super.unbind();
    }
}
