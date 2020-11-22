package com.tobin.top.ui.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tobin.top.R;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, WeatherFragment.newInstance())
                    .commitNow();
        }
    }
}