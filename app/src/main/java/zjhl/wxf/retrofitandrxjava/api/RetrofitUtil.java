package zjhl.wxf.retrofitandrxjava.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zjhl.wxf.retrofitandrxjava.Constant;

/**
 * Created by Weixf
 * Date on 2017/2/21.
 * Describe Retrofit+Rxjava的网络框架
 */

public class RetrofitUtil {
    private static final String HOST = Constant.BASEURL;
    private static final String TAG = "RetrofitUtil";
    //设置超时时间为6秒
    private static final long DEFAULT_TIMEOUT = 6;
    private static Retrofit retrofit;
    private static ApiServer apiServer = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            //创建一个Okhttp
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(logging);
            //创建Gson
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            retrofit = new Retrofit.Builder()
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(HOST)
                    .build();
        }
        return retrofit;
    }

    public static ApiServer getApiServer() {
        if (apiServer == null) {
            apiServer = getRetrofit().create(ApiServer.class);
        }
        return apiServer;
    }

}
