package top.tobin.basic.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import top.tobin.basic.R;

/**
 * @author Tobin
 * @date 2020/7/17
 * @email 616041023@qq.com
 * @description 自定义Toast
 */
public class CustomToast {
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;

    public static final int SUCCESS = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;
    public static final int CONFUSING = 4;
    public static final int INFO = 5;
    public static final int DEFAULT = 6;

    public static final int TOP_DOWN = 1;
    public static final int LEFT_RIGHT = 2;
    public static final int SCALE = 3;

    private static BaseToast mToast;

    private static BaseToast instance(Context context, int animation) {
        if (mToast == null) {
            synchronized (BaseToast.class) {
                if (mToast == null)
                    mToast = new BaseToast(context, animation);
            }
        }
        mToast.init();
        return mToast;
    }

    public static void makeText(Context context, String msg, int length) {
        makeText(context, msg, length, DEFAULT, 0);
    }

    /**
     * no pop Anim
     *
     * @param context context
     * @param msg     吐司消息
     * @param length  LENGTH_SHORT or LENGTH_LONG
     * @param type    SUCCESS,WARNING,ERROR,CONFUSING,INFO,DEFAULT
     */
    public static void makeText(Context context, String msg, int length, int type) {
        makeText(context, msg, length, type, 0);
    }

    /**
     * @param context  Context
     * @param msg      吐司消息
     * @param length   显示时间长度
     * @param type     6 type
     * @param showAnim animStyle  默认无(TOP_DOWN,LEFT_RIGHT,SCALE)
     */
    public static void makeText(Context context, String msg, int length, int type, int showAnim) {
//        if (showAnim == TOP_DOWN) {
//            mToast = instance(context, R.style.toastTopDown);
//        }else if (showAnim == LEFT_RIGHT) {
//            mToast = instance(context, R.style.toastLeftRight);
//        } else if (showAnim == SCALE) {
//            mToast = instance(context, R.style.toastScale);
//        } else {
//            mToast = instance(context, showAnim);
//        }
        mToast = instance(context, showAnim);

        View layout = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.toast_custom_layout, null, false);

        ImageView icon = layout.findViewById(R.id.toastIcon);
        TextView text = layout.findViewById(R.id.toastMessage);
        text.setText(msg);
        mToast.setDuration(length);
        switch (type) {
            case SUCCESS:
            case WARNING:
            case ERROR:
            case CONFUSING:
            case INFO:
            case DEFAULT:
            default:
                icon.setImageResource(R.mipmap.toast_default_icon);
                break;
        }
        mToast.setView(layout);
        mToast.show();
    }

    private static class BaseToast extends Toast {

        boolean hasReflectException = false;//是否反射成功
        int mAnim = 0; //动画配置 默认无


        public void setAnim(int AnimStyle) {
            mAnim = AnimStyle;
        }

        public BaseToast(Context context, int AnimStyle) {
            super(context.getApplicationContext());
            setAnim(AnimStyle);
            init();
        }

        public void init() {
            //反射成功调用动画
            if (!hasReflectException) initTN();
        }

        private void initTN() {
            Field mTN;
            Object mObj;
            Class<Toast> clazz = Toast.class;
            int anim = getAnim();
            try {
                mTN = clazz.getDeclaredField("mTN");
                mTN.setAccessible(true);
                mObj = mTN.get(this);

                // 替换默认Toast弹出动画
                Field field = mObj.getClass().getDeclaredField("mParams");
                field.setAccessible(true);
                Object mParams = field.get(mObj);
                if (mParams instanceof WindowManager.LayoutParams) {
                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                    params.windowAnimations = anim;
                }
                hasReflectException = false;
            } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
                hasReflectException = true;
            }
        }

        public int getAnim() {
            return mAnim;
        }
    }

}

