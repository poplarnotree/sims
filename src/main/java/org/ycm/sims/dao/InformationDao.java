package org.ycm.sims.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ycm.sims.dto.ClassManagerDTO;
import org.ycm.sims.dto.PageDTO;
import org.ycm.sims.dto.RoleManagerDTO;
import org.ycm.sims.entity.Classes;
import org.ycm.sims.entity.Role;
import org.ycm.sims.entity.TeacherInformation;

import java.util.List;
import java.util.Map;

/**
 * Create by yangchangmin
 * on 2018/4/22 0:56
 */
@Mapper
public interface InformationDao {

    /*查询班级*/
    List<Classes> findClasses(Classes classes);

    /*创建班级*/
    Integer createClass(Classes classes);

    /*修改班级名称*/
    Integer updateClass(Classes classes);

    /*删除班级*/
    Integer deleteClass(String name);

   /*查询班级数*/
    Integer findClassCount(@Param("name") String name);

    /*查询班级人数*/
    Integer findClassStuCount(String classes);

    /*根据班级名称查询班级数*/
    Integer findClassByName(String name);

    /*根据班级名称查询班级ID*/
    Integer findClassIdByName(String name);

    /*查询最大工号*/
    String findNumberMax();

    /*查找教师信息*/
    TeacherInformation findByInformation(TeacherInformation teacherInformation);

    /*根据班级模糊查询教师信息*/
    List<TeacherInformation> findTeacherInformationByClasses(String classes);

    /*修改教师班级*/
    Integer updateTeacherClasses(TeacherInformation teacherInformation);

    /*查找登录名是否存在*/
    Integer findInformationLoginName(String loginName);

    /*查找工号是否存在*/
    Integer findInformationNumber(String number);

    /*写入信息*/
    Integer createInformation(TeacherInformation teacherInformation);

    /*查询教师信息*/
    List<TeacherInformation> informationList(Role role);

    /*根据id查询班级*/
    String findClassById(Integer id);

    /*查询教师信息数*/
    Integer teacherInformationCount(TeacherInformation teacherInformation);

    /*修改教师信息*/
    Integer updateTeacherInformation(TeacherInformation teacherInformation);

    /*查询教师部门*/
    Integer findTeacherDepartment(TeacherInformation teacherInformation);

    /*查询班级教师*/
    List<Map<String, String>> findClassTeacher(String classes);
}
