package org.ycm.sims.dto;


import lombok.Data;

/**
 * Create by yangchangmin
 * 2018/4/15 15:38
 */
@Data
public class LoginDTO extends CheckDTO {

    private int userType;

    public LoginDTO(int status, String message) {
        super(status, message);
    }

    public LoginDTO(int status, String message, int userType) {
        super(status, message);
        this.userType = userType;
    }

}
