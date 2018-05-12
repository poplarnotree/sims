package org.ycm.sims.enums;

import lombok.Getter;

/**
 * 异常枚举
 * Created by yangchangmin
 * on 2018/4/17 16:20
 */
@Getter
public enum ExceptionEnum {

    DATA_BASE_ERROR(100, "数据库异常,请稍后再试"),

    UNAUTHORIZED_OPERATION(200, "越权操作,请重新登录"),

    SYSTEM_ERROR(500, "系统异常,请稍后再试")
    ;

    private Integer status;

    private String message;

    ExceptionEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
