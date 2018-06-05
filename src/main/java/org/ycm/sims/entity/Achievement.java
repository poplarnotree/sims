package org.ycm.sims.entity;

import lombok.Data;

import java.util.Date;

/**
 * Create by yangchangmin
 * on 2018/5/23 23:27
 */
@Data
public class Achievement {

    private Integer sInformationId;

    private Integer tInformationId;

    private String classes;

    private String year;

    private String mouth;

    private String num;

    private String subject;

    private Integer score;

    private Integer status;

    private Integer createId;

    private Date createTime;

}
