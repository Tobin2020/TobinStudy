package com.tobin.top.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;

import com.gyf.immersionbar.ImmersionBar;

public abstract class BaseNoModelFragment extends androidx.fragment.app.Fragment {

    protected Context context;
    protected FragmentActivity activity;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @androidx.annotation.Nullable
    @Override
    public android.view.View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(onCreate(), container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
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
     */
    protected abstract int onCreate();


    /**
     * 初始化视图
     */
    protected abstract void initView(android.view.View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

}
