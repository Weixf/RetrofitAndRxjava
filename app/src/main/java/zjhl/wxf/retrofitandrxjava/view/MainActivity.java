package zjhl.wxf.retrofitandrxjava.view;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.TextView;

import com.trello.rxlifecycle.ActivityEvent;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import zjhl.wxf.retrofitandrxjava.R;
import zjhl.wxf.retrofitandrxjava.api.RetrofitUtil;
import zjhl.wxf.retrofitandrxjava.base.BaseActivity;
import zjhl.wxf.retrofitandrxjava.bean.Bean;
import zjhl.wxf.retrofitandrxjava.util.DateUtil;
import zjhl.wxf.retrofitandrxjava.util.DialogHelper;


/**
 * Observale  被观察者的创建操作：创建Observable的各种操作符
 * from  逐个取出集合或数组的所有数据,参数必须放置集合或者数组
 * just  直接把传递的集合或者数据当成整体发送，参数为任意
 * timer  延时操作，适合欢迎界面显示，参数3个：时间(数字)，时间单位(秒或者分)，线程；
 * repeat  重复操作  参数为几次，不能直接在Observale后使用
 * defer  直到有订阅  才会创建Observable
 * range  发送一个特定的整数序列的Observable  可以指定范围
 * interval  和timer用法相似，参数一样 ，是每隔几秒发送一次，无限发送(相当于timer+repeat)
 *
 * Observable的变换操作：对Observable发射的数据执行变换操作的各种操作符
 * flatMap 对发射的数据的每一项进行变换操作，返回结果还是Observable类型，但是不保证顺序  如果需要保证顺序用concatMap
 * map  对发射的每一条数据都进行函数操作 ，返回结果自定义
 * cast 对发射的每一条数据进行强转
 * byLine  对发射的字符串变换成按行发送的Observable
 * groupBy  分组，返回值也是Observable  可以根据getKey()获取组名
 * scan  对Observable发射的第一个数据应用一个函数，将结果作为第一项的数据发射
 * window 将Observable的数据分解为一个Observable的窗口(暂时不用)
 *
 * Observable的过滤操作：过滤和选择Observable发射的数据序列
 * Debounce 会过滤掉发射速率过快的数据项
 * Distinct 值允许没有发射过的数据项通过
 * ElementAt 只发射第n项数据  参数为int
 * Filter 筛选，最常用，参数为函数，返回值为boolean类型
 * First 只发射第一项或者满足条件的第一项
 * Last  只发送最后一项或者满足条件的最后一项
 * IgnoreElement  不发射任何数据 只发送Observable的终止通知
 * Sample 定期发射Observable最近发射的数据项
 * Skip  参数为int 忽略前n项  只发送后面的数据
 * SkipLast 参数为int 忽略后n项  只发送前面的数据
 * take 参数为int  只发送前n项数据
 *
 * Observable的结合操作
 * merge 合并多个Observable的发射物  相当于直接插入
 * zip 合并多个Observable的发射物  会结合到一起 不过新的Observable以最短的Observable为准
 * combineLatest 当两个Observables中的任何一个发射了数据时，使用一个函数结合每个Observable发射的最近数据项，并且基于这个函数的结果发射数据。
 * startWith 在数据序列的开头插入一条指定的项
 * switchOnNext 当原始Observable 发射了一个新的Observable,取消之前发的
 * switchMap  功能同上 是flatMap的优化，建议使用
 *
 * Observable错误处理
 * catch  能拦截Observable的onError的通知 实现分为三个操作符
 *        onErrorReturn 当Observable遇到错误时发送一个特殊的Observable并且正常终止
 *        onErrorResumeNext 当Observable遇到错误时开始发送第二个数据序列
 *        onExceptionResumeNext  当Observable遇到Exception时开始发送第二个数据，但是如果收到的是Throwable则会执行onError
 * retry 不会将原始的Observable的onError通知传递给观察者，而是会重新订阅这个Observable,而是在尝试一次 执行onNext，但是可能会造成数据项重复
 *       无论收到多少次onError通知 都会继续订阅并发射原始Observable 如果参数为int 择指定次数
 * retryWhen  retryWhen 和 retry 类似，区别是， retryWhen 将 onError 中的 Throwable 传递给一个函
 *            数，这个函数产生另一个Observable， retryWhen 观察它的结果再决定是不是要重新订阅原
 *            始的Observable。如果这个Observable发射了一项数据，它就重新订阅，如果这个
 *            Observable发射的是 onError 通知，它就将这个通知传递给观察者然后终止。
 *
 * Observable的辅助操作
 * observeOn 指定观察者在那个调度器上观察  发送的线程即onNext执行的线程、
 * subscribeOn 指定被观察者在那个调度器上执行  即call执行的线程
 * subscribe  连接观察者和被观察者，订阅的功能
 *             Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
 *             Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
 *             Schedulers.newThread() 代表一个常规的新线程
 *             AndroidSchedulers.mainThread() 代表Android的主线程
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView text = (TextView) findViewById(R.id.text);
        RetrofitUtil.getApiServer().getDetail("Guolei1130")
                .compose(this.<Bean>bindUntilEvent(ActivityEvent.PAUSE))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        DialogHelper.showProgressDlg(MainActivity.this, "测试");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bean>() {
                    @Override
                    public void onCompleted() {
                        DialogHelper.stopProgressDlg();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        text.setText(bean.getName());
                        Log.e(TAG, "onNext: "+ DateUtils.formatDateTime(MainActivity.this,System.currentTimeMillis(),1));
                        Log.e(TAG, "onNext: "+ DateUtil.getTimeNum());
                    }
                });
    }
}

