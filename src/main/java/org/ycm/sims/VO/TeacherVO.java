package org.ycm.sims.VO;

import lombok.Data;

/**
 * 科任教师姓名
 * Created by yangchangmin
 * on 2018/4/28 16:58
 */
@Data
public class TeacherVO {

    private static String NOTEACHER = "暂无教师";

    private String chinese = NOTEACHER;

    private String math = NOTEACHER;

    private String english = NOTEACHER;

    private String physics = NOTEACHER;

    private String chemistry = NOTEACHER;

    private String biology = NOTEACHER;

    private String politics = NOTEACHER;

    private String geography = NOTEACHER;

    private String history = NOTEACHER;

}
