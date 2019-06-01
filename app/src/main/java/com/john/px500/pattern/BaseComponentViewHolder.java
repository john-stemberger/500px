package com.john.px500.pattern;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A common  base for all view holders.
 * The standard view holder/binder structure provided by android has been
 * replaced by a component view holder and component view model structure. This
 * decouples the data layer from the presentation layer.
 *
 * Here the purpose of the view holder is two fold:
 * 1) provides the standard view caching for efficient binding of data
 * 2) provides a "layout contract" which enforces an shared definition of what a particular component is capable of displaying
 *    This is used to allow designers to display a ui component in distinct ways depending on the use case. For example
 *    a photo may be styled as a gallery (thumbnail image, with a title under, and author under that) as a simple image "postcard" where
 *    the title and author is over the image.
 *    By defining the view holder independent of a specific layout we are able to reuse the view model for multiple
 *    layouts
 *
 */
public class BaseComponentViewHolder extends RecyclerView.ViewHolder {

  public BaseComponentViewHolder(@NonNull View itemView) {
    super(itemView);
  }

  /**
   * When a view holder gets recycled we want to free up any data the view was was holding onto.
   * In most cases the data is small (text) but if there is an image it can be quite large.
   */
  public void unbind() {

  }
}
