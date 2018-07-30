package com.vegantravels.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.vegantravels.utilities.AppLocker;

public class OnBoot extends BroadcastReceiver {
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    @Override
    public void onReceive(Context ctx, Intent intent) {
        if(AppLocker.TrialModeOn) {
            sp = ctx.getSharedPreferences(AppLocker.PrefName, 0);
            ed = sp.edit();

            int stateLock = sp.getInt(AppLocker.ModeStartalarm, -1);

            int appLock = sp.getInt(AppLocker.ModeStopApp, -1);

            if(appLock!=1)//if app already lock dont start BR
            if (stateLock == -1) {
                AppLocker.startAlarmForLockingApp(ctx, ed);
            }
//            } else {
//                int appLock = sp.getInt(AppLocker.ModeStopApp, -1);
//
//                if (appLock == 1)
//                    finish();
//            }
        }
    }
}
