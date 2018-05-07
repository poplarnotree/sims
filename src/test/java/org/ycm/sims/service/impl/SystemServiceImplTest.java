package org.ycm.sims.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ycm.sims.VO.CheckVO;
import org.ycm.sims.dto.MenuDTO;
import org.ycm.sims.enums.ParameterEnum;
import org.ycm.sims.service.RoleService;
import org.ycm.sims.service.SystemService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

/**
 * Create by yangchangmin
 * on 2018/5/8 1:27
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SystemServiceImplTest {
    @Autowired
    private SystemService systemService;

    @Autowired
    private HttpServletRequest request;

    @Test
    public void updateMenu() throws Exception {
    }

    @Test
    public void deleteMenu() throws Exception {
        request.getSession().setAttribute("loginName","admin");
        CheckVO checkVO = systemService.deleteMenu(1001);
        log.info("checkVO={}",checkVO);
    }

    @Test
    public void addMenu() throws Exception {
        request.getSession().setAttribute("loginName","admin");
        MenuDTO menuDTO = new MenuDTO("角色管理", "角色", 1, 0, "/role/roleList");
        CheckVO checkVO = systemService.addMenu(menuDTO);
        log.info("checkVO={}",checkVO);
    }

}