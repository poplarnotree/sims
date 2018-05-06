package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/5/7 0:32
 */
@Data
public class RecordPageDTO extends PageDTO{

    private Integer id;

    public RecordPageDTO() {
    }

    public RecordPageDTO(Integer page, Integer limit) {
        super(page, limit);
    }

    public RecordPageDTO(Integer page, Integer limit, Integer id) {
        super(page, limit);
        this.id = id;
    }
}
