package org.ycm.sims.enums;

import lombok.Getter;

/**
 * 参数枚举
 * Create by yangchangmin
 * on 2018/4/18 1:30
 */
@Getter
public enum ParameterEnum {

    ADMIN_TYPE(0, "超级管理员"),

    TEACHER_TYPE(1, "教师"),

    STUDENT_TYPE(2, "学生"),

    LOGIN_NAME(100,"loginName"),

    RESET_PASSWORD(101,"123456"),
    ;

    private Integer code;

    private String value;

    ParameterEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

}
