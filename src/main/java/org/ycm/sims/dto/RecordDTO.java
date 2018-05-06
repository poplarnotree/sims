package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/5/6 16:44
 */
@Data
public class RecordDTO {

    private String loginName;

    private String tableName;

    private Integer keyId;

    private String updateColumn;

    private String updateValue;

    private String originalValue;

    private Integer type;

    public RecordDTO() {
    }

    public RecordDTO(String loginName, String tableName, Integer keyId) {
        this.loginName = loginName;
        this.tableName = tableName;
        this.keyId = keyId;
        this.type = type;
    }

    public RecordDTO(String loginName, String tableName, Integer keyId, String updateColumn, String updateValue, String originalValue) {
        this.loginName = loginName;
        this.tableName = tableName;
        this.keyId = keyId;
        this.updateColumn = updateColumn;
        this.updateValue = updateValue;
        this.originalValue = originalValue;
        this.type = type;
    }
}
