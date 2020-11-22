package com.tobin.top.ui.weather;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.View;
import android.widget.TextView;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.weather.LocalDayWeatherForecast;
import com.amap.api.services.weather.LocalWeatherForecast;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;

import com.tobin.top.R;
import com.tobin.top.base.BaseApplication;
import com.tobin.top.base.BaseLazyFragment;
import com.tobin.top.widgets.CustomToast;

import java.util.List;

public class WeatherFragment extends BaseLazyFragment<WeatherViewModel> implements WeatherSearch.OnWeatherSearchListener {

    private WeatherViewModel mViewModel;

    private TextView forecasttv;
    private TextView reporttime1;
    private TextView reporttime2;
    private TextView weather;
    private TextView Temperature;
    private TextView wind;
    private TextView humidity;

    private LocalWeatherLive weatherlive;

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int onCreate() {
        return R.layout.weather_fragment;
    }

    @Override
    protected void initView(View view) {
        TextView city = (TextView) view.findViewById(R.id.city);
        city.setText("湖南");
        forecasttv = view.findViewById(R.id.forecast);
        reporttime1 = view.findViewById(R.id.reporttime1);
        reporttime2 = view.findViewById(R.id.reporttime2);
        weather = view.findViewById(R.id.weather);
        Temperature = view.findViewById(R.id.temp);
        wind = view.findViewById(R.id.wind);
        humidity = view.findViewById(R.id.humidity);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected WeatherViewModel initViewModel() {
        return new ViewModelProvider(this).get(WeatherViewModel.class);
    }

    @Override
    protected void showError(Object obj) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private WeatherSearchQuery mQuery;
    private WeatherSearch mWeatherSearch;


    @Override
    protected void lazyLoad() {

        searchliveweather();
        searchforcastsweather();
    }

    /**
     * 预报天气查询
     */
    private void searchforcastsweather() {
        mQuery = new WeatherSearchQuery("湖南", WeatherSearchQuery.WEATHER_TYPE_FORECAST);//检索参数为城市和天气类型，实时天气为1、天气预报为2
        mWeatherSearch = new WeatherSearch(activity);
        mWeatherSearch.setOnWeatherSearchListener(this);
        mWeatherSearch.setQuery(mQuery);
        mWeatherSearch.searchWeatherAsyn(); //异步搜索
    }

    /**
     * 实时天气查询
     */
    private void searchliveweather() {
        mQuery = new WeatherSearchQuery("湖南", WeatherSearchQuery.WEATHER_TYPE_LIVE);//检索参数为城市和天气类型，实时天气为1、天气预报为2
        mWeatherSearch = new WeatherSearch(activity);
        mWeatherSearch.setOnWeatherSearchListener(this);
        mWeatherSearch.setQuery(mQuery);
        mWeatherSearch.searchWeatherAsyn(); //异步搜索
    }

    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult weatherLiveResult, int rCode) {
        if (rCode == 1000) {
            if (weatherLiveResult != null&&weatherLiveResult.getLiveResult() != null) {
                weatherlive = weatherLiveResult.getLiveResult();
                reporttime1.setText(weatherlive.getReportTime()+"发布");
                weather.setText(weatherlive.getWeather());
                Temperature.setText(weatherlive.getTemperature()+"°");
                wind.setText(weatherlive.getWindDirection()+"风     " + weatherlive.getWindPower()+"级");
                humidity.setText("湿度         "+weatherlive.getHumidity()+"%");
            }else {
                CustomToast.makeText(BaseApplication.getInstance(), "R.string.no_result", CustomToast.LENGTH_LONG);
            }
        }else {
            CustomToast.makeText(BaseApplication.getInstance(), "showerror" + rCode, CustomToast.LENGTH_LONG);
        }
    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult weatherForecastResult, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (weatherForecastResult != null && weatherForecastResult.getForecastResult() != null
                    && weatherForecastResult.getForecastResult().getWeatherForecast() != null
                    && weatherForecastResult.getForecastResult().getWeatherForecast().size() > 0) {
                weatherforecast = weatherForecastResult.getForecastResult();
                forecastlist = weatherforecast.getWeatherForecast();
                fillForecast();

            } else {
                CustomToast.makeText(BaseApplication.getInstance(), "R.string.no_result", CustomToast.LENGTH_LONG);
            }
        } else {
            CustomToast.makeText(BaseApplication.getInstance(), "showerror" + rCode, CustomToast.LENGTH_LONG);
        }
    }

    private List<LocalDayWeatherForecast> forecastlist = null;
    private LocalWeatherForecast weatherforecast;


    private void fillForecast() {
        reporttime2.setText(weatherforecast.getReportTime() + "发布");
        String forecast = "";
        for (int i = 0; i < forecastlist.size(); i++) {
            LocalDayWeatherForecast localdayweatherforecast = forecastlist.get(i);
            String week = null;
            switch (Integer.valueOf(localdayweatherforecast.getWeek())) {
                case 1:
                    week = "周一";
                    break;
                case 2:
                    week = "周二";
                    break;
                case 3:
                    week = "周三";
                    break;
                case 4:
                    week = "周四";
                    break;
                case 5:
                    week = "周五";
                    break;
                case 6:
                    week = "周六";
                    break;
                case 7:
                    week = "周日";
                    break;
                default:
                    break;
            }
            String temp = String.format("%-3s/%3s",
                    localdayweatherforecast.getDayTemp() + "°",
                    localdayweatherforecast.getNightTemp() + "°");
            String date = localdayweatherforecast.getDate();
            forecast += date + "  " + week + "                       " + temp + "\n\n";
        }
        forecasttv.setText(forecast);
    }


}