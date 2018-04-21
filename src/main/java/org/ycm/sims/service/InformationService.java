package org.ycm.sims.service;

import org.ycm.sims.VO.DepartmentVO;

import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:03
 */
public interface InformationService {

    /*查询部门*/
    List<DepartmentVO> findDepartment();

}
