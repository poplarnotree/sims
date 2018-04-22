package org.ycm.sims.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/4/22 22:06
 */
@Data
public class TeacherInformation {

    private int id;

    private String loginName;

    private String number;

    private String name;

    private String sex;

    private String idCart;

    private String positionalTitles;

    private String nation;

    private String place;

    private String address;

    private String birthday;

    private String phone;

    private String department;

    private String classes;

    private String subject;

    private Date createTime;

    public TeacherInformation() {
    }

    public TeacherInformation(String loginName) {
        this.loginName = loginName;
    }
}
