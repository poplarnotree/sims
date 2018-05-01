package org.ycm.sims.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ycm.sims.VO.*;
import org.ycm.sims.converter.Map2TeacherVO;
import org.ycm.sims.dao.InformationDao;
import org.ycm.sims.dao.RoleDao;
import org.ycm.sims.dto.*;
import org.ycm.sims.entity.Classes;
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
import java.util.Map;

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
        List<Classes> classesList = informationDao.findClasses();
        List<String> classList = new ArrayList<>();
        for (Classes classes : classesList){
            classList.add(classes.getName());
        }
        String maxNumber = informationDao.findNumberMax();
        NumberAndClassesVO numberAndClassesVO = new NumberAndClassesVO(classList, maxNumber);
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
    public PageVO<TeacherInformationVO> teacherInformationPage(RolePageDTO rolePageDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (rolePageDTO.getRoleType() - role.getRoleType() == 1){
            int count = informationDao.teacherInformationCount(new TeacherInformation(rolePageDTO.getLoginName()));
            PageHelper.startPage(rolePageDTO.getPage(), rolePageDTO.getLimit());
            List<TeacherInformation> teacherInformationList = informationDao.informationList(rolePageDTO);
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
    @Transactional
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

    @Override
    @Transactional
    public CheckVO classManage(ClassPageDTO classPageDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        int departmentCount = informationDao.findTeacherDepartment(new TeacherInformation(role.getLoginName(), "学生处"));
        int classCount = informationDao.findClassByName(classPageDTO.getName());
        if (classCount == 1){
            return new CheckVO(ResultEnum.CLASS_EXIST);
        }
        if (role.getRoleType() == 0 || departmentCount == 1){
            int row;
            if (classPageDTO.getId() == null){
                row = informationDao.createClass(classPageDTO.getName());
            }else{
                row = informationDao.updateClass(classPageDTO.getId(), classPageDTO.getName());
            }
            if (row == 1){
                return new CheckVO(ResultEnum.SUCCESS);
            }
            throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
        }else {
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    public PageVO<ClassVO> classPage(ClassPageDTO classPageDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        int departmentCount = informationDao.findTeacherDepartment(new TeacherInformation(role.getLoginName(), "学生处"));
        if (role.getRoleType() == 0 || departmentCount == 1){
            int count = informationDao.findClassCount();
            PageHelper.startPage(classPageDTO.getPage(), classPageDTO.getLimit());
            List<Classes> classesList = informationDao.findClasses();
            List<ClassVO> classVOList = new ArrayList<>();
            for (int i = 0; i < classesList.size(); i++){
                ClassVO classVO = new ClassVO();
                BeanUtils.copyProperties(classesList.get(i), classVO);
                classVOList.add(classVO);
                List<Map<String, String>> teacherList = informationDao.findClassTeacher(classesList.get(i).getName());
                TeacherVO teacherVO = Map2TeacherVO.map2TeacherVO(teacherList);
                classVOList.get(i).setTeacherVO(teacherVO);
                classVOList.get(i).setCreateTime(FormatConversionUtil.DateFormatUtil(classesList.get(i).getCreateTime()));
            }
            return new PageVO<ClassVO>(ResultEnum.SUCCESS, count,classVOList);
        }else {
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }
}
