package com.tobin.top;

import android.view.View;

import com.tobin.top.base.BaseActivity;
import com.tobin.top.base.BaseFragment;
import com.tobin.top.utils.LogUtil;

import androidx.lifecycle.ViewModelProvider;

/**
 * @author lijunbin
 * @date 2020/9/16
 * @email 616041023@qq.com
 * @description MainActivity
 */
public class MainActivity extends BaseActivity<MainViewModel> {

    @Override
    protected MainViewModel initViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    protected void showError(Object obj) {

    }

    @Override
    protected int onCreate() {
        return R.layout.activity_main_layout;
    }

    @Override
    protected void initView() {
        findViewById(R.id.bt_recipes).setOnClickListener(v -> {
            viewModel.getRecipesLiveData().observe(this, recipesBean -> {
//                LogUtil.d(recipesBean.toString());
            });
        });
    }

    @Override
    protected void initData() {

    }
}
