package org.ycm.sims.service.impl;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.RoleCheckVO;
import org.ycm.sims.VO.RoleVO;
import org.ycm.sims.dto.RoleDTO;
import org.ycm.sims.dto.UpdatePasswordDTO;
import org.ycm.sims.entity.Role;
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

    private String NEW_LOGIN_NAME = "mashisi";

    private String NEW_LOGIN_NAME_1 = "lisi";

    private String LOGIN_PASSWORD = "123456";

    private String UPDATE_PASSWORD = "654321";

    private int zhangsan_id = 1006;

    @Test
    public void login() throws Exception {
        RoleDTO roleDTO = new RoleDTO(LOGIN_NAME, LOGIN_PASSWORD);
        RoleCheckVO roleCheckVO = roleService.login(roleDTO);
        log.info("roleCheckVO={}", roleCheckVO);
    }

    @Test
    public void updatePassword() throws Exception {
        request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), LOGIN_NAME);
        RoleCheckVO roleCheckVO = roleService.updatePassword(new UpdatePasswordDTO(LOGIN_PASSWORD, UPDATE_PASSWORD));
//        RoleCheckVO roleCheckVO1 = roleService.updatePassword(new UpdatePasswordDTO(LOGIN_PASSWORD, UPDATE_PASSWORD));
        log.info("roleCheckVO={}", roleCheckVO);
//        log.info("roleCheckVO1={}", roleCheckVO1);
    }

    @Test
    public void createRole() throws Exception {
        request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), LOGIN_NAME);
        RoleDTO roleDTO = new RoleDTO(NEW_LOGIN_NAME, LOGIN_PASSWORD,ParameterEnum.TEACHER_TYPE.getCode());
        RoleCheckVO roleCheckVO = roleService.createRole(roleDTO);
        log.info("roleCheckVO={}", roleCheckVO);
//        RoleCheckVO roleVO1 = roleService.createRole(NEW_LOGIN_NAME, LOGIN_PASSWORD,ParameterEnum.TEACHER_TYPE.getCode());
//        log.info("roleVO1={}",roleVO1);
//        RoleCheckVO roleVO2 = roleService.createRole(NEW_LOGIN_NAME_1, LOGIN_PASSWORD, ParameterEnum.STUDENT_TYPE.getCode());
//        log.info("roleVO2={}",roleVO2);
    }

    @Test
    public void cancelRole() throws Exception {
        request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), LOGIN_NAME);
        RoleCheckVO roleCheckVO = roleService.cancelRole(new RoleDTO(zhangsan_id, ParameterEnum.TEACHER_TYPE.getCode()));
        log.info("roleCheckVO={}", roleCheckVO);
    }

    @Test
    public void resetPassword() throws Exception {
        request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), LOGIN_NAME);
        RoleCheckVO roleCheckVO = roleService.resetPassword(new RoleDTO(zhangsan_id, ParameterEnum.TEACHER_TYPE.getCode()));
        log.info("roleCheckVO={}", roleCheckVO);
    }

    @Test
    public void findRole() throws Exception {
        request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), LOGIN_NAME);
        PageInfo<RoleVO> roleVOPageInfo = roleService.findRole(new RoleDTO(1),1);
        log.info("roleVOPageInfo={}", roleVOPageInfo);
    }

}