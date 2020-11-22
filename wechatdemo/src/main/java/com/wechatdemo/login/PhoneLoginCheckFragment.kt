package com.wechatdemo.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.wechatdemo.NetworkUtil
import com.wechatdemo.R

/**
 * 电话登录
 */
class PhoneLoginCheckFragment : Fragment() {

    companion object {
        fun newInstance() = PhoneLoginCheckFragment()
    }

    private lateinit var viewModel: PhoneLoginCheckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.phone_login_check_fragment, container, false)
        val ivBack: ImageView = root.findViewById(R.id.iv_back)
        val etPhone: EditText = root.findViewById(R.id.et_phone_number)
        // 返回
        ivBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp();
        }

        // 用微信号/QQ号/邮箱登录
        val tvThirdLogin: TextView = root.findViewById(R.id.tv_phone_login)
        tvThirdLogin.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_phoneLoginCheckFragment_to_thirdLoginFragment)
        }

        // 下一步
        val btStep: Button = root.findViewById(R.id.bt_phone_next)
        btStep.setOnClickListener {

            if (!NetworkUtil.isNetworkAvailable(requireContext())){
                Toast.makeText(requireContext(),"当前网络不可用!", Toast.LENGTH_LONG).show()
            }else{
                //
                viewModel.phoneCheck.observe(viewLifecycleOwner, Observer {
                    val bundle = Bundle()
                    if (TextUtils.isEmpty(etPhone.text.toString())){
                        bundle.putString("phone", "13811112222")
                    }else{
                        bundle.putString("phone", etPhone.text.toString())
                    }

                    Navigation.findNavController(btStep).navigate(R.id.action_phoneLoginCheckFragment_to_phoneLoginFragment, bundle)
                })
            }

        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhoneLoginCheckViewModel::class.java)
    }

}