package top.tobin.top.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import top.tobin.basic.utils.LogUtil;

/**
 *  封装基础的Retrofit
 */
public abstract class BaseApi {

    /**
     * 初始化Retrofit
     */
    public Retrofit initRetrofit(String baseUrl) {
        LogUtil.d("BaseApi baseUrl: " + baseUrl);
        Retrofit.Builder builder = new Retrofit.Builder();
        //支持返回Call<String>
        builder.addConverterFactory(ScalarsConverterFactory.create());
        //支持直接格式化json返回Bean对象
        builder.addConverterFactory(GsonConverterFactory.create());
        //支持RxJava
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.baseUrl(baseUrl);
        OkHttpClient client = setClient();
        if (client != null) {
            builder.client(client);
        }
        return builder.build();
    }

    protected abstract OkHttpClient setClient();
}
