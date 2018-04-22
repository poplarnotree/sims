package org.ycm.sims.VO;

import lombok.Data;

/**
 * 创建信息返回的VO
 * Create by yangchangmin
 * on 2018/4/22 22:18
 */
@Data
public class CreateInformationVO {

    /*验证状态，0=成功，1=失败*/
    private int status;

    /*信息*/
    private String message;

}
