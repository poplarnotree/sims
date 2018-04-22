package org.ycm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.VO.NumberAndClassesVO;
import org.ycm.sims.VO.RoleCheckVO;
import org.ycm.sims.dto.TeacherInformationDTO;
import org.ycm.sims.enums.ResultEnum;
import org.ycm.sims.service.InformationService;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:14
 */
@Controller
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @RequestMapping("/classes")
    @ResponseBody
    public NumberAndClassesVO department(){
        return informationService.createInformationVO();
    }

    @RequestMapping("/create")
    @ResponseBody
    public CheckVO create(@RequestBody TeacherInformationDTO teacherInformationDTO){

        return informationService.createInformation(teacherInformationDTO);
    }
}
