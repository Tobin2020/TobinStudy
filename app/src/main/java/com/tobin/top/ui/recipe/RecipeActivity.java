package com.tobin.top.ui.recipe;

import android.os.Bundle;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.tobin.top.R;
import com.tobin.top.base.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
/**
 * @author lijunbin
 * @date 2020/9/17
 * @email 616041023@qq.com
 * @description 做菜
 */
public class RecipeActivity extends BaseActivity<RecipeViewModel> {
    private TextView textView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int onCreate() {
        return R.layout.activity_recipe;
    }

    @Override
    protected void initView() {
        textView = findViewById(R.id.tv_recipe);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);  //加载Toolbar控件
        ImmersionBar.with(this).titleBar(R.id.tool_bar).statusBarDarkFont(true).init();
    }

    @Override
    protected void initData() {
        viewModel.getRecipesClassLiveData().observe(this,recipesClassBean -> {
            if (recipesClassBean != null){
                textView.setText(recipesClassBean.toString());
            }
        });
    }

    @Override
    protected RecipeViewModel initViewModel() {
        return new ViewModelProvider(this).get(RecipeViewModel.class);
    }

    @Override
    protected void showError(Object obj) {

    }
}