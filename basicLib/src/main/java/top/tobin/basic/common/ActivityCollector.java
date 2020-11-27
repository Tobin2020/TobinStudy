package top.tobin.basic.common;

import android.app.Activity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * @author lijunbin
 * @date 2020/8/28
 * @email 616041023@qq.com
 * @description ActivityCollector
 */
public class ActivityCollector {
    private static Stack<WeakReference<Activity>> stack;
    private static ActivityCollector manager;

    /**
     * 获取实例
     */
    public static synchronized ActivityCollector getInstance() {
        if (manager == null) {
            manager = new ActivityCollector();
            stack = new Stack<>();
        }
        return manager;
    }

    /**
     * 添加Activity
     */
    public synchronized void addActivity(Activity activity) {
        WeakReference<Activity> weakReference = new WeakReference<>(activity);
        stack.add(weakReference);
    }

    /**
     * 移除Activity
     */
    public synchronized void removeActivity(Activity activity) {
        WeakReference<Activity> weakReference = new WeakReference<>(activity);
        stack.remove(weakReference);
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Reference<Activity> activity : stack) {
            if (activity.get().getClass().equals(cls)) {
                finishActivity(activity.get());
                return;
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        for(WeakReference<Activity> weakReference : stack) {
            if(weakReference.get() == activity) {
                activity.finish();
                stack.remove(weakReference);
                break;
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public Activity getActivity(Class<?> cls) {
        for (WeakReference<Activity> activity : stack) {
            if (activity.get().getClass().equals(cls)) {
                return activity.get();
            }
        }
        return null;
    }

    /**
     * 是否存在Activity
     */
    public boolean containsActivity(Class<?> cls) {
        for (WeakReference<Activity> activity : stack) {
            if (activity.get().getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (WeakReference<Activity> activity : stack) {
            if (activity.get() != null) {
                activity.get().finish();
            }
        }
        stack.clear();
    }
}
