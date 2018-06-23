package com.example.flickerimgdownload.imageSearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.flickerimgdownload.R;
import com.example.flickerimgdownload.entity.PhotoModel;


import java.util.ArrayList;
import java.util.List;


public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.CustomViewHolder> {


    private List<PhotoModel> photoModels = new ArrayList<PhotoModel>();

    private final Context mContext;



    public PhotoListAdapter(Context context, List<PhotoModel> photos) {
        this.mContext = context;
        this.photoModels = photos;

    }

    @Override
    public PhotoListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.item, parent, false);
        return new CustomViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(PhotoListAdapter.CustomViewHolder holder, int position) {
        PhotoModel photoModel = photoModels.get(position);
        Glide.with(mContext).load(photoModel.getUrl())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return photoModels.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder  {

        ImageView image;

        public CustomViewHolder(View view) {
            super(view);

            image = (ImageView) view;//(ImageView) view.findViewById(R.id.image);

        }



    }
}