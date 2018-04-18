package org.ycm.sims.VO;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/4/19 1:36
 */
@Data
public class RoleVO {

    private String loginName;

    private String roleType;

    private String createTime;

    public RoleVO(String loginName, String roleType, String createTime) {
        this.loginName = loginName;
        this.roleType = roleType;
        this.createTime = createTime;
    }
}
