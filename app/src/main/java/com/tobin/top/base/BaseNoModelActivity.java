package com.tobin.top.base;

import android.content.Context;

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
