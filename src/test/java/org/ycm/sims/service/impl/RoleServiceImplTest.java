package org.ycm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ycm.sims.entity.Role;
import org.ycm.sims.service.RoleService;

import static org.junit.Assert.*;

/**
 * Create by yangchangmin
 * 2018/4/14 21:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void findRoleByLoginName() throws Exception {
        String loginName = "admin";
        Role role = roleService.findRoleByLoginName(loginName);
        Assert.assertNotNull(role);
        log.info(role.toString());
    }

}