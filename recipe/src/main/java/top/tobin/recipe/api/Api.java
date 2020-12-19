package top.tobin.recipe.api;

import okhttp3.OkHttpClient;
import top.tobin.basic.net.BaseApi;
import top.tobin.basic.net.OkHttpInstance;

/**
 * @author lijunbin
 * @date 2020/8/18
 * @email
 * @description Api ,get a ApiStore instance
 */
class Api extends BaseApi {


    @Override
    protected final <T> Class getSer() {
        return Api.class;
    }

    private static class ApiHolder {
        private static Api api = new Api();
        private final static ApiStore apiService =
                api.initRetrofit(ApiStore.BASE_URL_DEVELOP).create(ApiStore.class);
    }

    public static ApiStore getInstance() {
        return ApiHolder.apiService;
    }


}

