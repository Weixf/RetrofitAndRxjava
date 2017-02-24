package zjhl.wxf.retrofitandrxjava.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Weixf
 * Date on 2017/2/23.
 * Describe 获取App的一些基本信息
 */

public class AppInfomation {

    /**
     * 获取版本号
     * @param context
     * @return
     */
    public static String getVersionCode(Context context){
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return  version;
        } catch (Exception e) {
            e.printStackTrace();
            return "获取不到版本";
        }
    }

    /**
     * 获取包名
     * @param context
     * @return
     */
    public String getPackageName(Context context) {
        return context.getPackageName();
    }
}
