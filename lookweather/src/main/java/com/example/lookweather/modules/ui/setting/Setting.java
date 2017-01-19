package com.example.lookweather.modules.ui.setting;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import com.xiecc.lookWeather.base.BaseApplication;

/**
 * Created by hugo on 2016/2/19 0019.
 *
 * 设置相关 包括 sp 的写入
 */
public class Setting {

    public static final String CHANGE_ICONS = "change_icons";//切换图标
    public static final String CLEAR_CACHE = "clear_cache";//清空缓存
    public static final String AUTO_UPDATE = "change_update_time"; //自动更新时长
    public static final String CITY_NAME = "城市";//选择城市
    public static final String HOUR = "current_hour";//当前小时
    public static final String NOTIFICATION_MODEL = "notification_model";

    //public static final String API_TOKEN = "7db041d0c3013b63e4bed2a554f02d85";//fir.im api_token
    public static final String KEY = "e2faff6b359440c288e7fb675df53d22";// 和风天气 key
    public static final String MULTI_CHECK = "multi_check";
    public static int ONE_HOUR = 1000 * 60 * 60;

    private static com.xiecc.lookWeather.modules.ui.setting.Setting sInstance;

    private SharedPreferences mPrefs;

    public static com.xiecc.lookWeather.modules.ui.setting.Setting getInstance() {
        if (sInstance == null) {
            sInstance = new com.xiecc.lookWeather.modules.ui.setting.Setting(BaseApplication.mAppContext);
        }
        return sInstance;
    }

    private Setting(Context context) {
        mPrefs = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        //mPrefs.edit().putInt(CHANGE_ICONS, 1).apply();
    }


    public com.xiecc.lookWeather.modules.ui.setting.Setting putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }

    public com.xiecc.lookWeather.modules.ui.setting.Setting putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
        return this;
    }

    public String getString(String key, String defValue) {
        return mPrefs.getString(key, defValue);
    }


    // 设置当前小时
    public void setCurrentHour(int h){
        mPrefs.edit().putInt(HOUR,h).apply();
    }
    public int getCurrentHour(){
        return mPrefs.getInt(HOUR,0);
    }

    // 图标种类相关
    public void setIconType(int type) {
        mPrefs.edit().putInt(CHANGE_ICONS, type).apply();
    }

    public int getIconType() {
        return mPrefs.getInt(CHANGE_ICONS, 0);
    }

    // 自动更新时间 hours
    public void setAutoUpdate(int t) {
        mPrefs.edit().putInt(AUTO_UPDATE, t).apply();
    }

    public int getAutoUpdate() {
        return mPrefs.getInt(AUTO_UPDATE, 3);
    }

    //当前城市
    public void setCityName(String name) {
        mPrefs.edit().putString(CITY_NAME, name).apply();
    }

    public String getCityName() {return mPrefs.getString(CITY_NAME, "济南");}

    //  通知栏模式 默认为常驻
    public void setNotificationModel(int t) {
        mPrefs.edit().putInt(NOTIFICATION_MODEL, t).apply();
    }
    public int getNotificationModel() {
        return mPrefs.getInt(NOTIFICATION_MODEL, Notification.FLAG_AUTO_CANCEL);
    }
}
