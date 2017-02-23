package zjhl.wxf.retrofitandrxjava.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import zjhl.wxf.retrofitandrxjava.Bean;

/**
 * Created by Weixf
 * Date on 2017/2/21.
 * Describe
 */

public interface ApiServer {
//    https://api.github.com/users/Guolei1130
//    @GET("/users/{user}")
//    Call<Bean> getDetail(@Path("user") String user);
    @GET("/users/{user}")
    Observable<Bean> getDetail(@Path("user") String user);

}
