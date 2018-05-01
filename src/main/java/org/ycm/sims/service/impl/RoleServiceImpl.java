package org.ycm.sims.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.VO.RoleCheckVO;
import org.ycm.sims.VO.RoleVO;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.dto.PageDTO;
import org.ycm.sims.dto.RoleDTO;
import org.ycm.sims.dto.RolePageDTO;
import org.ycm.sims.dto.UpdatePasswordDTO;
import org.ycm.sims.entity.Role;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ParameterEnum;
import org.ycm.sims.enums.ResultEnum;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.InformationService;
import org.ycm.sims.service.RoleService;
import org.ycm.sims.utils.FormatConversionUtil;
import org.ycm.sims.utils.MD5Util;
import org.ycm.sims.utils.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public RoleCheckVO login(RoleDTO roleDTO) {
        Role role = roleDao.findRoleByLoginName(roleDTO.getLoginName());
        if (role == null) {
            return new RoleCheckVO(ResultEnum.ROLE_NULL);
        }
        if (role.getRoleStatus() != 0){
            return new RoleCheckVO(ResultEnum.ROLE_INVALID);
        }
        if ((MD5Util.MD5Util(roleDTO.getLoginPassword())).equals(role.getLoginPassword())) {
            request.getSession().setAttribute(ParameterEnum.LOGIN_NAME.getValue(), roleDTO.getLoginName());
            request.getSession().setAttribute(ParameterEnum.ROLE_TYPE.getValue(), role.getRoleType());
            return new RoleCheckVO(ResultEnum.SUCCESS, role.getRoleType());
        } else {
            return new RoleCheckVO(ResultEnum.PASSWORD_ERROR);
        }

    }

    /**
     * 修改密码
     * @param updatePasswordDTO
     * @return
     */
    @Override
    @Transactional
    public RoleCheckVO updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        String sessionLoginName = (String) request.getSession().getAttribute(ParameterEnum.LOGIN_NAME.getValue());
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (MD5Util.MD5Util(updatePasswordDTO.getOriginalPassword()).equals(role.getLoginPassword())) {
            if (roleDao.updatePassword(new Role(sessionLoginName, MD5Util.MD5Util(updatePasswordDTO.getNewPassword())))==1){
                return new RoleCheckVO(ResultEnum.SUCCESS);
            }else{
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        } else {
            return new RoleCheckVO(ResultEnum.PASSWORD_ERROR);
        }
    }

    /**
     * 创建角色
     * @param roleDTO
     * @return
     */
    @Override
    @Transactional
    public RoleCheckVO createRole(RoleDTO roleDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (roleDao.findRoleByLoginName(roleDTO.getLoginName()) != null){
            return new RoleCheckVO(ResultEnum.ROLE_EXIST);
        }
        String roleType = roleDTO.getRoleType();
        if (FormatConversionUtil.roleTypeFormatUitl(roleType) - role.getRoleType() == 1){
            Role newRole = new Role(roleDTO.getLoginName(), MD5Util.MD5Util(roleDTO.getLoginName()), role.getCreateId(), FormatConversionUtil.roleTypeFormatUitl(roleDTO.getRoleType()));
            int row = roleDao.createRole(newRole);
            if (row == 0){
                return new RoleCheckVO(ResultEnum.ROLE_EXIST);
            }

            if (row == 1){
                return new RoleCheckVO(ResultEnum.SUCCESS, FormatConversionUtil.roleTypeFormatUitl(roleDTO.getRoleType()));
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
     * @param roleDTO
     * @return
     */
    @Override
    @Transactional
    public RoleCheckVO cancelRole(RoleDTO roleDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (FormatConversionUtil.roleTypeFormatUitl(roleDTO.getRoleType()) - role.getRoleType() == 1) {
            if (roleDao.cancelRole(roleDTO.getId()) == 1){
                return new RoleCheckVO(ResultEnum.SUCCESS);
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
     * @param roleDTO
     * @return
     */
    @Override
    @Transactional
    public RoleCheckVO resetPassword(RoleDTO roleDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (FormatConversionUtil.roleTypeFormatUitl(roleDTO.getRoleType()) - role.getRoleType() == 1) {
            if (roleDao.resetPassword(new Role(roleDTO.getId(), MD5Util.MD5Util(ParameterEnum.RESET_PASSWORD.getValue()))) == 1){
                return new RoleCheckVO(ResultEnum.SUCCESS);
            }else {
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        }else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    /**
     * 分页
     * @return
     */
    @Override
    public PageVO<RoleVO> rolePage(RolePageDTO rolePageDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (rolePageDTO.getRoleType() - role.getRoleType() == 1){
            int count = roleDao.roleCount(new Role(rolePageDTO.getLoginName(), rolePageDTO.getRoleType()));
            PageHelper.startPage(rolePageDTO.getPage(), rolePageDTO.getLimit());
            List<Role> roleList = roleDao.findRole(new Role(rolePageDTO.getLoginName(), rolePageDTO.getRoleType()));
            List<RoleVO> roleVOList = new ArrayList<RoleVO>();
            for (Role role1: roleList){
                roleVOList.add(new RoleVO(
                        role1.getId(), role1.getLoginName(),
                        FormatConversionUtil.roleTypeFormatUitl(role1.getRoleType()),
                        FormatConversionUtil.DateFormatUtil(role1.getCreateTime()))
                );
            }
            PageVO<RoleVO> pageVO = new PageVO(ResultEnum.SUCCESS, count,roleVOList);
            return pageVO;
        }else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

}
