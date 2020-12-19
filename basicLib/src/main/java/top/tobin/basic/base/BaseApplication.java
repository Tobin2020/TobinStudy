package top.tobin.basic.base;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kingja.loadsir.core.LoadSir;

import io.reactivex.plugins.RxJavaPlugins;
import top.tobin.basic.loadsir.CustomCallback;
import top.tobin.basic.loadsir.ErrorCallback;
import top.tobin.basic.loadsir.LottieEmptyCallback;
import top.tobin.basic.loadsir.LottieLoadingCallback;
import top.tobin.basic.loadsir.TimeoutCallback;
import top.tobin.basic.utils.LogUtil;

/**
 * @author lijunbin
 * @date 2020/7/2
 * @email 616041023@qq.com
 * @description BaseApplication
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RxJavaPlugins.setErrorHandler(throwable -> {
            LogUtil.d("BaseApplication RxJavaPlugins throwable: " + throwable.getMessage());
        });

        initNetworkMonitor();

        // 初始化ARouter
        if (true) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(this);

        // 设置LoadSir的各类状态View
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new LottieEmptyCallback())
                .addCallback(new LottieLoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LottieLoadingCallback.class)
                .commit();
    }

    private void initNetworkMonitor(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        connectivityManager.requestNetwork(new NetworkRequest.Builder().build(), new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                connectivityManager.unregisterNetworkCallback(this);


            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
