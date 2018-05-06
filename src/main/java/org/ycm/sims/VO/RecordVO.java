package org.ycm.sims.VO;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/5/7 0:27
 */
@Data
public class RecordVO {

    private static String NO_DATA = "无";

    private Integer id;

    private String loginName;

    private String tableName;

    private Integer keyId;

    private String updateColumn = NO_DATA;

    private String updateValue = NO_DATA;

    private String originalValue;

    private Integer type;

    private String createTime;

}
