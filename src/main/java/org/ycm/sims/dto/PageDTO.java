package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/4/21 1:29
 */
@Data
public class PageDTO {

    private Integer page;

    private Integer limit;

    public PageDTO() {
    }

    public PageDTO(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }
}
