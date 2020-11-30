package top.tobin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThirdLoginViewModel : ViewModel() {
    private val _login = MutableLiveData<String>().apply {
        value = "登录成功"
    }

    val login: LiveData<String> = _login
}