package top.tobin.basic.net.download;

import android.util.Pair;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.tobin.basic.utils.LogUtil;


/**
 * @author lijunbin
 * @date 2020/7/6
 * @email
 * @description 下载Observer
 */
public abstract class DownLoadObserver implements Observer<Object> {

    private int mPercent = 0;

    @Override
    public void onSubscribe(Disposable d) {
        LogUtil.d("DownLoad", "onSubscribe");
    }

    @Override
    public void onNext(Object o) {
        if (o instanceof Pair) {
            long uploaded = (long)(((Pair) o).first);
            long sumLength = (long)(((Pair) o).second);
            _onProgress(uploaded, sumLength);
            int percent = (int) (uploaded*100f / sumLength);
            if (percent < 0) {
                percent = 0;
            }
            if (percent > 100) {
                percent = 100;
            }
            if (percent == mPercent) {
                return;
            }
            mPercent = percent;
            _onProgress(mPercent);
        }
        if(o instanceof String) {
            _onNext((String) o);
        }
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }

    public abstract void _onNext(String result);
    public void _onProgress(Integer percent) {}
    public void _onProgress(long uploaded, long sumLength) {}

}
