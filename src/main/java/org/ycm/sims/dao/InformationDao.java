package org.ycm.sims.dao;

import org.apache.ibatis.annotations.Mapper;
import org.ycm.sims.VO.TeacherVO;
import org.ycm.sims.dto.PageDTO;
import org.ycm.sims.entity.Classes;
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
    List<Classes> findClasses();

    /*创建班级*/
    Integer createClass(String name);

    /*修改班级名称*/
    Integer updateClass(int id, String name);

    /*查询班级数*/
    Integer findClassCount();

    /*根据班级名称查询班级*/
    Integer findClassByName(String name);

    /*查询最大工号*/
    String findNumberMax();

    /*根据登录名查找教师信息*/
    TeacherInformation findByInformation(TeacherInformation teacherInformation);

    /*查找登录名是否存在*/
    Integer findInformationLoginName(String loginName);

    /*查找工号是否存在*/
    Integer findInformationNumber(String number);

    /*写入信息*/
    Integer createInformation(TeacherInformation teacherInformation);

    /*查询教师信息*/
    List<TeacherInformation> informationList(PageDTO pageDTO);

    /*查询教师信息数*/
    Integer teacherInformationCount(TeacherInformation teacherInformation);

    /*超管修改教师信息*/
    Integer updateTeacherInformation(TeacherInformation teacherInformation);

    /*查询教师部门*/
    Integer findTeacherDepartment(TeacherInformation teacherInformation);

    /*查询班级教师*/
    List<Map<String, String>> findClassTeacher(String classes);

}
