package org.ycm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.VO.RecordVO;
import org.ycm.sims.dto.RecordPageDTO;
import org.ycm.sims.service.SystemService;

/**
 * Create by yangchangmin
 * on 2018/5/7 0:46
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @RequestMapping("/record")
    public String record(){
        return "/admin/record";
    }

    @RequestMapping("/recordList")
    @ResponseBody
    public PageVO<RecordVO> recordList(RecordPageDTO recordPageDTO){
        return systemService.recordPage(recordPageDTO);
    }

}
