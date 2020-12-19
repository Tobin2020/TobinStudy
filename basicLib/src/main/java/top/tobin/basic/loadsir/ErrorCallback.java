package top.tobin.basic.loadsir;

import com.kingja.loadsir.callback.Callback;

import top.tobin.basic.R;

public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_error;
    }
}
