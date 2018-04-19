package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/4/19 21:57
 */
@Data
public class UpdatePasswordDTO {

    /* 原密码 */
    private String originalPassword;

    /* 新密码 */
    private String newPasswor;

    public UpdatePasswordDTO(String originalPassword, String newPasswor) {
        this.originalPassword = originalPassword;
        this.newPasswor = newPasswor;
    }
}
