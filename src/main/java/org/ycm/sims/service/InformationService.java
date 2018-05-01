package org.ycm.sims.service;

import org.ycm.sims.VO.*;
import org.ycm.sims.dto.ClassPageDTO;
import org.ycm.sims.dto.RolePageDTO;
import org.ycm.sims.dto.TeacherInformationDTO;

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
    PageVO<TeacherInformationVO> teacherInformationPage(RolePageDTO rolePageDTO);

    /*超管修改教师信息*/
    CheckVO updateTeacherInformation(TeacherInformationDTO teacherInformationDTO);

    /*班级管理*/
    CheckVO classManage(ClassPageDTO classPageDTO);

    PageVO<ClassVO> classPage(ClassPageDTO classPageDTO);
}
