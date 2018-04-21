package org.ycm.sims.entity;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/4/22 0:58
 */
@Data
public class Department {

    /*id*/
    private int id;

    /*部门名称*/
    private String name;

    /*创建时间*/
    private Data createTime;

    public Department() {
    }
}
