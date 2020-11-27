package top.tobin.basic.base;

import androidx.fragment.app.DialogFragment;

import top.tobin.basic.R;

/**
 * @author lijunbin
 * @date 2020/8/21
 * @email 616041023@qq.com
 * @description DialogFragment基类
 */
public class BaseDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@androidx.annotation.Nullable android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.fragment_dialog_style);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().setCanceledOnTouchOutside(false);
    }
}
