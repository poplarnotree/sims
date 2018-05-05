package org.ycm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ycm.sims.VO.*;
import org.ycm.sims.dto.ClassManagerDTO;
import org.ycm.sims.dto.RoleManagerDTO;
import org.ycm.sims.service.InformationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        RoleManagerDTO roleManagerDTO = new RoleManagerDTO();
        roleManagerDTO.setLimit(10);
        roleManagerDTO.setLoginName("admin");
        roleManagerDTO.setRoleType(1);
        roleManagerDTO.setPage(1);
        PageVO<TeacherInformationVO> teacherInformationVOPageVO = informationService.teacherInformationPage(roleManagerDTO);
        log.info("teacherInformationVOPageVO = {}", teacherInformationVOPageVO);
    }

    @Test
    public void updateTeacherInformation() throws Exception {
    }

    @Test
    public void classManage() {
        request.getSession().setAttribute("loginName","admin");
        CheckVO checkVO = informationService.classManage(new ClassManagerDTO(1002,"高三(3)班"));
        log.info("checkVO={}" + checkVO);
    }

    @Test
    public void classPage() {
        request.getSession().setAttribute("loginName","admin");
        PageVO<ClassVO> classVOPageVO = informationService.classPage(new ClassManagerDTO(1,2,""));
        log.info("classVOPageVO={}",classVOPageVO);
    }

    @Test
    public void deleteClass() throws Exception {
        request.getSession().setAttribute("loginName","admin");
        CheckVO checkVO = informationService.deleteClass("高一(7)班");
        log.info("checkVO={}",checkVO);
    }

    @Test
    public void teacherSubNameList() throws Exception {
        request.getSession().setAttribute("loginName","admin");
        List<TeacherSubjectNameVO> teacherSubjectNameVOList = informationService.teacherSubNameList(new RoleManagerDTO(1));
        log.info("teacherSubjectNameVOList={}",teacherSubjectNameVOList);
    }
}