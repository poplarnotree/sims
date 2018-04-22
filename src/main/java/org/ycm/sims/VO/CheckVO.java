package org.ycm.sims.VO;

import lombok.Data;
import org.ycm.sims.enums.ResultEnum;

/**
 * Create by yangchangmin
 * on 2018/4/22 22:20
 */
@Data
public class CheckVO {

    /*验证状态，0=成功，1=失败*/
    private int status;

    /*信息*/
    private String message;

    public CheckVO(ResultEnum resultEnum) {
        this.status = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public CheckVO(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
