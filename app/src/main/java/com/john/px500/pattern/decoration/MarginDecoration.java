package com.john.px500.pattern.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.john.px500.pattern.ComponentAdapter;

public class MarginDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = MarginDecoration.class.getSimpleName();

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        RecyclerView.Adapter adapter = parent.getAdapter();
        if (!(adapter instanceof ComponentAdapter)) {
            return;
        }
        int position = parent.getChildAdapterPosition(view);
        ComponentAdapter componentAdapter = (ComponentAdapter) adapter;
        Rect margins = componentAdapter.getMarginForPosition(position);
        outRect.left = margins.left;
        outRect.top = margins.top;
        outRect.right = margins.right;
        outRect.bottom = margins.bottom;
    }
}