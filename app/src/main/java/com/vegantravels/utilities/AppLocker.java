package com.vegantravels.utilities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.vegantravels.receivers.StopApp;

public class AppLocker {
    public static int OneHour=3600000;
    public static long Trialperiod= OneHour*6;
    public static int ServiceCode=11;
    public static String ModeStartalarm="trialApp";

    public static boolean TrialModeOn = true;
    //used from br
    public static String ModeStopApp="stopApp";

    public static String PrefName="applockerpref";


    public static void startAlarmForLockingApp(Context ctx, @NonNull SharedPreferences.Editor ed){

            AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(ctx, StopApp.class);
            PendingIntent pendingIntent;

            pendingIntent = PendingIntent.getBroadcast(ctx, ServiceCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            long l=System.currentTimeMillis();
            alarmManager.setRepeating(AlarmManager.RTC, l+Trialperiod,Trialperiod,
                    pendingIntent);


            //save it in shared pref
            ed.putInt(ModeStartalarm,1);
            ed.commit();
        }
    }


