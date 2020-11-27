package top.tobin.top.net;

import okhttp3.OkHttpClient;

/**
 * @author lijunbin
 * @date 2020/8/18
 * @email
 * @description Api ,get a ApiStore instance
 */
class Api extends BaseApi {

    private static class ApiHolder {
        private static Api api = new Api();
        private final static ApiStore apiService =
                api.initRetrofit(ApiStore.BASE_URL_DEVELOP).create(ApiStore.class);
    }

    public static ApiStore getInstance() {
        return ApiHolder.apiService;
    }

    @Override
    protected OkHttpClient setClient() {
        return OkHttpInstance.get();
    }

}

