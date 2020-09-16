package com.tobin.top;

import com.tobin.top.bean.RecipesBean;
import com.tobin.top.lifecycle.BaseViewModel;
import com.tobin.top.net.ApiManager;
import com.tobin.top.utils.LogUtil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lijunbin
 * @date 2020/9/16
 * @email lijunb@szlanyou.com
 * @description TobinStudy
 */
public class MainViewModel extends BaseViewModel {
    private MutableLiveData<RecipesBean> recipesLiveData;

    public MutableLiveData<RecipesBean> getRecipesLiveData() {
        if (recipesLiveData == null) {
            recipesLiveData = new MutableLiveData<>();
            loadRecipes();
        }
        return recipesLiveData;
    }

    public void loadRecipes() {
        Disposable disposable = ApiManager.getRecipesBean("红烧肉")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(baseResult -> {
                    LogUtil.d("MainViewModel loadRecipes baseResult: " + baseResult.toString());
                    recipesLiveData.postValue(baseResult.getResult());
                }, throwable -> {
                    LogUtil.e("AuthViewModel loadQrCode throwable: " + throwable.getMessage());
                    error.postValue(throwable.getMessage());
                });
        addDisposable(disposable);
    }



}
