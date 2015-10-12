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

package com.github.awanishraj.aspectratiorecycler;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by Awanish Raj on 12/10/15.
 * <p/>
 * Wrapper for a RecyclerView Adapter that allows AspectRatioLayout
 */
public class ARAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView.Adapter mAdapter;
    private List<? extends DimInterface> mDataset;

    /**
     * Constructor for the wrapper
     *
     * @param ARAdapterInterface
     */
    public ARAdapterWrapper(ARAdapterInterface ARAdapterInterface) {
        this.mDataset = ARAdapterInterface.getDataset();
        this.mAdapter = ARAdapterInterface.getAdapter();
        this.mAdapter.registerAdapterDataObserver(new ARDatasetObserver(this));
    }

    /**
     * Simply wraps over the existing adapter
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    /**
     * Wraps over the existing adapter and also applies the LayoutParams
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setLayoutParams(new ARLayoutParams(mDataset.get(position)));
        this.mAdapter.onBindViewHolder(holder, position);
    }

    /**
     * SImply wraps over the existing adapter
     * @return
     */
    @Override
    public int getItemCount() {
        return mAdapter.getItemCount();
    }

    /**
     * Class that gives LayoutParams for the cell view.
     */
    private class ARLayoutParams extends RelativeLayout.LayoutParams {
        public ARLayoutParams(DimInterface dim) {
            super(dim.getWidth(), dim.getHeight());
        }
    }
}
