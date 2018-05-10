package org.ycm.sims.entity;

import lombok.Data;

import java.util.Date;

/**
 * Create by yangchangmin
 * on 2018/5/10 1:56
 */
@Data
public class StudentInformation {

    private int id;

    private String loginName;

    private String number;

    private String name;

    private String sex;

    private String idCart;

    private String nation;

    private String place;

    private String address;

    private String birthday;

    private String phone;

    private String classes;

    private String enrolmentTime;

    private Date createTime;

    public StudentInformation() {
    }

    public StudentInformation(String loginName) {
        this.loginName = loginName;
    }
}
