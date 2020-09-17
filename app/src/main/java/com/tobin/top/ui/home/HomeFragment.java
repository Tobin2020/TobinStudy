package com.tobin.top.ui.home;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tobin.top.R;
import com.tobin.top.base.BaseLazyFragment;
import com.tobin.top.ui.recipe.RecipeActivity;

public class HomeFragment extends BaseLazyFragment<HomeViewModel> implements View.OnClickListener {
    TextView textView;
    @Override
    protected int onCreate() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

        textView = view.findViewById(R.id.text_home);
    }

    @Override
    protected void initData() {
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected HomeViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    protected void showError(Object obj) {

    }
}