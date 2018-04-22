package org.ycm.sims.dto;

import lombok.Data;

import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/4/22 20:11
 */
@Data
public class TeacherInformationDTO {

    private String loginName;

    private String loginPassword;

    private String roleType;

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

    private List<String> department;

    private List<String> classes;

    private String subject;

    public TeacherInformationDTO() {
    }

    public TeacherInformationDTO(String loginName, String loginPassword, String roleType, String number, String name, String sex, String idCart, String positionalTitles, String nation, String place, String address, String birthday, String phone, List<String> department, List<String> classes, String subject) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.roleType = roleType;
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.idCart = idCart;
        this.positionalTitles = positionalTitles;
        this.nation = nation;
        this.place = place;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.department = department;
        this.classes = classes;
        this.subject = subject;
    }
}
