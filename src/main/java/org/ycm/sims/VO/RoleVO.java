package org.ycm.sims.VO;

import lombok.Data;
import org.ycm.sims.utils.DateUtil;

import java.util.Date;

/**
 * Create by yangchangmin
 * on 2018/4/19 1:36
 */
@Data
public class RoleVO {

    private Integer id;

    private String loginName;

    private int roleType;

    private String createTime;

    public RoleVO() {
    }

    public RoleVO(Integer id, String loginName, int roleType, String createTime) {
        this.id = id;
        this.loginName = loginName;
        this.roleType = roleType;
        this.createTime = createTime;
    }
}
