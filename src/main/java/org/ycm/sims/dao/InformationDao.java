package org.ycm.sims.dao;

import org.ycm.sims.entity.Department;

import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/4/22 0:56
 */
public interface InformationDao {

    /*查询部门*/
    List<Department> findDepartment();

}
