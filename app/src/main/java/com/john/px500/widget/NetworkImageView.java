package com.john.px500.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

/**
 * a wrapper around an image view that loads images using our image library. This this case
 * the image library is Glide but ideally I would wrap the image library in an abstraction layer
 * to let me swap out the actual image library without having to change of of this code.
 */
public class NetworkImageView extends androidx.appcompat.widget.AppCompatImageView {

    protected String mUrl;

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetworkImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            url = null;
        }

        mUrl = url;

        if (mUrl == null) {
            setImageDrawable(null);
        } else {
            loadImage();
        }
    }

    public String getUrl() {
        return mUrl;
    }

    protected void loadImage() {
        Glide.with(getContext()).clear(this);

        Glide.with(getContext())
                .load(mUrl)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(this);
    }

}
