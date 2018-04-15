package org.ycm.sims.enums;

import lombok.Getter;

/**
 * 结果枚举
 * Create by yangchangmin
 * 2018/4/15 2:25
 */
@Getter
public enum  ResultEnum {
    SUCCESS(0,"成功"),

    PASSWORD_ERROR(1, "密码错误"),

    ROLE_NULL(2, "这个用户不存在")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
