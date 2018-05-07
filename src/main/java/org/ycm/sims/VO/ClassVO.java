package org.ycm.sims.VO;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by yangchangmin
 * on 2018/4/28 11:20
 */
@Data
public class ClassVO {

    private Integer id;

    private String name;

    /*人数*/
    private Integer number;

    /*各科教师*/
    private TeacherVO teacherVO;

    /*创建人登录名*/
    private String createName;

    private String createTime;

}
