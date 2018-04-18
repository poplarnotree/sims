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

    private Integer roleType;

    public RoleDTO(Integer roleType) {
        this.roleType = roleType;
    }

    public RoleDTO(String loginName, String loginPassword) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
    }

    public RoleDTO(String loginName, String loginPassword, Integer roleType) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.roleType = roleType;
    }
}
