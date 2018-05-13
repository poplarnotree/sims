package org.ycm.sims.service;

import org.ycm.sims.VO.*;
import org.ycm.sims.dto.*;
import org.ycm.sims.entity.StudentInformation;

import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:03
 */
public interface InformationService {

    /*创建角色页面需要的信息, 最大工号和班级名称*/
    NumberAndClassesVO createInformationVO();

    /*查询教师执教的班级*/
    NumberAndClassesVO teacherClasses();

    /*超管录入TeacherInformation*/
    CheckVO createInformation(TeacherInformationDTO teacherInformationDTO);

    /*超管查询所有教师信息*/
    PageVO<TeacherInformationVO> teacherInformationPage(RoleManagerDTO roleManagerDTO);

    /*超管修改教师信息*/
    CheckVO updateTeacherInformation(TeacherInformationDTO teacherInformationDTO);

    /*班级管理*/
    CheckVO classManage(ClassManagerDTO classManagerDTO);

    /*删除班级*/
    CheckVO deleteClass(String name);

    /*班级分页*/
    PageVO<ClassVO> classPage(ClassManagerDTO classManagerDTO);

    /*查询教师姓名科目*/
    List<TeacherSubjectNameVO> teacherSubNameList(RoleManagerDTO roleManagerDTO);

    /*修改教师班级*/
    CheckVO updateTeacherClass(UpdateTeacherClassDTO updateTeacherClassDTO);

    /*教师录入StudentInformation*/
    CheckVO createStudentInformation(StudentInformationDTO studentInformationDTO);

//    /*查询学生信息*/
//    PageVO<StudentInformationVO> studentInformationPage(RoleManagerDTO roleManagerDTO);

    /*修改学生信息*/
    CheckVO updateStudentInformation(StudentInformationDTO studentInformationDTO);

    /*教师查看个人信息*/
    TeacherInformationVO myInformationT();

    /*教师的学生*/
    PageVO<StudentInformationVO> myStudent(StudentInformationPageDTO studentInformationPageDTO);

    /*班级学生*/
    PageVO<StudentInformationVO> classesStudent(StudentInformationPageDTO studentInformationPageDTO);
}
