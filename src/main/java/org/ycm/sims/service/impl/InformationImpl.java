package org.ycm.sims.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ycm.sims.VO.DepartmentVO;
import org.ycm.sims.dao.InformationDao;
import org.ycm.sims.entity.Department;
import org.ycm.sims.service.InformationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:05
 */
@Service
public class InformationImpl implements InformationService {

    @Autowired
    private InformationDao informationDao;

    @Override
    public List<DepartmentVO> findDepartment() {

        List<Department> departmentList = informationDao.findDepartment();
        List<DepartmentVO> departmentVOList = new ArrayList<DepartmentVO>();

        for (Department department: departmentList){
            DepartmentVO departmentVO1 = new DepartmentVO();
            BeanUtils.copyProperties(department, departmentVO1);
            departmentVOList.add(departmentVO1);
        }
        return departmentVOList;
    }
}
