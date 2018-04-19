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
    private String newPassword;

    public UpdatePasswordDTO() {
    }

    public UpdatePasswordDTO(String originalPassword, String newPassword) {
        this.originalPassword = originalPassword;
        this.newPassword = newPassword;
    }
}
