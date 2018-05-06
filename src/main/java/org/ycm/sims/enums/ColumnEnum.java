package org.ycm.sims.enums;

import lombok.Getter;

/**
 * Create by yangchangmin
 * on 2018/5/6 20:11
 */
@Getter
public enum ColumnEnum {

    LOGIN_PASSWORD(1, "login_password")

    ,ROLE_STATUS(2, "role_status")

    ,LOGIN_NAME(3, "login_name")

    ,NUMBER(4, "number")

    ,NAME(5, "name")

    ,SEX(6, "sex")

    ,ID_CART(7, "id_cart")

    ,POSITIONAL_TITLES(8, "positional_titles")

    ,NATION(9, "nation")

    ,PLACE(10, "place")

    ,ADDRESS(11, "address")

    ,BIRTHDAY(12, "birthday")

    ,PHONE(13, "phone")

    ,DEPARTMENT(14, "department")

    ,CLASSES(15, "classes")

    ,SUBJECT(16, "subject")

    ;
    private Integer code;

    private String value;

    ColumnEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
