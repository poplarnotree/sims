package org.ycm.sims.VO;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/5/10 0:16
 */
@Data
public class HeadVO {

    private String name;

    private String type;

    public HeadVO() {
    }

    public HeadVO(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
