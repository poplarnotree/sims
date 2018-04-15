package org.ycm.sims.entity;

import lombok.Data;

import java.util.Date;

/**
 * Create by yangchangmin
 * 2018/4/14 21:53
 */

@Data
public class Role {

    private Integer id;

    private String loginName;

    private String loginPassword;

    private Integer createId;

    private Integer userType;

    private Date createTime;

}
