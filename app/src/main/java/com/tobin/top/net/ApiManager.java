package com.tobin.top.net;

import com.tobin.top.bean.RecipesBean;
import com.tobin.top.bean.RecipesClassBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;

public class ApiManager {

    public static Flowable<BaseResult<RecipesBean>> getRecipesSearch(String keyword) {

        Map<String, Object> bodyMaps = new HashMap<>();
        bodyMaps.put("appkey",ApiStore.appKey);
        bodyMaps.put("keyword",keyword);
        bodyMaps.put("num",1);

        return Api.getInstance().getRecipesSearch(bodyMaps);
    }

    public static Flowable<BaseResult<RecipesClassBean>> getRecipesClass() {
        Map<String, Object> bodyMaps = new HashMap<>();
        bodyMaps.put("appkey",ApiStore.appKey);
        return Api.getInstance().getRecipesClass(bodyMaps);
    }

}
