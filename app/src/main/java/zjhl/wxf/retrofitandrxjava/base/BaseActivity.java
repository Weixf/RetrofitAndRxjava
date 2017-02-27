package zjhl.wxf.retrofitandrxjava.base;

import android.content.Intent;
import android.os.Bundle;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import zjhl.wxf.retrofitandrxjava.R;
import zjhl.wxf.retrofitandrxjava.util.AppManager;

/**
 * Create by Weixf
 * Date 2017年2月23日
 * Describe  Activity的基类
 */
public class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_to_left_enter,R.anim.right_to_left_exit);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_to_right_enter,R.anim.left_to_right_exit);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this.getClass());
    }
}
