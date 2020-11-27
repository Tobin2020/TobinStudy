package top.wechatdemo.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhoneSecureViewModel : ViewModel() {
    private val phoneSecureImp = MutableLiveData<String>().apply {
        value = "微信安全验证通过"
    }

    val phoneSecure: LiveData<String> = phoneSecureImp


}