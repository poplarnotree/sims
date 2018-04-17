package org.ycm.sims.dto;

import lombok.Data;

import java.util.Date;

/**
 * Create by yangchangmin
 * on 2018/4/18 2:01
 */
@Data
public class RoleDTO {

    private Integer id;

    private String loginName;

    private String loginPassword;

    private Integer createId;

    private Integer roleType;

    private Integer roleStatus;

    private Date createTime;

}
