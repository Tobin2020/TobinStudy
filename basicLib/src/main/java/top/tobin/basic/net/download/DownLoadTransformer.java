package top.tobin.basic.net.download;

import android.text.TextUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import okhttp3.ResponseBody;
import top.tobin.basic.base.BaseApplication;

/**
 * @author lijunbin
 * @date 2020/7/7
 * @email
 * @description LY_BootAD
 */
public class DownLoadTransformer implements ObservableTransformer<ResponseBody, Object> {

    //    默认保存地址
    private String mPath;
    //    文件名
    private String mFileName;


    public DownLoadTransformer(String mPath, String mFileName) {
        if (TextUtils.isEmpty(mPath)){
            this.mPath = BaseApplication.getInstance().getFilesDir().getAbsolutePath();
        }else {
            this.mPath = mPath;
        }
        this.mFileName = mFileName;
    }

    @Override
    public ObservableSource<Object> apply(Observable<ResponseBody> upstream) {
        return upstream.flatMap(responseBody -> Observable.create(new DownLoadOnSubscribe(responseBody, mPath, mFileName)));
    }
}
