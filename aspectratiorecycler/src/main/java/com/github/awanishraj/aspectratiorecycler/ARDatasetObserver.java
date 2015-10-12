/*******************************************************************************
 * Copyright 2015 Awanish Raj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.github.awanishraj.aspectratiorecycler;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Awanish Raj on 12/10/15.
 */
public class ARDatasetObserver extends RecyclerView.AdapterDataObserver {
    private RecyclerView.Adapter mAdapter;

    public ARDatasetObserver(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public void onChanged() {
        super.onChanged();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
        super.onItemRangeChanged(positionStart, itemCount);
        mAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        super.onItemRangeChanged(positionStart, itemCount, payload);
        mAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        super.onItemRangeInserted(positionStart, itemCount);
        mAdapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        super.onItemRangeRemoved(positionStart, itemCount);
        mAdapter.notifyItemRangeRemoved(positionStart, itemCount);
    }
}
