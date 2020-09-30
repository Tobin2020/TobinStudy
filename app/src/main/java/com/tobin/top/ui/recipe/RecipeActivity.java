package com.tobin.top.ui.recipe;

import android.os.Bundle;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.tobin.top.R;
import com.tobin.top.base.BaseActivity;
import com.tobin.top.bean.RecipesClassBean;
import com.tobin.top.ui.OnItemClickListener;
import com.tobin.top.utils.LogUtil;
import com.tobin.top.widgets.CustomToast;
import com.tobin.top.widgets.RecycleViewDivider;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author lijunbin
 * @date 2020/9/17
 * @email 616041023@qq.com
 * @description 做菜
 */
public class RecipeActivity extends BaseActivity<RecipeViewModel> {
    private TextView textView;
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    RecipeClassAdapter adapter;
    private RecipesClassBean recipesClassBean;

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
        recyclerView = findViewById(R.id.rcv_recipe_class);
        textView = findViewById(R.id.tv_recipe);
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("菜谱");
        setSupportActionBar(toolbar);  //加载Toolbar控件
        ImmersionBar.with(this).titleBar(R.id.tool_bar).statusBarDarkFont(true).init();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 12, getResources().getColor(R.color.colorWhite)));
        adapter = new RecipeClassAdapter();
        adapter.setOnItemClickListener(itemClickListener);
        recyclerView.setAdapter(adapter);
    }

    private OnItemClickListener itemClickListener = (rv, view, position, data) -> {
        LogUtil.d("RecipeActivity position: " + position);
    };

    @Override
    protected void initData() {
        viewModel.getRecipesClassLiveData().observe(this, recipesClassBean -> {
            adapter.setData(recipesClassBean);
        });
    }

    @Override
    protected RecipeViewModel initViewModel() {
        return new ViewModelProvider(this).get(RecipeViewModel.class);
    }

    @Override
    protected void showError(Object obj) {
        CustomToast.makeText(this, obj == null ? "" : obj.toString(), CustomToast.LENGTH_SHORT);
    }
}