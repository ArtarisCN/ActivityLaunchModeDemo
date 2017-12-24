package cn.artaris.activitylaunchermodedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * cn.artaris.activitylaunchermodedemo
 * ActivityLauncherModeDemo
 * 2017.12.19.0:18
 *
 * @author : artaris
 */
public class SingleTaskActivity extends BaseActivity {

    private TextView mTvDumpActivityStackInfo;

    private static int mActivityCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityCount ++;

        setContentView(R.layout.activity_single_task);

        TextView mTvActivityInfo = findViewById(R.id.tv_activity_info);

        mTvDumpActivityStackInfo = findViewById(R.id.tv_dump_stack_info);

        mTvActivityInfo.setText(String.format(getString(R.string.activity_info),this.toString().substring(PACKAGE_NAME.length() + 1),mActivityCount));

        onDumpStackInfo();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityCount --;
    }

    public void onStandard(View view){
        Intent intent = new Intent(this,StandardActivity.class);
        startActivity(intent);
    }

    public void onSingleTop(View view){
        Intent intent = new Intent(this,SingleTopActivity.class);
        startActivity(intent);
    }

    public void onSingleTask(View view){
        Intent intent = new Intent(this,SingleTaskActivity.class);
        startActivity(intent);
    }

    public void onSingleInstance(View view){
        Intent intent = new Intent(this,SingleInstanceActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDumpStackInfo(){

        StringBuilder info = new StringBuilder();

        for (Activity activity : DemoApplication.getApplication().getActivityStack()) {

            info.append(activity.toString().substring(PACKAGE_NAME.length() + 1));
            info.append("\n");
        }

        mTvDumpActivityStackInfo.setText(info);
    }

}
