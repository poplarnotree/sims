package org.ycm.sims.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.PageVO;
import org.ycm.sims.VO.RoleCheckVO;
import org.ycm.sims.VO.RoleVO;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.dto.RecordDTO;
import org.ycm.sims.dto.RoleDTO;
import org.ycm.sims.dto.RoleManagerDTO;
import org.ycm.sims.dto.UpdatePasswordDTO;
import org.ycm.sims.entity.Role;
import org.ycm.sims.enums.*;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.RoleService;
import org.ycm.sims.service.SystemService;
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

    @Autowired
    private SystemService systemService;

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
                systemService.addRecord(new RecordDTO(
                        role.getLoginName(), TableEnum.ROLE.getValue(), role.getId(), ColumnEnum.LOGIN_PASSWORD.getValue(),
                        updatePasswordDTO.getNewPassword(), updatePasswordDTO.getOriginalPassword()));
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
        String originalValue = roleDao.findRoleById(roleDTO.getId()).getRoleType().toString();
        if (FormatConversionUtil.roleTypeFormatUitl(roleDTO.getRoleType()) - role.getRoleType() == 1) {
            if (roleDao.cancelRole(roleDTO.getId()) == 1){
                String updateValue = roleDao.findRoleById(roleDTO.getId()).getRoleType().toString();
                systemService.addRecord(new RecordDTO(
                        role.getLoginName(), TableEnum.ROLE.getValue(), roleDTO.getId(),
                        ColumnEnum.ROLE_STATUS.getValue(), updateValue, originalValue));
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
        String updateValue = roleDao.findRoleById(roleDTO.getId()).getLoginPassword();
        if (FormatConversionUtil.roleTypeFormatUitl(roleDTO.getRoleType()) - role.getRoleType() == 1) {
            if (roleDao.resetPassword(new Role(roleDTO.getId(), MD5Util.MD5Util(ParameterEnum.RESET_PASSWORD.getValue()))) == 1){
                systemService.addRecord(new RecordDTO(
                        role.getLoginName(), TableEnum.ROLE.getValue(), roleDTO.getId(), ColumnEnum.LOGIN_PASSWORD.getValue(),
                        updateValue, MD5Util.MD5Util(ParameterEnum.RESET_PASSWORD.getValue())));
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
    public PageVO<RoleVO> rolePage(RoleManagerDTO roleManagerDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (roleManagerDTO.getRoleType() - role.getRoleType() == 1){
            int count = roleDao.roleCount(new Role(roleManagerDTO.getLoginName(), roleManagerDTO.getRoleType()));
            PageHelper.startPage(roleManagerDTO.getPage(), roleManagerDTO.getLimit());
            List<Role> roleList = roleDao.findRole(new Role(roleManagerDTO.getLoginName(), roleManagerDTO.getRoleType()));
            List<RoleVO> roleVOList = new ArrayList<RoleVO>();
            for (Role role1: roleList){
                String createName = roleDao.findRoleById(role1.getId()).getLoginName();
                roleVOList.add(new RoleVO(
                        role1.getId(), role1.getLoginName(),
                        FormatConversionUtil.roleTypeFormatUitl(role1.getRoleType()),createName,
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
