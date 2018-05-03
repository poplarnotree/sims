package org.ycm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ycm.sims.VO.*;
import org.ycm.sims.dto.ClassPageDTO;
import org.ycm.sims.dto.RolePageDTO;
import org.ycm.sims.service.InformationService;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 获取班级名称和最大工号
     * @throws Exception
     */
    @Test
    public void createInformationVO() throws Exception {
        NumberAndClassesVO numberAndClassesVO = informationService.createInformationVO();
        Assert.assertNotNull(numberAndClassesVO);
        log.info("NumberAndClassesVO = {}", numberAndClassesVO);
    }

    @Test
    public void createInformation() throws Exception {
        request.getSession().setAttribute("loginName","admin");
    }

    @Test
    public void teacherInformationPage() throws Exception {
        request.getSession().setAttribute("loginName","admin");
        RolePageDTO rolePageDTO = new RolePageDTO();
        rolePageDTO.setLimit(10);
        rolePageDTO.setLoginName("admin");
        rolePageDTO.setRoleType(1);
        rolePageDTO.setPage(1);
        PageVO<TeacherInformationVO> teacherInformationVOPageVO = informationService.teacherInformationPage(rolePageDTO);
        log.info("teacherInformationVOPageVO = {}", teacherInformationVOPageVO);
    }

    @Test
    public void updateTeacherInformation() throws Exception {
    }

    @Test
    public void classManage() {
        request.getSession().setAttribute("loginName","admin");
        CheckVO checkVO = informationService.classManage(new ClassPageDTO("高一(1)班"));
        log.info("checkVO={}" + checkVO);
    }

    @Test
    public void classPage() {
        request.getSession().setAttribute("loginName","admin");
        PageVO<ClassVO> classVOPageVO = informationService.classPage(new ClassPageDTO(1,2,""));
        log.info("classVOPageVO={}",classVOPageVO);
    }
}