package org.ycm.sims.enums;

import lombok.Getter;

/**
 * Create by yangchangmin
 * on 2018/5/6 20:11
 */
@Getter
public enum ColumnEnum {

    LOGINPASSWORD(1, "login_password")

    ;
    private Integer code;

    private String value;

    ColumnEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
