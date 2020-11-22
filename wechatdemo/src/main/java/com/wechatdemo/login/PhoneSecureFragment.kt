package com.wechatdemo.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.wechatdemo.MainActivity
import com.wechatdemo.R

/**
 * 电话号码和密码登录 弹出安全验证界面
 *
 */
class PhoneSecureFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    companion object {
        fun newInstance() = PhoneSecureFragment()
    }

    private lateinit var viewModel: PhoneSecureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.phone_secure_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ivBack: ImageView = view.findViewById(R.id.iv_back)
        val sbSwipe: SeekBar = view.findViewById(R.id.sb_swipe_secure)

        ivBack.setOnClickListener {
            Navigation.findNavController(it).navigateUp();
        }
        sbSwipe.setOnSeekBarChangeListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhoneSecureViewModel::class.java)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (progress == 100){
            // 安全码验证监听
            viewModel.phoneSecure.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()

                startActivity(Intent().setClass(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            })
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

}