package org.ycm.sims.enums;

import lombok.Getter;

/**
 * Create by yangchangmin
 * on 2018/5/6 20:06
 */
@Getter
public enum SystemEnum {

    UPDATE(0, "修改")

    ,DELETE(1, "删除")

    ,LOGINPASSWORD(2, "")

    ;
    private Integer code;

    private String value;

    SystemEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
