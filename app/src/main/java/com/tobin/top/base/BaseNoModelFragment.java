package com.tobin.top.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;

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
