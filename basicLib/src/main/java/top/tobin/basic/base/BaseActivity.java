package top.tobin.basic.base;

import top.tobin.basic.lifecycle.BaseViewModel;

public abstract class BaseActivity<VM extends BaseViewModel> extends BaseNoModelActivity {

    protected VM viewModel;

    @Override
    protected void onCreate(@androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
        viewModel = initViewModel();
        initObserve();
        super.onCreate(savedInstanceState);
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
