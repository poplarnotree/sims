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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString = formatter.format(date);
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
     * int type 转 String type
     * @param type
     * @returnt
     */
    public static String typeFormatUitl(Integer type){
        String roletype = "";
        switch (type) {
            case 1:
                roletype = "老师";
                break;
            case 2:
                roletype = "同学";
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
        int roletype = -1;
        if (roleType == null){
            return -1;
        }
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
        if (list == null){
            return null;
        }
        for(int i = 0; i < list.size(); i++){
            if (i == list.size() - 1){
                str += list.get(i);
            }else{
                str += list.get(i) + ",";
            }
        }
        return str;
    }

    /**
     * 字符串拼装classes
     * @param classes 数据库中查到的教师classes
     * @param oldClasses 需要修改的,老的classes
     * @param newClasses 新的classes
     * @return
     */
    public static String teacherClass(String classes, String oldClasses, String newClasses){
        String str = "";
        String[] s = classes.split(",");
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals(oldClasses)){
                str += newClasses;
                if (i != s.length - 1){
                    str += ",";
                }
            }else {
                str += s[i];
                if (i != s.length - 1){
                    str += ",";
                }
            }
        }
        return str;
    }

    /**
     * 删除当前重复的字符串，用于删除教师执教班级
     * @param currentClass 当前教师的班级
     * @param classes 需要删除的班级
     * @return
     */
    public static String cutTeacherClass(String currentClass, String classes){
        String str = "";
        if (currentClass == "暂无班级"){
            str = classes;
            return str;
        }
        String[] s = currentClass.split(",");
        for (int i = 0; i < s.length; i++){
            if (!s[i].equals(classes)){
                str += s[i];
                if (i != s.length - 1){
                    str += ",";
                }
            }
        }
        if (str == ""){
            str = "暂无班级";
        }
        int index = str.length();
        if (str.charAt(index - 1) == ','){
            str = str.substring(0, index - 1);
        }
        return str;
    }

    public static List<String> ClassFormatList(String classes){
        List<String> list = new ArrayList<>();
        String[] s = classes.split(",");
        for (String str: s){
            list.add(str);
        }
        return list;
    }
}
