package org.ycm.sims.VO;

import lombok.Data;
import org.ycm.sims.enums.ResultEnum;

/**
 * Create by yangchangmin
 * on 2018/4/18 1:09
 */
@Data
public class RoleVO {

    /*登录验证状态，0=成功，1=失败*/
    private int status;

    /*信息*/
    private String message;

    /* 用户类型 */
    private int roleType;

    public RoleVO(ResultEnum resultEnum) {
        this.status = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public RoleVO(ResultEnum resultEnum, int roleType) {
        this.status = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.roleType = roleType;
    }
}
