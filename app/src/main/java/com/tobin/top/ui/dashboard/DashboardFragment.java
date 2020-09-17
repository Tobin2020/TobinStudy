package com.tobin.top.ui.dashboard;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.tobin.top.R;
import com.tobin.top.base.BaseLazyFragment;
import com.tobin.top.ui.recipe.RecipeActivity;

public class DashboardFragment extends BaseLazyFragment<DashboardViewModel> implements View.OnClickListener {

    @Override
    protected int onCreate() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.card_view_recipe).setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected DashboardViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
    }

    @Override
    protected void showError(Object obj) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.card_view_recipe){
            Intent intent = new Intent(getActivity(), RecipeActivity.class);
            getActivity().startActivity(intent);
        }
    }
}