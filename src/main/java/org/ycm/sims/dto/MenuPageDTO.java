package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/5/8 0:58
 */
@Data
public class MenuPageDTO extends PageDTO{

    private Integer id;

    public MenuPageDTO() {
    }

    public MenuPageDTO(Integer page, Integer limit) {
        super(page, limit);
    }

    public MenuPageDTO(Integer page, Integer limit, Integer id) {
        super(page, limit);
        this.id = id;
    }
}
