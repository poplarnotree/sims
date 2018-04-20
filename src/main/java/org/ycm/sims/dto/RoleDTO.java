package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/4/18 2:01
 */
@Data
public class RoleDTO {

    private Integer id;

    private String loginName;

    private String loginPassword;

    private String roleType;

    public RoleDTO() {
    }

    public RoleDTO(String roleType) {
        this.roleType = roleType;
    }

    public RoleDTO(Integer id, String roleType) {
        this.id = id;
        this.roleType = roleType;
    }

    public RoleDTO(String loginName, String loginPassword) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
    }

    public RoleDTO(String loginName, String loginPassword, String roleType) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.roleType = roleType;
    }
}
