package org.ycm.sims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.RoleVO;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.dto.RoleDTO;
import org.ycm.sims.entity.Role;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ParameterEnum;
import org.ycm.sims.enums.ResultEnum;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.RoleService;
import org.ycm.sims.utils.MD5Util;
import org.ycm.sims.utils.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
     * @param roleDTO
     * @return
     */
    @Override
    public RoleVO login(RoleDTO roleDTO) {
        Role role = roleDao.findRoleByLoginName(roleDTO.getLoginName());
        if (role == null) {
            return new RoleVO(ResultEnum.ROLE_NULL);
        }
        if (role.getRoleStatus() != 0){
            return new RoleVO(ResultEnum.ROLE_INVALID);
        }
        if ((MD5Util.MD5Util(roleDTO.getLoginPassword())).equals(role.getLoginPassword())) {
            request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), roleDTO.getLoginName());
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
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
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
     * @param roleDTO
     * @return
     */
    @Override
    @Transactional
    public RoleVO createRole(RoleDTO roleDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (roleDao.findRoleByLoginName(roleDTO.getLoginName()) != null){
            return new RoleVO(ResultEnum.ROLE_EXIST);
        }
        if (roleDTO.getRoleType() - role.getRoleType() == 1){
            Role newRole = new Role(roleDTO.getLoginName(), MD5Util.MD5Util(roleDTO.getLoginName()), role.getCreateId(), roleDTO.getRoleType());
            int row = roleDao.createRole(newRole);
            if (row == 0){
                return new RoleVO(ResultEnum.ROLE_EXIST);
            }

            if (row == 1){
                return new RoleVO(ResultEnum.SUCCESS, roleDTO.getRoleType());
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
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
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
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
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

    /**
     * 查询角色
     * @param roleDTO
     * @return
     */
    @Override
    public List<Role> findRole(RoleDTO roleDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (roleDTO.getRoleType() - role.getRoleType() == 1){
            List<Role> roleList = roleDao.findRole(new Role(roleDTO.getRoleType()));
            return roleList;
        }else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }
}
