package org.ycm.sims.enums;

import lombok.Getter;

/**
 * Created by yangchangmin
 * on 2018/4/17 16:20
 */
@Getter
public enum ExceptionEnum {

    DATA_BASE_ERROR(100, "数据库异常"),

    SYSTEM_ERROR(500, "系统异常")
    ;

    private Integer code;

    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
