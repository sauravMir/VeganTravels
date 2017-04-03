package com.vegantravels.utilities;

import android.app.Application;

import com.vegantravels.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ibrar on 3/30/2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/rancho3.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
