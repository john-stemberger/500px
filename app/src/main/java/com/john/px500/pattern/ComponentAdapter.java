package com.john.px500.pattern;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.john.px500.pattern.adapter.PhotoAdapter;

/**
 * This is an empty class that forms the last extension of the BaseComponentAdapter extension chain.
 * The reason for this is really about minimising code changes as you extend the pattern library.
 * For example, lets say you have the BaseComponentAdapter then is extended by the PhotoAdapter.
 * If you have a fragment (call it the ListingsFragment) that creates an instance of the
 * PhotoAdapter. Now, if you add a new pattern into a new Adapter (SocialAdapter) and want to take
 * advantage of all of those patterns in the ListingsFragment then you have to change the adapter type
 * in the Listings Fragment and all other fragments that also show elements from the pattern library.
 * <p>
 * Instead of having to make many changes in many files to take advantage of a new pattern element
 * we simply always instantiate an instance of the ComponentAdapter. When it comes time in create
 * the SocialAdapter you simply insert the SocialAdapter into the extension chain between PhotoAdapter
 * and have ComponentAdapter extend SocialAdapter. Now you can have all the benefits of the new
 * components in any fragment without having to make changes to the type of adapter created
 */
public class ComponentAdapter extends PhotoAdapter {
    @NonNull
    @Override
    public BaseComponentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(@NonNull BaseComponentViewHolder holder, int position) {
        ComponentViewModel model = mViewModels.get(position);
        model.bind(holder);
        model.bindActionListeners(this);
    }


}
