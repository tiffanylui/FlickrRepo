package com.example.tiffanylui.flickerrendering;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by tiffanylui on 2017-06-06.
 */

class PhotoAdapter extends android.support.v7.widget.RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    List<GalleryItem> mPhotos;

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (SimpleDraweeView) itemView.findViewById(R.id.image);
        }
    }

    public PhotoAdapter(List<GalleryItem> photos) {
        mPhotos = photos;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_layout, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder holder, int position) {
        holder.image.setImageURI(mPhotos.get(position).mUrl);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }


}
