package top.tobin.basic.base;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;

import top.tobin.basic.common.ActivityCollector;

/**
 * 不需要ViewModel的页面基类
 */
public abstract class BaseNoModelActivity extends AppCompatActivity {

    protected Context context;

    @Override
    protected void onCreate(@androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ActivityCollector.getInstance().addActivity(this);
        setContentView(onCreate());

        initView();
        initData();

        initImmersionBar();
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        ImmersionBar.with(this).init();
    }

    /**
     * 初始化要加载的布局资源ID
     * 此函数优先执行于onCreate()可以做window操作
     */
    protected abstract int onCreate();


    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        ActivityCollector.getInstance().removeActivity(this);
        super.onDestroy();
    }
}
