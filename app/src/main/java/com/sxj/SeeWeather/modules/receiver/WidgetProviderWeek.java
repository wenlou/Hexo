package com.sxj.SeeWeather.modules.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.sxj.SeeWeather.R;
import com.sxj.SeeWeather.modules.service.UpdataAppwidgetService;

/**
 * Created by sxj52 on 2016/5/1.
 */
public class WidgetProviderWeek extends AppWidgetProvider {
    // widget
    private PendingIntent pendingIntent = null;

    // TAG
//    private final String TAG = "WidgetProviderWeek";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, UpdataAppwidgetService.class);
        if (pendingIntent == null) {
            pendingIntent = PendingIntent.getService(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int hour;
        long ONE_HOUR_TIME = 1000 * 60 * 60;
        String hourSave = sharedPreferences.getString(context.getString(R.string.key_widget_time),
                context.getString(R.string.widget_time_default));
        switch (hourSave) {
            case "1hour":
                hour = 1;
                break;
            case "2hours":
                hour = 2;
                break;
            case "3hours":
                hour = 3;
                break;
            case "4hours":
                hour = 4;
                break;
            default:
                hour = 2;
                break;
        }
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), hour * ONE_HOUR_TIME, pendingIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Intent intentRefresh = new Intent(context, UpdataAppwidgetService.class);
                context.startService(intentRefresh);
    }

    @Override
    public void onDisabled(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}
