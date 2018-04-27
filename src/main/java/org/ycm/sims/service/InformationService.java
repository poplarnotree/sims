package org.ycm.sims.service;

import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.VO.NumberAndClassesVO;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.VO.TeacherInformationVO;
import org.ycm.sims.dto.TeacherInformationDTO;
import org.ycm.sims.dto.PageDTO;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:03
 */
public interface InformationService {

    /*创建角色页面需要的信息*/
    NumberAndClassesVO createInformationVO();

    /*超管录入TeacherInformation*/
    CheckVO createInformation(TeacherInformationDTO teacherInformationDTO);

    /*超管查询所有教师信息*/
    PageVO<TeacherInformationVO> teacherInformationPage(PageDTO pageDTO);

    /*超管修改教师信息*/
    CheckVO updateTeacherInformation(TeacherInformationDTO teacherInformationDTO);

    /*创建班级*/
    CheckVO createClass(String name);

}
