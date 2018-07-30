package com.vegantravels.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.vegantravels.utilities.AppLocker;

public class StopApp extends BroadcastReceiver {
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    @Override
    public void onReceive(Context ctx, Intent intent) {
        if(AppLocker.TrialModeOn) {
            sp = ctx.getSharedPreferences(AppLocker.PrefName, 0);
            ed = sp.edit();


            //save it in shared pref
            ed.putInt(AppLocker.ModeStopApp, 1);
            ed.commit();
        }
    }
}
