package com.wechatdemo.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhoneLoginViewModel : ViewModel() {
    private val phoneLoginImp = MutableLiveData<String>().apply {
        value = "手机号码登录成功"
    }

    val phoneLogin: LiveData<String> = phoneLoginImp
}