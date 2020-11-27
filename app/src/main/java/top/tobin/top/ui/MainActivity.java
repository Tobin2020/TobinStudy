package top.tobin.top.ui;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gyf.immersionbar.ImmersionBar;
import com.tobin.top.R;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import top.tobin.basic.base.BaseActivity;
import top.tobin.basic.lifecycle.BaseViewModel;

public class MainActivity extends BaseActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this).titleBar(R.id.tool_bar).statusBarDarkFont(true).init();
    }

    @Override
    protected int onCreate() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);  //加载Toolbar控件


        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemTextAppearanceInactive(0);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // 没有actionbar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
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