package zjhl.wxf.retrofitandrxjava.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zjhl.wxf.retrofitandrxjava.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
