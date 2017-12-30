# aDateSwitcher

<img width="300" src="https://i.imgur.com/Bo7Kpzf.png"></img>

aDateSwitcher is a custom android view that switches to your desired date range returns a Map<DateRange,Date> of top and bottom date values.

# How to use it

<b>Add aDateSwitcher to your app level build.gradle dependency</b>

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
	
dependencies {
    compile 'com.github.sayemkcn:aDateSwitcher:rc~1.1'
}

```

<b>Add aDateSwitcher in your layout</b>

```
<xyz.rimon.adateswitcher.DateSwitcher
    android:id="@+id/mDateSwitcher"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:buttonBackgroundColor="@color/colorAccent"
    app:buttonTextColor="@color/white"
    app:textBackgroundColor="@android:color/darker_gray"
    app:textColor="@color/white"
    app:textSize="18sp" />

```

<b>Setup DateSwitcher</b>

````
DateSwitcher mDateSwitcher = findViewById(R.id.mDateSwitcher);

// set listener
this.mDateSwitcher.setOnDateChangeListener(new DateSwitcher.OnDateChangeListener() {
     @Override
     public void onChange(Map<DateSwitcher.DateRange, Date> dateRange) {

         Date topDate = dateRange.get(DateSwitcher.DateRange.TOP_DATE);
         Date bottomDate = dateRange.get(DateSwitcher.DateRange.BOTTOM_DATE);
         
         Log.i("DATE_RANGE","TOP: " + topDate.toString()
                 + "\nBOTTOM: " + bottomDate.toString());
     }
 });

````

You can change date switcher type like

````
this.wDateSwitcher.setType(DateSwitcher.Type.WEEK);

````
