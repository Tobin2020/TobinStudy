package top.wechatdemo.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import top.wechatdemo.MainActivity
import top.wechatdemo.R

/**
 * 微信号/QQ号/邮箱登录
 */
class ThirdLoginFragment : Fragment() {

    companion object {
        fun newInstance() = ThirdLoginFragment()
    }

    private lateinit var viewModel: ThirdLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.third_login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ivBack: ImageView = view.findViewById(R.id.iv_back)
        val etAccount: EditText = view.findViewById(R.id.et_account_input)
        val etPassword: EditText = view.findViewById(R.id.et_password_input)
        val btLogin: Button = view.findViewById(R.id.bt_third_login)
        val tvPhoneLogin: TextView = view.findViewById(R.id.tv_phone_login)

        // 返回
        ivBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        // 登录按钮
        btLogin.setOnClickListener {
            val account = etAccount.text
            val password = etPassword.text

            // TODO
            // 校验账号密码

            viewModel.login.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()

                // 登录成功
                startActivity(Intent().setClass(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            })
        }

        // 使用手机号码登录
        tvPhoneLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_thirdLoginFragment_to_phoneLoginCheckFragment)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ThirdLoginViewModel::class.java)

    }

}