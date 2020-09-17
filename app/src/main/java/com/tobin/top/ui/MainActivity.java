package com.tobin.top.ui;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tobin.top.R;
import com.tobin.top.base.BaseActivity;
import com.tobin.top.lifecycle.BaseViewModel;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemTextAppearanceInactive(0);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // 没有actionbar
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
    //            resetToDefaultIcon();//重置到默认不选中图片
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        //在这里替换图标
    //                    item.setIcon(R.mipmap.ic_home_selected);
                        return true;

                }
                return false;
            };

    @Override
    protected int onCreate() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BaseViewModel initViewModel() {
        return null;
    }

    @Override
    protected void showError(Object obj) {

    }

}