package com.john.px500.pattern;


import android.graphics.Rect;

import androidx.annotation.NonNull;

/**
 * The base for all component view models. This allows me to separate the data model from the view model
 * and more easily change or update the view model without impacting the views. Useful for replacing legacy
 * systems with new servers that may be using more efficient data structures
 */
public abstract class ComponentViewModel<T extends BaseComponentViewHolder> {
    private final int mViewType;
    private Rect mMargins; // the margin values to use for this view defined in [left, top, right, bottom] order

    public ComponentViewModel(int type) {
        mViewType = type;
        mMargins = new Rect(0, 0, 0, 0);
    }

    public ComponentViewModel setMargins(int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        mMargins.left = leftMargin;
        mMargins.top = topMargin;
        mMargins.right = rightMargin;
        mMargins.bottom = bottomMargin;
        return this;
    }

    public Rect getMargins() {
        return mMargins;
    }

    public int getViewType() {
        return mViewType;
    }

    /**
     * Bind the data from the view model to the corresponding view holder. All view models should
     * override this method
     *
     * @param viewHolder the view holder to bind the data to.
     */
    public void bind(@NonNull T viewHolder) {
        // no-op
    }

    public void bindActionListeners(ComponentAdapter componentAdapter) {
        // no-op
    }
}
