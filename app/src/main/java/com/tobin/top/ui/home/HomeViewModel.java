package com.tobin.top.ui.home;

import com.tobin.top.bean.RecipesBean;
import com.tobin.top.bean.RecipesClassBean;
import com.tobin.top.lifecycle.BaseViewModel;
import com.tobin.top.net.ApiManager;
import com.tobin.top.utils.LogUtil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {
    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("敬请期待！");
    }

    public LiveData<String> getText() {
        return mText;
    }

}