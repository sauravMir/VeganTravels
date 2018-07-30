package com.vegantravels.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.vegantravels.utilities.AppLocker;
import com.vegantravels.utilities.ApplicationMode;
import com.vegantravels.utilities.ExceptionHandler;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by RAFI on 9/29/2016.
 */
// Common function create here for all Activity
public class BaseActivity extends Activity {


 SharedPreferences sp;
 SharedPreferences.Editor ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullScreencall();
        UiChangeListener();

        //Custom Error log handeled by Mail
        if (!ApplicationMode.devMode) {
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        }


        if(AppLocker.TrialModeOn) {
            sp = getSharedPreferences(AppLocker.PrefName, 0);
            ed = sp.edit();

            int stateLock = sp.getInt(AppLocker.ModeStartalarm, -1);

            if (stateLock == -1) {
                AppLocker.startAlarmForLockingApp(this, ed);
            } else {
                int appLock = sp.getInt(AppLocker.ModeStopApp, -1);

                if (appLock == 1)
                    finish();
            }
        }

    }

    // for centrally handled String type by Rokan
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        fullScreencall();
        UiChangeListener();

    }


    // Full Screen display
    public void fullScreencall() {
        if (Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    // Navigation bar control
    public void UiChangeListener() {
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {

    }


   /* @Override
    protected void onPause() {
        super.onPause();

        fullScreencall();
        UiChangeListener();

    }*/
}
