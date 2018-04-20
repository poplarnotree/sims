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

    private Integer roleType;

    private Integer roleStatus;

    private Date createTime;

    public Role() {
    }

    public Role(Integer roleType) {
        this.roleType = roleType;
    }

    public Role(String loginName, Integer roleType) {
        this.loginName = loginName;
        this.roleType = roleType;
    }

    public Role(String loginName, String loginPassword) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
    }

    public Role(Integer id, String loginPassword) {
        this.id = id;
        this.loginPassword = loginPassword;
    }

    public Role(String loginName, String loginPassword, Integer createId, Integer roleType) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.createId = createId;
        this.roleType = roleType;
    }
}
