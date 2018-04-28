package org.ycm.sims.dto;

import lombok.Data;

/**
 * Created by yangchangmin
 * on 2018/4/28 10:59
 */
@Data
public class ClassPageDTO extends PageDTO {

    private Integer id;

    private String name;

    public ClassPageDTO() {
    }

    public ClassPageDTO(String name) {
        this.name = name;
    }

    public ClassPageDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClassPageDTO(Integer page, Integer limit, String name) {
        super(page, limit);
        this.name = name;
    }
}
