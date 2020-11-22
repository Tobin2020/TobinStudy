package com.wechatdemo.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.wechatdemo.NetworkUtil
import com.wechatdemo.R

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.login_fragment, container, false)
        val btLogin: Button = root.findViewById(R.id.bt_login)
        val btRegister: Button = root.findViewById(R.id.bt_register)

        // 登录按钮
        btLogin.setOnClickListener{

            // 1、判断网络是否可用， 不可用Toast提示
            if (!NetworkUtil.isNetworkAvailable(requireContext())){
                Toast.makeText(requireContext(),"当前网络不可用!",Toast.LENGTH_LONG).show()
            }else{
                // 2、可用、跳转电话登录 国家_地区选择界面
                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_phoneLoginCheckFragment)
            }

        }

        // 注册按钮
        btRegister.setOnClickListener {
            // 本篇原型交互设计不包含【注册】相关功能及逻辑
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}