package com.tobin.top.ui.recipe;

import androidx.lifecycle.MutableLiveData;

import com.tobin.top.lifecycle.BaseViewModel;
import com.tobin.top.net.ApiManager;
import com.tobin.top.utils.LogUtil;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lijunbin
 * @date 2020/9/17
 * @email 616041023@qq.com
 * @description TobinStudy
 */
public class RecipeViewModel extends BaseViewModel {
    private MutableLiveData<RecipesBean> recipesLiveData;

    public RecipeViewModel() {
        recipesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<RecipesBean> getRecipesLiveData() {
        return recipesLiveData;
    }

    public void byRecipesClass(int classId, int start, int num) {
        Disposable disposable = ApiManager.byRecipesClass(classId, start, num)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(baseResult -> {
                    LogUtil.d("HomeViewModel loadRecipes baseResult: " + baseResult.toString());
                    recipesLiveData.postValue(baseResult.getResult());
                }, throwable -> {
                    LogUtil.e("HomeViewModel loadRecipes throwable: " + throwable.getMessage());
                    error.postValue(throwable.getMessage());
                });
        addDisposable(disposable);
    }

}
