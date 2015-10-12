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

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Awanish Raj on 27/08/15.
 */
public class ARLayoutManager extends GridLayoutManager {

    /**
     * Default values of aspect ratio thresholds
     */
    private float ar_min = 2.0f;
    private float ar_max = 3.0f;

    private Activity mActivity;
    private List<? extends DimInterface> mValues;
    private RecyclerView.Adapter mAdapter;

    /**
     * Constructor for this layoutManager
     *
     * @param activity            Activity is required for display metrics calculation
     * @param ARAdapterInterface For obtaining the dataset and adapter
     */
    public ARLayoutManager(Activity activity, ARAdapterInterface ARAdapterInterface) {
        super(activity, 1);
        this.mActivity = activity;
        this.mValues = ARAdapterInterface.getDataset();
        this.mAdapter = ARAdapterInterface.getAdapter();
        updateDataSet();
    }

    /**
     * Constructor for this layoutManager
     *
     * @param activity            Activity is required for display metrics calculation
     * @param ARAdapterInterface For obtaining the dataset and adapter
     * @param ar_min              Minimum Aspect ratio required
     * @param ar_max              Maximum Aspect ratio limit
     */
    public ARLayoutManager(Activity activity, ARAdapterInterface ARAdapterInterface, float ar_min, float ar_max) {
        super(activity, 1);
        this.mActivity = activity;
        this.ar_max = ar_max;
        this.ar_min = ar_min;
        this.mValues = ARAdapterInterface.getDataset();
        updateDataSet();
    }

    /**
     * Method computes the cell dimensions, and notifies the Adapter
     */
    private void updateDataSet() {
        ARAspectComputer.SpanBucket result = ARAspectComputer.computeAspects(mActivity, mValues, ar_min, ar_max);
        this.setSpanCount(result.spanCount);
        this.setSpanSizeLookup(result.spanSizeLookup);
        this.mAdapter.notifyDataSetChanged();
    }

    /**
     * Method to update the Aspect Ratio thresholds
     *
     * @param ar_min Minimum Aspect ratio required
     * @param ar_max Maximum Aspect ratio limit
     */
    public void setThresholds(float ar_min, float ar_max) {
        this.ar_min = ar_min;
        this.ar_max = ar_max;
        updateDataSet();
    }

}
