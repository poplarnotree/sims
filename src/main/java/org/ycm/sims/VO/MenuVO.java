package org.ycm.sims.VO;

import lombok.Data;

import java.util.Date;

/**
 * Create by yangchangmin
 * on 2018/5/8 0:56
 */
@Data
public class MenuVO {

    private Integer id;

    private String name;

    private String modular;

    //    学生处教师可见
    private Integer teacherDisplay1;

    //    教研组教师可见
    private Integer teacherDisplay2;

    private Integer studentDisplay;

    private String url;

    private String createName;

    private String createTime;
}
