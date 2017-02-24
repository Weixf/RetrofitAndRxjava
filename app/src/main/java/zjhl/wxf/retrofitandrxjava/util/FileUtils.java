package zjhl.wxf.retrofitandrxjava.util;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Weixf
 * Date on 2017/2/23.
 * Describe 文件保存的工具类
 */

public class FileUtils {
    /**
     * 保存字符串到本地
     * @param str 需要写入的字符串
     * @param fileName 写入的文件名
     */
    public static void StringToLocal(String str,String fileName){
        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
                File sdFile = new File(sdCardDir, fileName);
                if(!sdFile.exists()){
                    sdFile.createNewFile();
                }
                try {
                    FileOutputStream fos = new FileOutputStream(sdFile);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(str);// 写入
                    fos.close(); // 关闭输出流
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){}
    }
}
