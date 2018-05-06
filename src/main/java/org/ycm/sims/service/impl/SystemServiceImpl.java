package org.ycm.sims.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.VO.RecordVO;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.dao.SystemDao;
import org.ycm.sims.dto.RecordDTO;
import org.ycm.sims.dto.RecordPageDTO;
import org.ycm.sims.entity.Record;
import org.ycm.sims.entity.Role;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ResultEnum;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.SystemService;
import org.ycm.sims.utils.FormatConversionUtil;
import org.ycm.sims.utils.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/5/6 16:50
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleDao roleDao;

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

    @Override
    public PageVO<RecordVO> recordPage(RecordPageDTO recordPageDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (role.getRoleType() == 0) {
            int count = systemDao.recordCount();
            PageHelper.startPage(recordPageDTO.getPage(), recordPageDTO.getLimit());
            List<Record> recordList = systemDao.recordList(recordPageDTO.getId());
            List<RecordVO> recordVOList = new ArrayList<>();
            for (Record record : recordList) {
                RecordVO recordVO = new RecordVO();
                BeanUtils.copyProperties(record, recordVO);
                recordVO.setCreateTime(FormatConversionUtil.DateFormatUtil(record.getCreateTime()));
                recordVOList.add(recordVO);
            }
            PageVO<RecordVO> recordVOPageVO = new PageVO<>(ResultEnum.SUCCESS, count, recordVOList);
            return recordVOPageVO;
        } else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }
}
