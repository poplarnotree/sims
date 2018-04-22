package org.ycm.sims.utils;

import org.ycm.sims.dto.RoleDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 格式转换工具
 * Create by yangchangmin
 * on 2018/4/19 1:21
 */
public class FormatConversionUtil {

    /**
     * 时间格式转换，返回 yyyy-MM-dd 格式的字符串
     * @param date
     * @return
     */
    public static String DateFormatUtil(Date date){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * int roleType 转 String roletype
     * @param roleType
     * @return
     */
    public static String roleTypeFormatUitl(Integer roleType){
        String roletype = "";
        switch (roleType) {
            case 0:
                roletype = "超级管理员";
                break;
            case 1:
                roletype = "教师";
                break;
            case 2:
                roletype = "学生";
                break;
        }
        return roletype;
    }

    /**
     * String roletype 转 int roleType
     * @param roleType
     * @return
     */
    public static Integer roleTypeFormatUitl(String roleType){
        int roletype = 0;
        switch (roleType) {
            case "超级管理员":
                roletype = 0;
                break;
            case "教师":
                roletype = 1;
                break;
            case "学生":
                roletype = 2;
                break;
        }
        return roletype;
    }

    /**
     * List 转 String
     * @param list
     * @return
     */
    public static String ListFormatString(List list){
        String str = "";
        for(int i = 0; i < list.size(); i++){
            if (i == list.size() - 1){
                str += list.get(i);
            }else{
                str += list.get(i) + ",";
            }
        }
        return str;
    }
}
