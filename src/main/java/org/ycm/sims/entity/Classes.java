package org.ycm.sims.entity;

import lombok.Data;

import java.util.Date;

/**
 * Create by yangchangmin
 * on 2018/4/22 17:02
 */
@Data
public class Classes {

    /*id*/
    private Integer id;

    /*班级名称*/
    private String name;

    /*创建人id*/
    private Integer createId;

    /*创建时间*/
    private Date createTime;

    public Classes() {
    }

    public Classes(String name, Integer createId) {
        this.name = name;
        this.createId = createId;
    }
}
