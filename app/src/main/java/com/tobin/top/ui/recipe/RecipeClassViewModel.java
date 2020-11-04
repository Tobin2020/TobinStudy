package com.tobin.top.ui.recipe;

import com.tobin.top.lifecycle.BaseViewModel;
import com.tobin.top.net.ApiManager;
import com.tobin.top.utils.LogUtil;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lijunbin
 * @date 2020/9/17
 * @email 616041023@qq.com
 * @description TobinStudy
 */
public class RecipeClassViewModel extends BaseViewModel {
    private MutableLiveData<RecipesBean> recipesLiveData;
    private MutableLiveData<RecipesClassBean> recipesClassLiveData;

    public RecipeClassViewModel() {
        recipesLiveData = new MutableLiveData<>();
        recipesClassLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<RecipesBean> getRecipesLiveData() {
        loadRecipes();
        return recipesLiveData;
    }

    public MutableLiveData<RecipesClassBean> getRecipesClassLiveData() {
        loadRecipesClass();
        return recipesClassLiveData;
    }

    public void loadRecipes() {
        Disposable disposable = ApiManager.recipesSearch("红烧肉")
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

    public void loadRecipesClass() {
        Disposable disposable = ApiManager.recipesClass()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(baseResult -> {
                    LogUtil.d("HomeViewModel loadRecipesClass baseResult: " + baseResult.toString());
                    recipesClassLiveData.postValue(baseResult.getResult());
                }, throwable -> {
                    LogUtil.e("HomeViewModel loadRecipesClass throwable: " + throwable.getMessage());
                    error.postValue(throwable.getMessage());
                });
        addDisposable(disposable);
    }

}
