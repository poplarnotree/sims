package org.ycm.sims.dao;

import org.apache.ibatis.annotations.Param;
import org.ycm.sims.dto.TeacherInformationDTO;
import org.ycm.sims.entity.Classes;
import org.ycm.sims.entity.TeacherInformation;

import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/4/22 0:56
 */
public interface InformationDao {

    /*查询班级*/
    List<String> findClasses();

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




}
