package com.tobin.top.ui.home;

import com.tobin.top.lifecycle.BaseViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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