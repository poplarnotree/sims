package org.ycm.sims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.dto.CheckDTO;
import org.ycm.sims.dto.LoginDTO;
import org.ycm.sims.entity.Role;
import org.ycm.sims.enums.ResultEnum;
import org.ycm.sims.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Create by yangchangmin
 * 2018/4/14 21:52
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private HttpServletRequest request;

    @Override
    public Role findRoleByLoginName(String loginName) {
        return roleDao.findRoleByLoginName(loginName);
    }

    /**
     * 登录
     * @param loginName
     * @param loginPassword
     * @return
     */
    @Override
    public LoginDTO login(String loginName, String loginPassword) {
        Role role = roleDao.findRoleByLoginName(loginName);
        if (role==null){
            LoginDTO loginDTO = new LoginDTO(1, ResultEnum.ROLE_NULL.getMessage());
            return loginDTO;
        }else {
            if (role.getLoginPassword().equals(loginPassword)){
                request.getSession().setAttribute("loginName",loginName);
                LoginDTO loginDTO = new LoginDTO(0, ResultEnum.SUCCESS.getMessage(), role.getUserType());
                return loginDTO;
            }else{
                LoginDTO loginDTO = new LoginDTO(1, ResultEnum.PASSWORD_ERROR.getMessage());
                return loginDTO;
            }
        }
    }
}
