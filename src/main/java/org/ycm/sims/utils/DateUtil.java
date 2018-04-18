package org.ycm.sims.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式转换
 * Create by yangchangmin
 * on 2018/4/19 1:21
 */
public class DateUtil {

    /**
     * 返回 yyyy-MM-dd 格式的字符串
     * @param date
     * @return
     */
    public static String DateUtil(Date date){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
