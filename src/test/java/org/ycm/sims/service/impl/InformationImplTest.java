package org.ycm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.ycm.sims.VO.DepartmentVO;
import org.ycm.sims.entity.Department;
import org.ycm.sims.service.InformationService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class InformationImplTest {

    @Autowired
    private InformationService informationService;

    @Test
    public void findDepartment() throws Exception {
        List<DepartmentVO> departmentVOList = informationService.findDepartment();
        log.info("departmentVOList={}", departmentVOList);
    }

}