package top.tobin.top.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import top.tobin.basic.lifecycle.BaseViewModel;

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