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


import com.github.awanishraj.aspectratiorecycler.DimInterface;

/**
 * Created by Awanish Raj on 23/09/15.
 */
public class ImagePOJO implements DimInterface {

    private Object path;
    private int width;
    private int height;

    public ImagePOJO(int width, int height, Object path) {
        setWidth(width);
        setHeight(height);
        this.path = path;
    }

    public Object getImagePath() {
        return path;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
}
