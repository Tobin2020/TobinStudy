package top.tobin.amap.weather;

import android.os.Bundle;

import top.tobin.amap.R;
import top.tobin.basic.base.BaseNoModelActivity;


public class WeatherActivity extends BaseNoModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, WeatherFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    protected int onCreate() {
        return R.layout.weather_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}