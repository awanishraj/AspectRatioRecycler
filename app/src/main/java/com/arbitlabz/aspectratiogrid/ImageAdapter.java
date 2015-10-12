/*******************************************************************************
 * Copyright 2015 Awanish Raj
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.arbitlabz.aspectratiogrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.awanishraj.aspectratiorecycler.ARAdapterInterface;
import com.github.awanishraj.aspectratiorecycler.DimInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Awanish Raj on 23/09/15.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements ARAdapterInterface {

    private List<ImagePOJO> mValues = new ArrayList<>();
    private Context mContext;

    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery_aspect, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ImagePOJO obj = mValues.get(position);
        Glide.with(mContext)
                .load(obj.getImagePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .override(obj.getWidth(), obj.getHeight())
                .centerCrop()
                .into(holder.iv_single);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Method to set the dataset for the adapter
     * @param images List of image objects
     */
    public void setDataset(List<ImagePOJO> images) {
        mValues.clear();
        mValues.addAll(images);
        notifyDataSetChanged();
    }


    /**
     * ViewHolder for this adapter
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView iv_single;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_single = (ImageView) itemView.findViewById(R.id.iv_single);
        }
    }

    /**
     * Method from ARAdapterInterface
     *
     * @return RecyclerView Adapter
     */
    @Override
    public RecyclerView.Adapter getAdapter() {
        return this;
    }

    /**
     * Method from ARAdapterInterface
     *
     * @return Dataset
     */
    @Override
    public List<? extends DimInterface> getDataset() {
        return mValues;
    }

}
