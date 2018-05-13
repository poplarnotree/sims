package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/5/13 19:51
 */
@Data
public class StudentInformationPageDTO extends PageDTO {

    private String loginNameT;

    private String loginNameS;

    private String classes;

    public StudentInformationPageDTO() {
    }

    public StudentInformationPageDTO(String classes) {
        this.classes = classes;
    }

    public StudentInformationPageDTO(Integer page, Integer limit, String loginNameS, String classes) {
        super(page, limit);
        this.loginNameS = loginNameS;
        this.classes = classes;
    }
}
