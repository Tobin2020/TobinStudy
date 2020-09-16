package com.tobin.top.net;

import com.tobin.top.bean.RecipesBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;

public class ApiManager {

    public static Flowable<BaseResult<RecipesBean>> getRecipesBean(String keyword) {

        Map<String, Object> bodyMaps = new HashMap<>();
        bodyMaps.put("appkey",ApiStore.appKey);
        bodyMaps.put("keyword",keyword);
        bodyMaps.put("num",20);

        return Api.getInstance().getRecipesBean(bodyMaps);
    }

}
