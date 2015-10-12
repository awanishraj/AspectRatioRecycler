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
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Awanish Raj on 27/08/15.
 */
public class ARAspectComputer {


    /**
     * Method that computes the SpanBucket for the given list of objects
     * @param activity
     * @param objList
     * @param ar_min
     * @param ar_max
     * @return
     */
    public static synchronized SpanBucket computeAspects(Activity activity, final List<? extends DimInterface> objList, float ar_min, float ar_max) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int W = dm.widthPixels;
        //Apply the dimensions based on thresholds
        applyAspects(objList, W, ar_min, ar_max);
        return new SpanBucket(objList, W);
    }

    /**
     * Method to normalize the heights while maintaining the aspect ratio
     *
     * @param subList
     * @param height
     */
    private static synchronized void normalizeHeights(List<? extends DimInterface> subList, float height) {
        for (DimInterface temp : subList) {
            temp.setWidth((int) (height * getAspectRatio(temp)));
            temp.setHeight((int) height);
        }
    }

    /**
     * Algorithm that applies the dimensions to the DimInterface objects for layout.
     *
     * @param imageList
     * @param W
     * @param ar_min
     * @param ar_max
     */
    private static synchronized void applyAspects(List<? extends DimInterface> imageList, int W, float ar_min, float ar_max) {
        float ar_sum = 0;
        List<DimInterface> tempList = new ArrayList<>();
        for (DimInterface temp : imageList) {
            tempList.add(temp);
            ar_sum += getAspectRatio(temp);
            if (ar_sum >= ar_min && ar_sum <= ar_max) {
                normalizeHeights(tempList, W / ar_sum);
                ar_sum = 0;
                tempList.clear();
            } else if (ar_sum > ar_max) {
                DimInterface pop = tempList.remove(tempList.size() - 1);
                ar_sum -= getAspectRatio(pop);
                normalizeHeights(tempList, W / ar_sum);
                tempList.clear();
                tempList.add(pop);
                ar_sum = getAspectRatio(pop);
            }
        }
        normalizeHeights(tempList, W / ar_sum);
    }

    /**
     * Class to store the SpanCount and SpanSizeLookup
     */
    public static class SpanBucket {
        public int spanCount;
        public GridLayoutManager.SpanSizeLookup spanSizeLookup;

        public SpanBucket(final List<? extends DimInterface> objList, final int W) {
            this.spanCount = W;
            this.spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (objList.get(position) != null) {
                        int spanSize = objList.get(position).getWidth();
                        if (spanSize <= W) {
                            return spanSize;
                        }
                    }
                    return W;
                }
            };
        }
    }

    /**
     * Method to obtain the aspect ratio for DimInterface object
     *
     * @param dim
     * @return
     */
    private static synchronized float getAspectRatio(DimInterface dim) {
        return 1.0f * dim.getWidth() / dim.getHeight();
    }

}
