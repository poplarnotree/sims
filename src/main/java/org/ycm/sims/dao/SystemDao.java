package org.ycm.sims.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ycm.sims.dto.RecordPageDTO;
import org.ycm.sims.entity.Record;

import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/5/6 15:41
 */
@Mapper
public interface SystemDao {

    Integer addRecord(Record record);

    Integer recordCount();

    List<Record> recordList(@Param("id") Integer id);

}
