package com.sxj.SeeWeather.modules.ui.setting;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sxj.SeeWeather.R;
import com.sxj.SeeWeather.base.BaseActivity;

/**
 * Created by hugo on 2016/2/19 0019.
 */
public class SettingActivity extends BaseActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setStatusBarColor(R.color.colorPrimary);
        if (mSetting.getCurrentHour()< 6 || mSetting.getCurrentHour() > 18) {
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSunset));
            setStatusBarColor(R.color.colorSunset);
        }
        getFragmentManager().beginTransaction().replace(R.id.framelayout, new SettingFragment()).commit();
    }
}
