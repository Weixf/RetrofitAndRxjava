package zjhl.wxf.retrofitandrxjava.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import static android.content.Context.TELEPHONY_SERVICE;

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


    /**
     * 获取手机厂商
     */
    public static String getPhoneVendor(){
        try {
            return Build.MANUFACTURER;
        }catch (Exception e) {}
        return "no_phone_vendor";
    }

    /**
     * 获取设备硬件序列号（自2.3以上推荐用此方法作为唯一标识。只有数字字母，并且不区分大小写。）
     *
     * @return 设备硬件序列号
     */
    public static String getSerial() {
        return Build.SERIAL;
    }

    /**
     * 获取手机号
     */
    public static String getPhone(Context context){
        TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

    /**
     * 6.0后获取不到imei
     * 获取设备唯一标识(GSM获得IMEI，CDMA获得MEID或者ESN，若没手机卡则无法获得)
     * 注意：使用此函数请申请READ_PHONE_STATE权限
     *
     * @return 设备唯一标识
     */
    public static String getDeviceId(Context context) {
        try {
            String DeviceId = "";
            TelephonyManager telephonyManager = (TelephonyManager) context
                    .getSystemService(TELEPHONY_SERVICE);
            if (telephonyManager != null)
                DeviceId = telephonyManager.getDeviceId();
            return DeviceId;
        }catch (Exception e){
            Toast.makeText(context,"没有获取到设备号 ",Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * 获取设备品牌
     *
     * @return 设备品牌
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取设备模组号（即一般来说的型号）
     *
     * @return 设备模组号
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取系统版本号
     *
     * @return 系统版本号
     */
    public static String getSDKInt() {
        return Integer.toString(Build.VERSION.SDK_INT);
    }

    private final static String ip_default = "0.0.0.0"; // 默认ip内容

    /**
     * 获取网内IPv4
     *
     * @return IPv4字符串
     */
    public static String getLocalIpAddressV4() {
        String ip = ip_default;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
//                    if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(inetAddress.getHostAddress())) {
//                        ip = inetAddress.getHostAddress();
//                        return ip == null ? ip_default : ip;
//                    }
                }
            }
        } catch (SocketException e) {
        }
        return ip;
    }

    /**
     * 获取手机号（前提是如果手机号被写在SIM卡上的话。这种情况很少。） 注意：使用此函数请申请READ_PHONE_STATE权限
     *
     * @param context 程序上下文对象
     * @return 手机号
     */
    public static String getPhoneNumber(Context context) {
        String PhoneNumber = "";
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(TELEPHONY_SERVICE);
        if (telephonyManager != null)
            PhoneNumber = telephonyManager.getLine1Number();
        return PhoneNumber;
    }

    /**
     * 获取SIM卡运营商 注意：使用此函数请申请READ_PHONE_STATE权限
     *
     * @param context 程序上下文对象
     * @return SIM卡运营商
     */
    public static String getSimOperatorName(Context context) {
        String getSimOperatorName = "";
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(TELEPHONY_SERVICE);
        if (telephonyManager != null)
            getSimOperatorName = telephonyManager.getSimOperatorName();
        return getSimOperatorName;
    }

    /**
     * 获取网络类型
     *
     * @param context 程序上下文对象
     * @return 网络类型
     */
    public static String getNetConnectType(Context context) {
        String type = "";
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();

			/* 如果网络为WIFI的话，返回类型字符串（字符串WIFI）。如果为手机网络的话，返回具体的网络类型字符串。 */
            if (networkInfo != null)
                type = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE
                        ? (networkInfo.getSubtypeName()) : (networkInfo.getTypeName());
        }
        return type;
    }

    /**
     * 获取SIM卡状态
     *
     * @param context 程序上下文对象
     * @return SIM卡状态：
     * SIM_STATE_UNKNOWN = 0 SIM卡状态：不可知。该状态意味着SIM卡处于两种状态的转换过程之中。
     * 例如当SIM卡在PIN_REQUIRED的情况下，用户输入了PIN码，那么在SIM卡变为SIM_STATE_READY之前都会返回这个状态。
     * SIM_STATE_ABSENT = 1 SIM卡状态：设备中没有可用的SIM卡。
     * SIM_STATE_PIN_REQUIRED = 2 SIM卡状态：锁定中。需要SIM卡的PIN码来解锁。
     * SIM_STATE_PUK_REQUIRED = 3 SIM卡状态：锁定中。需要SIM卡的PUK码来解锁。
     * SIM_STATE_NETWORK_LOCKED = 4 SIM卡状态：锁定中。需要网络PIN码来解锁。
     * SIM_STATE_READY = 5 SIM卡状态：正常。
     */
    public static int getSimState(Context context) {
        int state = -1;
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(TELEPHONY_SERVICE);
        if (telephonyManager != null)
            state = telephonyManager.getSimState();
        return state;
    }

    /**
     * 获取手机类型
     *
     * @param context 程序上下文
     * @return 手机类型：
     * PHONE_TYPE_NONE = 0
     * PHONE_TYPE_GSM = 1
     * PHONE_TYPE_CDMA = 2
     * PHONE_TYPE_SIP = 3
     */
    public static int getPhoneType(Context context) {
        int type = -1;
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(TELEPHONY_SERVICE);
        if (telephonyManager != null)
            type = telephonyManager.getPhoneType();
        return type;
    }

    /**
     * 手机是否处于漫游状态
     *
     * @param context 程序上下文
     * @return 是否漫游
     */
    public static boolean isNetworkRoaming(Context context) {
        boolean isNetworkRoaming = false;
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(TELEPHONY_SERVICE);
        if (telephonyManager != null)
            isNetworkRoaming = telephonyManager.isNetworkRoaming();
        return isNetworkRoaming;
    }
}
