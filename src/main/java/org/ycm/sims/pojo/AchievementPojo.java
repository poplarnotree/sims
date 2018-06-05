package org.ycm.sims.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Create by yangchangmin
 * on 2018/6/2 23:51
 */
@Data
public class AchievementPojo {

    private Integer id;

    private String number;

    private String studentName;

    private String classes;

    private String subject;

    private String teacherName;

    private String year;

    private String mouth;

    private String num;

    private Integer score;

    private Date createTime;

}
