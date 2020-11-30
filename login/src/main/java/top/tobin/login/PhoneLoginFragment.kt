package top.tobin.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

class PhoneLoginFragment : Fragment() {

    companion object {
        fun newInstance() = PhoneLoginFragment()
    }

    private lateinit var viewModel: PhoneLoginViewModel
    private var passwordLogin = true // 默认使用密码登录

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.phone_login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivBack: ImageView = view.findViewById(R.id.iv_back)
        val btLogin: Button = view.findViewById(R.id.bt_phone_login)
        val etPhone: EditText = view.findViewById(R.id.et_country_number)
        val tvLoginWay: TextView = view.findViewById(R.id.tv_phone_login_way)
        val tvSafetyCode: TextView = view.findViewById(R.id.tv_get_safety_code)
        val tvPassOrCode: TextView = view.findViewById(R.id.tv_password_or_code)

        // 获取上一页面中填写并验证通过的手机号
        val phoneNumber = arguments?.getString("phone")
        etPhone.setText(phoneNumber)

        // 返回
        ivBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        // 登录按钮
        btLogin.setOnClickListener {
            viewModel.phoneLogin.observe(viewLifecycleOwner, Observer {
//                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()

                if (passwordLogin){
                    // 电话号码和密码登录 跳转安全验证页面
                    Navigation.findNavController(btLogin)
                        .navigate(R.id.action_phoneLoginFragment_to_phoneSecureFragment)
                }else{
                    // 电话号码和短信验证码登录
                    startActivity(Intent().setClass(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }

            })
        }

        // 切换登录方式 短信验证码登录 or 密码登录
        tvLoginWay.setOnClickListener {
            if (passwordLogin) {
                passwordLogin = false
                tvLoginWay.text = "用密码登录"
                tvSafetyCode.visibility = View.VISIBLE
                tvPassOrCode.text = "验证码"
            } else {
                passwordLogin = true
                tvLoginWay.text = "用短信验证码登录"
                tvPassOrCode.text = "密码"
                tvSafetyCode.visibility = View.GONE
            }
        }

        // 验证码倒计时
        tvSafetyCode.setOnClickListener {
            val countDownTimer = object : CountDownTimer(30 * 1000,1000){
                override fun onFinish() {
                    tvSafetyCode.text = "获取验证码"
                }

                override fun onTick(millisUntilFinished: Long) {
                    val time = (millisUntilFinished / 1000) + 1
                    tvSafetyCode.text = "${time}s"
                }
            }

            countDownTimer.start()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhoneLoginViewModel::class.java)
    }

}