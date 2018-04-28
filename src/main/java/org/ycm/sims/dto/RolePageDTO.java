package org.ycm.sims.dto;

import lombok.Data;

/**
 * Created by yangchangmin
 * on 2018/4/28 15:35
 */
@Data
public class RolePageDTO extends PageDTO {

    private String loginName;

    private Integer roleType;

    public RolePageDTO() {
    }

    public RolePageDTO(Integer page, Integer limit, Integer roleType) {
        super(page, limit);
        this.roleType = roleType;
    }

    public RolePageDTO(Integer page, Integer limit, String loginName, Integer roleType) {
        super(page, limit);
        this.loginName = loginName;
        this.roleType = roleType;
    }
}
