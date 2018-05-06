package org.ycm.sims.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.dao.SystemDao;
import org.ycm.sims.dto.RecordDTO;
import org.ycm.sims.entity.Record;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.SystemService;

/**
 * Create by yangchangmin
 * on 2018/5/6 16:50
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemDao systemDao;

    @Override
    @Transactional
    public void addRecord(RecordDTO recordDTO) {
        try {
            Record record = new Record();
            BeanUtils.copyProperties(recordDTO, record);
            if (recordDTO.getUpdateColumn() == null){
                record.setType(1);
            }
            Integer row = systemDao.addRecord(record);
            if (row != 1){
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
        }
    }
}
