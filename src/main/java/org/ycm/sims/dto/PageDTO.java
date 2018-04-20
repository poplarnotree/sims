package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/4/21 1:29
 */
@Data
public class PageDTO {

    private String loginName;

    private Integer page;

    private Integer limit;

    private Integer roleType;

    public PageDTO() {
    }

    public PageDTO(Integer page, Integer limit, Integer roleType) {
        this.page = page;
        this.limit = limit;
        this.roleType = roleType;
    }

    public PageDTO(String loginName, Integer page, Integer limit, Integer roleType) {
        this.loginName = loginName;
        this.page = page;
        this.limit = limit;
        this.roleType = roleType;
    }
}
