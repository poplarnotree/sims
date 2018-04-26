package org.ycm.sims.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.*;
import org.ycm.sims.dao.InformationDao;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.dto.PageDTO;
import org.ycm.sims.dto.RoleDTO;
import org.ycm.sims.dto.TeacherInformationDTO;
import org.ycm.sims.entity.Role;
import org.ycm.sims.entity.TeacherInformation;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ResultEnum;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.InformationService;
import org.ycm.sims.service.RoleService;
import org.ycm.sims.utils.FormatConversionUtil;
import org.ycm.sims.utils.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:05
 */
@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private InformationDao informationDao;

    @Autowired
    private RoleService roleService;

    @Override
    public NumberAndClassesVO createInformationVO() {
        List<String> classesList = informationDao.findClasses();
        String maxNumber = informationDao.findNumberMax();
        NumberAndClassesVO numberAndClassesVO = new NumberAndClassesVO(classesList, maxNumber);
        return numberAndClassesVO;
    }

    @Override
    @Transactional
    public CheckVO createInformation(TeacherInformationDTO teacherInformationDTO) {
        RoleCheckVO roleCheckVO = roleService.createRole(new RoleDTO(
                        teacherInformationDTO.getLoginName(),
                        teacherInformationDTO.getLoginPassword(),
                        teacherInformationDTO.getRoleType()));
        if (roleCheckVO.getStatus() != 0){
            return new CheckVO(roleCheckVO.getStatus(), roleCheckVO.getMessage());
        }
        if (informationDao.findInformationLoginName(teacherInformationDTO.getLoginName()) >= 1){
            throw new SimsException(ResultEnum.INFORMATION_EXIST);
        }
        if (informationDao.findInformationNumber(teacherInformationDTO.getNumber()) >= 1){
            throw new SimsException(ResultEnum.NUMBER_EXIST);
        }
        TeacherInformation teacherInformation = new TeacherInformation();
        BeanUtils.copyProperties(teacherInformationDTO, teacherInformation);
        teacherInformation.setDepartment(FormatConversionUtil.ListFormatString(teacherInformationDTO.getDepartment()));
        teacherInformation.setClasses(FormatConversionUtil.ListFormatString(teacherInformationDTO.getClasses()));
        if (informationDao.createInformation(teacherInformation) == 1){
            return new CheckVO(ResultEnum.SUCCESS);
        }else {
            throw new SimsException(ExceptionEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public PageVO<TeacherInformationVO> teacherInformationPage(PageDTO pageDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (pageDTO.getRoleType() - role.getRoleType() == 1){
            int count = informationDao.teacherInformationCount(new TeacherInformation(pageDTO.getLoginName()));
            PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit());
            List<TeacherInformation> teacherInformationList = informationDao.teacherInformationList(pageDTO);
            List<TeacherInformationVO> teacherInformationVOList = new ArrayList<TeacherInformationVO>();
            for (TeacherInformation teacherInformation: teacherInformationList){
                TeacherInformationVO teacherInformationVO = new TeacherInformationVO();
                BeanUtils.copyProperties(teacherInformation, teacherInformationVO);
                teacherInformationVO.setCreateTime(FormatConversionUtil.DateFormatUtil(teacherInformation.getCreateTime()));
                teacherInformationVOList.add(teacherInformationVO);
            }
            PageVO<TeacherInformationVO> pageVO = new PageVO(ResultEnum.SUCCESS, count,teacherInformationVOList);
            return pageVO;
        }else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    public CheckVO updateTeacherInformation(TeacherInformationDTO teacherInformationDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (FormatConversionUtil.roleTypeFormatUitl(teacherInformationDTO.getRoleType()) - role.getRoleType() == 1){
            TeacherInformation teacherInformation = new TeacherInformation();
            BeanUtils.copyProperties(teacherInformationDTO, teacherInformation);
            teacherInformation.setDepartment(FormatConversionUtil.ListFormatString(teacherInformationDTO.getDepartment()));
            teacherInformation.setClasses(FormatConversionUtil.ListFormatString(teacherInformationDTO.getClasses()));
            int row = informationDao.updateTeacherInformation(teacherInformation);
            if (row == 1){
                return new CheckVO(ResultEnum.SUCCESS);
            }else{
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        }else {
            request.getSession().invalidate();
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }
}
