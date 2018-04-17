package org.ycm.sims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.dto.CheckDTO;
import org.ycm.sims.dto.LoginDTO;
import org.ycm.sims.entity.Role;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ResultEnum;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.RoleService;
import org.ycm.sims.utils.MD5Util;
import org.ycm.sims.utils.SessionUtil;

import javax.servlet.http.HttpServletRequest;


/**
 * Create by yangchangmin
 * 2018/4/14 21:52
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleDao roleDao;

    /**
     * 登录
     * @param loginName
     * @param loginPassword
     * @return
     */
    @Override
    public LoginDTO login(String loginName, String loginPassword) {
        Role role = roleDao.findRoleByLoginName(loginName);
        if (role == null) {
            return new LoginDTO(ResultEnum.ROLE_NULL.getCode(), ResultEnum.ROLE_NULL.getMessage());
        }
//        if (role.getLoginPassword().equals(loginPassword)) {
        if (MD5Util.MD5Util(loginPassword).equals(role.getLoginPassword())) {
            request.getSession().setAttribute("loginName", loginName);
            return new LoginDTO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), role.getUserType());
        } else {
            return new LoginDTO(ResultEnum.PASSWORD_ERROR.getCode(), ResultEnum.PASSWORD_ERROR.getMessage());
        }

    }

    /**
     * 修改密码
     *
     * @param originalPassword
     * @param newPassword
     * @return
     */
    @Override
    public CheckDTO updatePassword(String originalPassword, String newPassword) {
        String SESSION_LOGIN_NAME = (String) request.getSession().getAttribute("loginName");
        Role role = roleDao.findRoleByLoginName(SESSION_LOGIN_NAME);
        if (role == null) {
            throw new SimsException(ExceptionEnum.SYSTEM_ERROR);
        }
        if (MD5Util.MD5Util(originalPassword).equals(role.getLoginPassword())) {
            if (roleDao.updatePassword(SESSION_LOGIN_NAME, MD5Util.MD5Util(newPassword))==1){
                return new CheckDTO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
            }else{
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        } else {
            return new CheckDTO(ResultEnum.PASSWORD_ERROR.getCode(), ResultEnum.PASSWORD_ERROR.getMessage());
        }
    }
}
