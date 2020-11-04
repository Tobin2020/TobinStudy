package com.tobin.top.ui.recipe;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.tobin.top.R;
import com.tobin.top.base.BaseActivity;
import com.tobin.top.utils.LogUtil;
import com.tobin.top.widgets.RecycleViewDivider;

public class RecipeActivity extends BaseActivity<RecipeViewModel> {

    public static final String INTENT_DATA = "class_id";
    private RecipesBean recipesBean;
    private  RecipesClassBean.ResultBean.ListBean listBean;

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;

    @Override
    protected RecipeViewModel initViewModel() {
        return new ViewModelProvider(this).get(RecipeViewModel.class);
    }

    @Override
    protected void showError(Object obj) {

    }

    @Override
    protected int onCreate() {
        return R.layout.activity_recipe;
    }

    @Override
    protected void initView() {
        Intent intent =getIntent();
        listBean = (RecipesClassBean.ResultBean.ListBean) intent.getSerializableExtra(INTENT_DATA);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        if (listBean != null) toolbar.setTitle(listBean.getName());
        setSupportActionBar(toolbar);  //加载Toolbar控件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.rcv_recipe);
        ImmersionBar.with(this).titleBar(R.id.tool_bar).statusBarDarkFont(true).init();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 12, getResources().getColor(R.color.colorWhite)));
        adapter = new RecipeAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        if (listBean != null) {
            viewModel.byRecipesClass(Integer.parseInt(listBean.getClassid()), 0, 20);
            viewModel.getRecipesLiveData().observe(this, recipesBean -> {
                if (recipesBean != null) {
                    this.recipesBean = recipesBean;
                    adapter.addData(recipesBean.getResult().getList());
                    LogUtil.d("RecipeActivity initData recipesBean: " + recipesBean.toString());
                }

            });
        }
    }
}
