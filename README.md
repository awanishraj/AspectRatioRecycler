AspectRatioRecycler
===================

AspectRatioRecycler Android library allows developers to easily apply an uncropped layout for cells in their image grid.
This kind of layout is ideal for a comfortable photo viewing as opposed to the traditional square grid.

![Screenshot][2]

This library is designed around the RecyclerView architecture, allowing developers to quickly port their RecyclerView projects with this LayoutManager.

The library majorly consists of 

- ```ARAdapterWrapper``` which wraps around your existing RecyclerView.Adapter
- ```ARLayoutManager``` which must be used as the LayoutManager for the RecyclerView

Download
--------
<!--You can download a jar from GitHub's [releases page][1].-->

Using Gradle:

```gradle
repositories {
    jcenter()
}

dependencies {
    compile 'com.github.awanishraj.aspect-ratio-recycler:aspectratiorecycler:0.1.0'
}
```

How do I use AspectRatioRecycler?
-------------------
### Step 1

Make your dataset item POJO implement the ```DimInterface```

```java
public class ImagePOJO implements DimInterface {

    
    //All fields and methods you may have in your POJO
    ...

	//Following overrides are for the interface methods
	private int width;
    private int height;
    
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
```

### Step 2

Make your custom RecyclerView.Adapter implement the ```ARAdapterInterface```

```java
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements ARAdapterInterface {

    private List<ImagePOJO> mValues = new ArrayList<>();
    
   	//All other methods and fields of your adapter
   	...
   	
   	
   	//Following overrides are for the interface 
   	
    @Override
    public RecyclerView.Adapter getAdapter() {
        return this;
    }

    @Override
    public List<? extends DimInterface> getDataset() {
        return mValues;
    }

}
```

You can alternatively define this ```ARAdapterInterface``` inline as per your wish.

### Step 3

Apply the wrapper around your adapter before applying to your RecyclerView.

For example change

```java
myRecyclerView.setAdapter(new ARAdapterWrapper(imageAdapter));
//Where imageAdapter is your adapter that implements ARAdapterInterface
```

### Step 4

Finally, use the ```ARLayoutManager``` and set it to your RecyclerView.

```java
ARLayoutManager alm = new ARLayoutManager(this, imageAdapter);
//Where imageAdapter is your adapter that implements ARAdapterInterface

myRecyclerView.setLayoutManager(alm);
```

How do I control the Grid Size?
-------------------------------

You can control the scaling of the grid using ```setThresholds(float ar_min, float ar_max)``` method of the ```ARLayoutManager```

The parameters decide the following:

- ```ar_min``` - This defines the minimum row-width to row-height ratio that should be used. A higher value means thinner rows with more items. *Default value* is **2.0f**
- ```ar_max``` - This defines the maximum row-width to row-height ratio that should be allowed. This prevents rows from becoming too thin because of panaromic images. Must be greater than ```ar_min```. *Default value* is **3.0f**

Calling this method automatically calls ```notifyDatasetChanged()``` on your adapter.

Example,

```java
...
alm.setThresholds(1.5f, 2.5f);
...
```



Android SDK Version
-------------------
AspectRatioRecycler requires a minimum SDK version of 9.

License
-------
Apache 2.0. See the [link][1] for details.

	Copyright 2015 Awanish Raj

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
 
	http://www.apache.org/licenses/LICENSE-2.0
 
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.


Author
------
Awanish Raj - @awanishraj

[1]: http://www.apache.org/licenses/LICENSE-2.0
[2]: https://github.com/awanishraj/AspectRatioRecycler/raw/master/screenshots/screens.png