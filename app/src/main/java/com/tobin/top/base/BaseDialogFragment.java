package com.tobin.top.base;

import com.tobin.top.R;

import androidx.fragment.app.DialogFragment;

/**
 * @author lijunbin
 * @date 2020/8/21
 * @email 616041023@qq.com
 * @description BaseDialogFragment
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
