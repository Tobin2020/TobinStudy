package top.tobin.basic.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author lijunbin
 * @date 2020/8/24
 * @email 616041023@qq.com
 * @description SharedPreferences
 */
public class SPUtil {
    public static final String KEY_AUTH_STATE = "key_auth_state";
    public static final String KEY_SHOW_AGREE = "key_show_agree";
    public static final String KEY_SHOW_PRIVACY = "key_show_privacy";
    public static final String KEY_ACTION_BOOT_SHUTDOWN = "key_action_boot_shutdown";
    public static final String KEY_ACTION_CAR_INPUT_DATA = "key_action_car_input_data";

    private static final String SHARE_FILE_NAME = "LY_BOOT_DATA";

    private static SharedPreferences prefs;
    private static SPUtil spUtil;

    private SPUtil(Context context){
        prefs = context.getSharedPreferences(SHARE_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SPUtil getInstance(Context context){
        if (spUtil == null){
            spUtil = new SPUtil(context);
        }
        return spUtil;
    }

    public void putString(String key, String value){
        if (spUtil != null){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key,value);
            editor.apply();
        }
    }

    public String getString(String key, String defValue){
        if (prefs != null ){
            return prefs.getString(key,defValue);
        }
        return defValue;
    }

    public void putInt(String key, int value){
        if (prefs != null){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(key,value);
            editor.apply();
        }
    }

    public int getInt(String key, int defValue){
        if (prefs != null ){
            return prefs.getInt(key,defValue);
        }
        return defValue;
    }

    public void putBoolean(String key, boolean value){
        if (spUtil != null){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(key,value);
            editor.apply();
        }
    }

    public boolean getBoolean(String key, boolean defValue){
        if (prefs != null ){
            return prefs.getBoolean(key,defValue);
        }
        return defValue;
    }

}
