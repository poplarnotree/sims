package org.ycm.sims.dto;

import lombok.Data;

/**
 * Created by yangchangmin
 * on 2018/4/28 10:59
 */
@Data
public class ClassManagerDTO extends PageDTO {

    private Integer id;

    private String name;

    public ClassManagerDTO() {
    }

    public ClassManagerDTO(String name) {
        this.name = name;
    }

    public ClassManagerDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClassManagerDTO(Integer page, Integer limit, String name) {
        super(page, limit);
        this.name = name;
    }
}
