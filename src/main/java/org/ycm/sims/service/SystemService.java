package org.ycm.sims.service;

import org.ycm.sims.VO.PageVO;
import org.ycm.sims.VO.RecordVO;
import org.ycm.sims.dto.PageDTO;
import org.ycm.sims.dto.RecordDTO;
import org.ycm.sims.dto.RecordPageDTO;

/**
 * Create by yangchangmin
 * on 2018/5/6 16:43
 */
public interface SystemService {

    void addRecord(RecordDTO recordDTO);

    PageVO<RecordVO> recordPage(RecordPageDTO recordPageDTO);

}
