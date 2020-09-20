package com.tobin.top.ui.dashboard;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tobin.top.R;
import com.tobin.top.base.BaseLazyFragment;
import com.tobin.top.ui.OnItemClickListener;
import com.tobin.top.ui.recipe.RecipeActivity;
import com.tobin.top.utils.LogUtil;
import com.tobin.top.widgets.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends BaseLazyFragment<DashboardViewModel>{
    private List<DashboardBean> funcList = new ArrayList<>();
    RecyclerView recyclerView;
    DashboardAdapter adapter;
    @Override
    protected int onCreate() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void initView(View view) {
        LogUtil.d("DashboardFragment initView");
        recyclerView = view.findViewById(R.id.rcv_dashboard);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                getContext(), LinearLayoutManager.HORIZONTAL, 12, getResources().getColor(R.color.colorWhite)));
        adapter = new DashboardAdapter(funcList);
        adapter.setOnItemClickListener(itemClickListener);
    }

    private  OnItemClickListener itemClickListener = (rv, view, position, data) -> {
        if (view.getId() == R.id.card_view_dashboard_item && position == 0){
            Intent intent = new Intent(getActivity(), RecipeActivity.class);
            requireActivity().startActivity(intent);
        }
    };

    @Override
    protected void initData() {
        LogUtil.d("DashboardFragment initData");
        initDashboard();
    }

    @Override
    protected void lazyLoad() {

    }

    private void initDashboard() {
        DashboardBean apple = new DashboardBean("菜谱", R.mipmap.recipes);
        funcList.add(apple);
        DashboardBean banana = new DashboardBean("天气", R.mipmap.weather);
        funcList.add(banana);
        DashboardBean orange = new DashboardBean("新闻", R.mipmap.news);
        funcList.add(orange);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected DashboardViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
    }

    @Override
    protected void showError(Object obj) {

    }

}