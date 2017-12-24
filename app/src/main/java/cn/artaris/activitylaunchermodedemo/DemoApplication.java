package cn.artaris.activitylaunchermodedemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

/**
 * cn.artaris.activitylaunchermodedemo
 * ActivityLauncherModeDemo
 * 2017.12.19.0:51
 *
 * @author : artaris
 */
public class DemoApplication extends Application {

    private static DemoApplication mApplication;

    private Stack<Activity> mActivityStack = new Stack<>();

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                mActivityStack.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                mActivityStack.remove(activity);
            }
        });
    }


    public Stack<Activity> getActivityStack() {
        return mActivityStack;
    }

    public static DemoApplication getApplication() {
        return mApplication;
    }
}
