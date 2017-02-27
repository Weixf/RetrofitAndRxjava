package zjhl.wxf.retrofitandrxjava.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by Weixf
 * Date on 2017/2/23.
 * Describe 正则验证工具类
 */
public class ValidationUtil {

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
		/*
		移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		170
		总结起来就是第一位必定为1，第二位必定为3或5或7或8，其他位置的可以为0-9
		*/
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    /**
     * 判断邮编
     * @param identityNum
     * @return
     */
    public static boolean isIdentityNo(String identityNum){
        String str = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))";
        return Pattern.compile(str).matcher(identityNum).matches();
    }

    /**
     * 判断身份证号
     * @param identity_card
     * @return
     */
    public static boolean identity_card(String identity_card) {
        // 验证通过返回true否则返回false
        return Pattern.matches("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))+$", identity_card);
    }

    /**
     * 判断名字
     * @param name
     * @return
     */
    public static boolean etName(String name) {
        // 验证通过返回true否则返回false
        return Pattern.matches("^[a-zA-Z0-9\u4e00-\u9fa5]{2,10}+$", name);
    }
}
