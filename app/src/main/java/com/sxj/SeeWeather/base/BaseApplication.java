package com.sxj.SeeWeather.base;

import android.app.Application;
import android.content.Context;

import com.sxj.SeeWeather.common.CrashHandler;
import com.sxj.SeeWeather.component.RetrofitSingleton;

/**
 * Created by xcc on 2015/12/16.
 */
public class BaseApplication extends Application {
    public static String currentGirl = "http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg";

    public static String cacheDir = "";
    public static Context mAppContext = null;


    @Override public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        // 初始化 retrofit
        RetrofitSingleton.init(getApplicationContext());
        //GankRetrofit.init(getApplicationContext());
        CrashHandler.init(new CrashHandler(getApplicationContext()));
       // CrashReport.initCrashReport(getApplicationContext(), "900028220", false);

        //Thread.setDefaultUncaughtExceptionHandler(new MyUnCaughtExceptionHandler());

        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */

        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();

        }
        else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    private boolean ExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        }
        else {
            return false;
        }
    }
}
