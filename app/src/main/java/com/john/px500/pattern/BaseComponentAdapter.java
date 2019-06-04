package com.john.px500.pattern;

import android.graphics.Rect;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This forms the bases for recycling of all components in the pattern library.
 * <p>
 * The view ViewModel is responsible for holding onto all view related information for a given
 * component as well as for any business logic that can be defined at the view level
 * (think fallback logic in the event that some data is missing). This adapter provides for all
 * extensions to the pattern library but does not implement any of the actual components. the
 * creation of the view holders is separated into the extensions to provide a logical grouping
 * to the library. I.E. all story components are defined in the PhotoAdapter, all social components
 * (comments/notes) can be contained in a SocialAdapter
 * <p>
 * is responsible for binding its self to a compatible view holder the code in here
 */
public abstract class BaseComponentAdapter extends RecyclerView.Adapter<BaseComponentViewHolder> {

    private static int sLastViewType = RecyclerView.INVALID_TYPE;

    /**
     * Get next available code
     * This is used to prevent view type collisions between the different adapters. The standard
     * way a view type would be defined is to use a series of constants but that means there needs
     * to be a single location in which all those constants are defined. That on its own is not
     * a bad thing but as the pattern library grows I want to be able to group common themed patterns
     * together for easier maintenance. I dont want to separate the constants from their primary usage
     * (i.e. the adapter that defines the view types) but if we are not careful by adding a view at a
     * lower level of the component adapter extension chain you could accidentally collide with an
     * already used number. One solution is to use a dynamic indexing system like this in which the
     * specific view type integer is determined at runtime.
     * <p>
     * Other alternatives would be to create some form of structure to the integer values themselves
     * like having all view types for a given grouping start at 100-199, the next grouping could
     * start at 200-299, etc
     */
    protected static synchronized int getNextViewType() {
        return ++sLastViewType;
    }


    protected List<ComponentViewModel> mViewModels;

    public BaseComponentAdapter() {
    }

    public void setViewModels(@Nullable List<ComponentViewModel> models) {
        mViewModels = models;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseComponentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this is gonna cause a crash eventually anyway is the system requires a non null view holder
        // and we just cant create one for this type.
        throw new IllegalArgumentException("Unknown View type detected [" + viewType + "]. Should range between 0 and " + sLastViewType);
    }

    @Override
    public final int getItemViewType(int position) {
        if (mViewModels == null ||
                position < 0 ||
                position >= mViewModels.size()) {
            return RecyclerView.INVALID_TYPE;
        }
        ComponentViewModel model = mViewModels.get(position);
        return model.getViewType();
    }

    @Override
    public final int getItemCount() {
        if (mViewModels == null) {
            // OPTIONAL: instead of setting it to 0 we can inject a "loading" view or "zero case" view
            // to make the ui look a littler nicer. This is nice for times when data is slow to load
            // due to network lag or slow devices querying the data source
            return 0;
        }
        return mViewModels.size();
    }

    @Override
    public final void onViewRecycled(@NonNull BaseComponentViewHolder holder) {
        holder.unbind();
        super.onViewRecycled(holder);
    }

    public Rect getMarginForPosition(int position) {
        return mViewModels.get(position).getMargins();
    }
}
