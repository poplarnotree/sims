package org.ycm.sims.entity;

import lombok.Data;

import java.util.Date;

/**
 * Create by yangchangmin
 * on 2018/5/6 15:33
 */
@Data
public class Record {

    private Integer id;

    private String loginName;

    private String tableName;

    private Integer keyId;

    private String updateColumn;

    private String updateValue;

    private String originalValue;

    private Integer type;

    private Date createTime;

}
