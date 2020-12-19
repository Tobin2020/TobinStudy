package top.tobin.study.ui.dashboard;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import top.tobin.basic.base.BaseLazyFragment;
import top.tobin.basic.utils.LogUtil;
import top.tobin.basic.widgets.RecycleViewDivider;
import top.tobin.recipe.ui.RecipeClassActivity;
import top.tobin.study.R;
import top.tobin.study.ui.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends BaseLazyFragment<DashboardViewModel> {
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                getContext(), LinearLayoutManager.HORIZONTAL, 12, getResources().getColor(R.color.colorWhite)));
        adapter = new DashboardAdapter();
        adapter.setOnItemClickListener(itemClickListener);
        recyclerView.setAdapter(adapter);
    }

    private  OnItemClickListener itemClickListener = (view, position, data) -> {
        if (view.getId() == R.id.card_view_dashboard_item && position == 0){

            Intent intent = new Intent(getActivity(), RecipeClassActivity.class);
            activity.startActivity(intent);
        }else if (view.getId() == R.id.card_view_dashboard_item && position == 1){
//            Intent intent = new Intent(getActivity(), WeatherActivity.class);
//            activity.startActivity(intent);
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
        adapter.setData(funcList);
    }

    @Override
    protected DashboardViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
    }

    @Override
    protected void showError(Object obj) {

    }

}