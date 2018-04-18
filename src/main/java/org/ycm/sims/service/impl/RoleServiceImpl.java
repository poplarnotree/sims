package org.ycm.sims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.RoleVO;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.entity.Role;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ParameterEnum;
import org.ycm.sims.enums.ResultEnum;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.RoleService;
import org.ycm.sims.utils.MD5Util;

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
    public RoleVO login(String loginName, String loginPassword) {
        Role role = roleDao.findRoleByLoginName(loginName);
        if (role == null) {
            return new RoleVO(ResultEnum.ROLE_NULL);
        }
        if (role.getRoleStatus() != 0){
            return new RoleVO(ResultEnum.ROLE_INVALID);
        }
        if ((MD5Util.MD5Util(loginPassword)).equals(role.getLoginPassword())) {
            request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), loginName);
            request.getSession().setAttribute(ParameterEnum.ROLE_TYPE.getValue(), role.getRoleType());
            return new RoleVO(ResultEnum.SUCCESS, role.getRoleType());
        } else {
            return new RoleVO(ResultEnum.PASSWORD_ERROR);
        }

    }

    /**
     * 修改密码
     * @param originalPassword
     * @param newPassword
     * @return
     */
    @Override
    @Transactional
    public RoleVO updatePassword(String originalPassword, String newPassword) {
        String sessionLoginName = (String) request.getSession().getAttribute(ParameterEnum.LOGIN_NAME.getValue());
        Role role = roleDao.findRoleByLoginName(sessionLoginName);
        if (role == null) {
            throw new SimsException(ExceptionEnum.SYSTEM_ERROR);
        }
        if (MD5Util.MD5Util(originalPassword).equals(role.getLoginPassword())) {
            if (roleDao.updatePassword(sessionLoginName, MD5Util.MD5Util(newPassword))==1){
                return new RoleVO(ResultEnum.SUCCESS);
            }else{
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        } else {
            return new RoleVO(ResultEnum.PASSWORD_ERROR);
        }
    }

    /**
     * 创建角色
     * @param loginName
     * @param loginPassword
     * @param roleType
     * @return
     */
    @Override
    @Transactional
    public RoleVO createRole(String loginName, String loginPassword, int roleType) {
        String sessionLoginName = (String) request.getSession().getAttribute(ParameterEnum.LOGIN_NAME.getValue());
        Role role = roleDao.findRoleByLoginName(sessionLoginName);
        if (role == null){
            throw new SimsException(ExceptionEnum.SYSTEM_ERROR);
        }
        if (roleDao.findRoleByLoginName(loginName) != null){
            return new RoleVO(ResultEnum.ROLE_EXIST);
        }
        if (roleType - role.getRoleType() == 1){
            Role newRole = new Role(loginName, MD5Util.MD5Util(loginPassword), role.getCreateId(), roleType);
            int row = roleDao.createRole(newRole);
            if (row == 0){
                return new RoleVO(ResultEnum.ROLE_EXIST);
            }

            if (row == 1){
                return new RoleVO(ResultEnum.SUCCESS, roleType);
            } else {
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        }else{
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    /**
     * 注销帐号
     * @param id
     * @return
     */
    @Override
    @Transactional
    public RoleVO cancelRole(int id, int roleType) {
        String sessionLoginName = (String) request.getSession().getAttribute(ParameterEnum.LOGIN_NAME.getValue());
        Role role = roleDao.findRoleByLoginName(sessionLoginName);
        if (role == null){
            throw new SimsException(ExceptionEnum.SYSTEM_ERROR);
        }
        if (roleType - role.getRoleType() == 1) {
            if (roleDao.cancelRole(id) == 1){
                return new RoleVO(ResultEnum.SUCCESS);
            }else {
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        }else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    /**
     * 重置密码
     * @param id
     * @param roleType
     * @return
     */
    @Override
    @Transactional
    public RoleVO resetPassword(int id, int roleType) {
        String sessionLoginName = (String) request.getSession().getAttribute(ParameterEnum.LOGIN_NAME.getValue());
        Role role = roleDao.findRoleByLoginName(sessionLoginName);
        if (role == null){
            throw new SimsException(ExceptionEnum.SYSTEM_ERROR);
        }
        if (roleType - role.getRoleType() == 1) {
            if (roleDao.resetPassword(id, MD5Util.MD5Util(ParameterEnum.RESET_PASSWORD.getValue())) == 1){
                return new RoleVO(ResultEnum.SUCCESS);
            }else {
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        }else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }
}
