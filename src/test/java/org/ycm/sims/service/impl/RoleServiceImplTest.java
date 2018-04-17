package org.ycm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ycm.sims.dto.CheckDTO;
import org.ycm.sims.dto.LoginDTO;
import org.ycm.sims.entity.Role;
import org.ycm.sims.service.RoleService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

/**
 * Created by yangchangmin
 * on 2018/4/17 16:39
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

    private String LOGIN_PASSWORD = "123456";

    private String UPDATE_PASSWORD = "654321";

    @Test
    public void login() {
        LoginDTO loginDTO = roleService.login(LOGIN_NAME,LOGIN_PASSWORD);
        log.info("loginDTO={}",loginDTO);
    }

    @Test
    public void updatePassword() {
        request.getSession().setAttribute("loginName",LOGIN_NAME);
        CheckDTO checkDTO = roleService.updatePassword(LOGIN_PASSWORD, UPDATE_PASSWORD);
        log.info("checkDTO={}", checkDTO);
    }
}