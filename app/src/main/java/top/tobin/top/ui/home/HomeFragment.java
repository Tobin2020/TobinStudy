package top.tobin.top.ui.home;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tobin.top.R;

import java.util.HashMap;

import top.tobin.basic.base.BaseLazyFragment;
import top.tobin.webview.WebActivity;


public class HomeFragment extends BaseLazyFragment<HomeViewModel> implements View.OnClickListener {
    TextView textView;
    @Override
    protected int onCreate() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

        textView = view.findViewById(R.id.text_home);

        Button test = view.findViewById(R.id.bt_test);
        test.setOnClickListener(v -> {
//            WebActivity.startCommonWeb(view.getContext(), "集合泛型与操作符", "https://mp.weixin.qq.com/s/z69R1dI8zZw007GE8lm_kg");

            HashMap<String, String> header =new HashMap<>();
            header.put("test","test");
            WebActivity.startAccountWeb(view.getContext(), "集合泛型与操作符", "https://mp.weixin.qq.com/s/z69R1dI8zZw007GE8lm_kg",header);
        });
    }

    @Override
    protected void initData() {
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected HomeViewModel initViewModel() {
        return new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    protected void showError(Object obj) {

    }
}