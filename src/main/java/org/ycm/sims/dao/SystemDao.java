package org.ycm.sims.dao;

import org.apache.ibatis.annotations.Mapper;
import org.ycm.sims.entity.Record;

/**
 * Create by yangchangmin
 * on 2018/5/6 15:41
 */
@Mapper
public interface SystemDao {

    Integer addRecord(Record record);

}
