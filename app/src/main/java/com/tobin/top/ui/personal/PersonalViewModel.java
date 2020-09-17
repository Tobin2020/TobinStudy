package com.tobin.top.ui.personal;

import com.tobin.top.lifecycle.BaseViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PersonalViewModel extends BaseViewModel {

    private MutableLiveData<String> mText;

    public PersonalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Personal fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}