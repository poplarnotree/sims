package org.ycm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.RoleVO;
import org.ycm.sims.enums.ParameterEnum;
import org.ycm.sims.service.RoleService;
import javax.servlet.http.HttpServletRequest;


/**
 * Create by yangchangmin
 * on 2018/4/18 1:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private HttpServletRequest request;

    private String LOGIN_NAME = "admin";

    private String NEW_LOGIN_NAME = "zhangsan";

    private String NEW_LOGIN_NAME_1 = "lisi";

    private String LOGIN_PASSWORD = "123456";

    private String UPDATE_PASSWORD = "654321";

    private int zhangsan_id = 1006;

    @Test
    public void login() throws Exception {
        RoleVO roleVO = roleService.login(LOGIN_NAME, LOGIN_PASSWORD);
        RoleVO roleVO1 = roleService.login(LOGIN_NAME, UPDATE_PASSWORD);
        RoleVO roleVO2 = roleService.login(NEW_LOGIN_NAME, UPDATE_PASSWORD);
        log.info("roleVO={}",roleVO);
        log.info("roleVO1={}",roleVO1);
        log.info("roleVO2={}",roleVO2);
    }

    @Test
    @Transactional
    public void updatePassword() throws Exception {
        request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), LOGIN_NAME);
        RoleVO roleVO = roleService.updatePassword(LOGIN_PASSWORD, UPDATE_PASSWORD);
        RoleVO roleVO1 = roleService.updatePassword(LOGIN_PASSWORD, UPDATE_PASSWORD);
        log.info("roleVO={}", roleVO);
        log.info("roleVO1={}", roleVO1);
    }

    @Test
//    @Transactional
    public void createRole() throws Exception {
        request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), LOGIN_NAME);
        RoleVO roleVO = roleService.createRole(NEW_LOGIN_NAME, LOGIN_PASSWORD,ParameterEnum.TEACHER_TYPE.getCode());
        log.info("roleVO={}",roleVO);
//        RoleVO roleVO1 = roleService.createRole(NEW_LOGIN_NAME, LOGIN_PASSWORD,ParameterEnum.TEACHER_TYPE.getCode());
//        log.info("roleVO1={}",roleVO1);
//        RoleVO roleVO2 = roleService.createRole(NEW_LOGIN_NAME_1, LOGIN_PASSWORD, ParameterEnum.STUDENT_TYPE.getCode());
//        log.info("roleVO2={}",roleVO2);
    }

    @Test
    public void cancelRole() throws Exception {
        request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), LOGIN_NAME);
        RoleVO roleVO = roleService.cancelRole(zhangsan_id, ParameterEnum.TEACHER_TYPE.getCode());
        log.info("roleVO={}",roleVO);
    }

    @Test
    public void resetPassword() throws Exception {
        request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), LOGIN_NAME);
        RoleVO roleVO = roleService.resetPassword(zhangsan_id, ParameterEnum.TEACHER_TYPE.getCode());
        log.info("roleVO={}",roleVO);
    }

}