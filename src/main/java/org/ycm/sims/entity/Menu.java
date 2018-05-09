package org.ycm.sims.entity;

import lombok.Data;

import java.util.Date;

/**
 * Create by yangchangmin
 * on 2018/5/7 21:01
 */
@Data
public class Menu {

    private Integer id;

    private String name;

    private String modular;

    //    学生处教师可见
    private Integer teacherDisplay1;

    //    教研组教师可见
    private Integer teacherDisplay2;

    private Integer studentDisplay;

    private String url;

    private Integer createId;

    private Date createTime;

}
