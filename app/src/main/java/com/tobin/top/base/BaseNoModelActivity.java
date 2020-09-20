package com.tobin.top.base;

import android.content.Context;

import com.gyf.immersionbar.ImmersionBar;
import com.tobin.top.common.ActivityCollector;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 不需要ViewModel的页面基类
 */
public abstract class BaseNoModelActivity extends AppCompatActivity {

    protected Context context;

    @Override
    protected void onCreate(@androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ActivityCollector.getInstance().addActivity(this);
        setContentView(onCreate());

        initView();
        initData();

        initImmersionBar();
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        ImmersionBar.with(this)
//                .titleBar(this)    //解决状态栏和布局重叠问题
//                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
//                .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
//                .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
//                .autoStatusBarDarkModeEnable(true,0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
//                .autoNavigationBarDarkModeEnable(true,0.2f) //自动导航栏图标变色，必须指定导航栏颜色才可以自动变色哦
                .init();
    }

    /**
     * 初始化要加载的布局资源ID
     * 此函数优先执行于onCreate()可以做window操作
     */
    protected abstract int onCreate();


    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        ActivityCollector.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
