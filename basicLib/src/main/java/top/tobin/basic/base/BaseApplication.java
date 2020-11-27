package top.tobin.basic.base;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

import io.reactivex.plugins.RxJavaPlugins;
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
    }

}
