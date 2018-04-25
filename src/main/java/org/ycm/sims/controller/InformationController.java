package org.ycm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.VO.*;
import org.ycm.sims.dto.PageDTO;
import org.ycm.sims.dto.TeacherInformationDTO;
import org.ycm.sims.service.InformationService;
import org.ycm.sims.utils.ControllerJumpUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:14
 */
@Controller
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private HttpServletRequest request;

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

    @RequestMapping("/teacherList")
    public String teacherList(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "admin/information", "role/information");
    }

    @RequestMapping("/teacherPage")
    @ResponseBody
    public PageVO<TeacherInformationVO> teacherPage(PageDTO pageDTO){
        System.out.println("-----------"+pageDTO);
        return informationService.teacherInformationPage(pageDTO);
    }
}
