package org.ycm.sims.enums;

import lombok.Getter;

/**
 * 结果枚举
 * Create by yangchangmin
 * 2018/4/15 2:25
 */
@Getter
public enum  ResultEnum {
    SUCCESS(0,"成功")

    ,PASSWORD_ERROR(1, "密码错误")

    ,ROLE_NULL(2, "帐号不存在")

    ,ROLE_INVALID(3, "无效账户")

    ,ROLE_EXIST(4, "帐号已存在")

    ,INFORMATION_EXIST(5, "信息已存在")

    ,NUMBER_EXIST(6, "工号已存在")

    ,CLASS_EXIST(7, "班级已存在")

    ,CLASS_EXIST_PERSON(8, "班级还存在人员，无法删除")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
