package cn.artaris.activitylaunchermodedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * cn.artaris.activitylaunchermodedemo
 * ActivityLauncherModeDemo
 * 2017.12.18.23:57
 *
 * @author : artaris
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String PACKAGE_NAME = "cn.artaris.activitylaunchermodedemo";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.toString().substring(PACKAGE_NAME.length() + 1),"On Create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(this.toString().substring(PACKAGE_NAME.length() + 1),"On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(this.toString().substring(PACKAGE_NAME.length() + 1),"On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(this.toString().substring(PACKAGE_NAME.length() + 1),"On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(this.toString().substring(PACKAGE_NAME.length() + 1),"On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(this.toString().substring(PACKAGE_NAME.length() + 1),"On Destroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(this.toString().substring(PACKAGE_NAME.length() + 1),"On Restart");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(this.toString().substring(PACKAGE_NAME.length() + 1),"On New Intent");
    }

    /**
     * 打印当前栈信息
     */
    protected abstract void onDumpStackInfo();
}
