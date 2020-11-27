package top.wechatdemo.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhoneLoginCheckViewModel : ViewModel() {
    private val phoneCheckImp = MutableLiveData<String>().apply {
        value = "手机号码和地区校验通过"
    }

    val phoneCheck: LiveData<String> = phoneCheckImp
}