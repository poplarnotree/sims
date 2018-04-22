package org.ycm.sims.service;

import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.VO.NumberAndClassesVO;
import org.ycm.sims.dto.TeacherInformationDTO;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:03
 */
public interface InformationService {

    /*创建角色页面需要的信息*/
    NumberAndClassesVO createInformationVO();

    /*录入TeacherInformation*/
    CheckVO createInformation(TeacherInformationDTO teacherInformationDTO);


}
