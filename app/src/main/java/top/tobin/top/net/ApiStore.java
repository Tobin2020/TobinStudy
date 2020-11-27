package top.tobin.top.net;

import top.tobin.top.ui.recipe.RecipesBean;
import top.tobin.top.ui.recipe.RecipesClassBean;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import top.tobin.top.ui.recipe.RecipesBean;
import top.tobin.top.ui.recipe.RecipesClassBean;

public interface ApiStore {

    String BASE_URL_DEVELOP = "https://way.jd.com";  // 开发环境
    String BASE_URL_TEST = "";       // 测试环境
    String BASE_URL_RELEASE = "";       // 商用环境

    String appKey = "2a5f3669118e8a082a1697c6b6f73f9a";

    @GET("/jisuapi/search") // 菜谱查询
    Flowable<BaseResult<RecipesBean>> recipesSearch(@QueryMap Map<String, Object> body);

    @GET("/jisuapi/recipe_class") // 菜谱分类
    Flowable<BaseResult<RecipesClassBean>> recipesClass(@QueryMap Map<String, Object> body);

    @GET("/jisuapi/byclass") // 按分类检索
    Flowable<BaseResult<RecipesBean>> byRecipesClass(@QueryMap Map<String, Object> body);

    @GET("/jisuapi/detail") // 根据ID查询详情
    Flowable<BaseResult<RecipesClassBean>> recipesDetail(@QueryMap Map<String, Object> body);


    @Streaming
    @GET
    Observable<ResponseBody> startDownLoad(@Url String fileUrl);


    @POST
    Flowable<BaseResult<Object>> mPostRequest(@Url String url, @HeaderMap Map<String, Object> headers,
                                              @Body Map<String, Object> body);


}
