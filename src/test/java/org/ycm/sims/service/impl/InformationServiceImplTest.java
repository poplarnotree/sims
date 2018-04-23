package org.ycm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.VO.TeacherInformationVO;
import org.ycm.sims.dto.PageDTO;
import org.ycm.sims.service.InformationService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

/**
 * Create by yangchangmin
 * on 2018/4/24 0:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class InformationServiceImplTest {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private InformationService informationService;

    @Test
    public void createInformationVO() throws Exception {
    }

    @Test
    public void createInformation() throws Exception {
    }

    @Test
    public void teacherInformationPage() throws Exception {
        request.getSession().setAttribute("loginName","admin");
        PageDTO pageDTO = new PageDTO();
        pageDTO.setLimit(10);
        pageDTO.setLoginName("admin");
        pageDTO.setRoleType(1);
        pageDTO.setPage(1);
        PageVO<TeacherInformationVO> teacherInformationVOPageVO = informationService.teacherInformationPage(pageDTO);
        log.info("teacherInformationVOPageVO = {}", teacherInformationVOPageVO);


    }

}