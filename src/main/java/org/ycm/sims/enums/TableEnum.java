package org.ycm.sims.enums;

import lombok.Getter;

/**
 * Create by yangchangmin
 * on 2018/5/6 15:57
 */
@Getter
public enum TableEnum {

    ROLE(1, "role")

    ,TEACHER_INFORMATION(2, "teacher_information")

    ,STUDENT_INFORMATION(3, "student_information")

    ,CLASSES(4, "classes")

    ,ACHIEVEMENT(5, "achievement")

    ,menu(6, "menu")

    ;
    private Integer code;

    private String value;

    TableEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }


}
