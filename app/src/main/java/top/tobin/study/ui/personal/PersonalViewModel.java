package top.tobin.study.ui.personal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import top.tobin.basic.lifecycle.BaseViewModel;

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