package org.ycm.sims.dto;

import lombok.Data;

/**
 * 登录DTO
 * Create by yangchangmin
 * 2018/4/15 2:20
 */
@Data
public class CheckDTO {

    /*登录验证状态，0=成功，1=失败*/
    private int status;

    /*信息*/
    private String message;

    public CheckDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
