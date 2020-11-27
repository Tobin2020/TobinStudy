package top.tobin.basic.base;

import android.app.Activity;
import android.content.Context;

import top.tobin.basic.lifecycle.BaseViewModel;

public abstract class BaseFragment<VM extends BaseViewModel> extends BaseNoModelFragment {
    protected Activity activity;
    protected VM viewModel;

    @Override
    public void onActivityCreated(@androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
        viewModel = initViewModel();
        initObserve();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    /**
     * 初始化ViewModel
     */
    protected abstract VM initViewModel();

    /**
     * 监听当前ViewModel中 showDialog和error的值
     */
    private void initObserve() {
        if (viewModel == null) return;
        viewModel.getError(this, new androidx.lifecycle.Observer<Object>() {
            @Override
            public void onChanged(Object obj) {
                showError(obj);
            }
        });
    }

    /**
     * ViewModel层发生了错误
     */
    protected abstract void showError(Object obj);
}
