package org.ycm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.VO.DepartmentVO;
import org.ycm.sims.service.InformationService;

import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:14
 */
@Controller
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @RequestMapping("/department")
    @ResponseBody
    public List<DepartmentVO> department(){
        return informationService.findDepartment();
    }
}
