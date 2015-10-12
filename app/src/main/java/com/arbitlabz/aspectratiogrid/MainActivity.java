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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.edmodo.rangebar.RangeBar;
import com.github.awanishraj.aspectratiorecycler.ARAdapterWrapper;
import com.github.awanishraj.aspectratiorecycler.ARLayoutManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final float MIN = 0.5f;
    private static final float MAX = 8f;
    private final String LOG_TAG = "MainActivity";
    private ImageAdapter imageAdapter;
    private ARLayoutManager alm;
    private List<ImagePOJO> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupRecyclerView();
        setupRangeBar();
    }

    private void setupRecyclerView() {
        final RecyclerView rv_images = (RecyclerView) findViewById(R.id.recyclerView);

        images = DummyImages.getDummyList();
        imageAdapter = new ImageAdapter(this);
        imageAdapter.setDataset(images);
        alm = new ARLayoutManager(this, imageAdapter);

        rv_images.setAdapter(new ARAdapterWrapper(imageAdapter));
        rv_images.setLayoutManager(alm);
    }

    private void setupRangeBar() {
        RangeBar rbar = (RangeBar) findViewById(R.id.rangebar1);
        rbar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar, int i, int i1) {
                alm.setThresholds(getProportional(i), getProportional(i1));
            }
        });
        rbar.setThumbIndices(2, 5);
    }

    private float getProportional(int value) {
        return MIN + ((MAX - 1) / 9) * value;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
