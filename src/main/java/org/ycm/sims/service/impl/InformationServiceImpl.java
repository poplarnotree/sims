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
import org.ycm.sims.entity.*;
import org.ycm.sims.enums.*;
import org.ycm.sims.exception.SimsException;
import org.ycm.sims.service.InformationService;
import org.ycm.sims.service.RoleService;
import org.ycm.sims.service.SystemService;
import org.ycm.sims.utils.CompareDataUtil;
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

    @Autowired
    private SystemService systemService;

    @Override
    public NumberAndClassesVO createInformationVO() {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        List<Classes> classesList = informationDao.findClasses(new Classes());
        List<String> classList = new ArrayList<>();
        for (Classes classes : classesList){
            classList.add(classes.getName());
        }
        String maxNumber = null;
        if (role.getRoleType() == 0){
            maxNumber = informationDao.findNumberMax();
        }
        if (role.getRoleType() == 1){
            maxNumber = informationDao.findStudentNumberMax();
        }
        if (role.getRoleType() != 0 && role.getRoleType() != 1){
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
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
    public PageVO<TeacherInformationVO> teacherInformationPage(RoleManagerDTO roleManagerDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (roleManagerDTO.getRoleType() - role.getRoleType() == 1){
            int count = informationDao.teacherInformationCount(new TeacherInformation(roleManagerDTO.getLoginName()));
            Role roles = new Role();
            BeanUtils.copyProperties(roleManagerDTO, roles);
            PageHelper.startPage(roleManagerDTO.getPage(), roleManagerDTO.getLimit());
            List<TeacherInformation> teacherInformationList = informationDao.informationList(roles);
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
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    @Transactional
    public CheckVO updateTeacherInformation(TeacherInformationDTO teacherInformationDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        TeacherInformation tI = informationDao.findByInformation(new TeacherInformation(teacherInformationDTO.getLoginName()));
        if (FormatConversionUtil.roleTypeFormatUitl(teacherInformationDTO.getRoleType()) - role.getRoleType() == 1 || teacherInformationDTO.getLoginName().equals(role.getLoginName())){
            TeacherInformation teacherInformation = new TeacherInformation();
            BeanUtils.copyProperties(teacherInformationDTO, teacherInformation);
            teacherInformation.setDepartment(FormatConversionUtil.ListFormatString(teacherInformationDTO.getDepartment()));
            teacherInformation.setClasses(FormatConversionUtil.ListFormatString(teacherInformationDTO.getClasses()));
            if (informationDao.findInformationNumber(teacherInformation.getNumber()) >= 1){
                throw new SimsException(ResultEnum.NUMBER_EXIST);
            }
            if (teacherInformation.getClasses() == ""){
                teacherInformation.setClasses(ParameterEnum.NO_CLASSES.getValue());
            }
            int row = informationDao.updateTeacherInformation(teacherInformation);
            if (row == 1){
                List<RecordDTO> recordDTOList = CompareDataUtil.CompareTeacherInformationData(tI, teacherInformation);
                for (RecordDTO recordDTO : recordDTOList){
                    recordDTO.setLoginName(role.getLoginName());
                    recordDTO.setTableName(TableEnum.TEACHER_INFORMATION.getValue());
                    recordDTO.setKeyId(tI.getId());
                    systemService.addRecord(recordDTO);
                }
                return new CheckVO(ResultEnum.SUCCESS);
            }else{
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        }else {
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    @Transactional
    public CheckVO classManage(ClassManagerDTO classManagerDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        int departmentCount = informationDao.findTeacherDepartment(new TeacherInformation(role.getLoginName(), "学生处"));
        int classCount = informationDao.findClassByName(classManagerDTO.getName());
        if (classCount == 1){
            return new CheckVO(ResultEnum.CLASS_EXIST);
        }
        if (role.getRoleType() == 0 || departmentCount == 1){
            int row;
            if (classManagerDTO.getId() == null){
                row = informationDao.createClass(new Classes(classManagerDTO.getName(), role.getId()));
            }else{
                String classDate = informationDao.findClassById(classManagerDTO.getId());
                try {
                    String oldClasses = informationDao.findClassById(classManagerDTO.getId());
                    List<TeacherInformation> teacherInformationList = informationDao.findTeacherInformationByClasses(oldClasses);
                    for (TeacherInformation teacherInformation : teacherInformationList){
                        Integer id = teacherInformation.getId();
                        String classes = teacherInformation.getClasses();
                        String newClasses = FormatConversionUtil.teacherClass(classes, oldClasses, classManagerDTO.getName());
                        String oldClass = informationDao.findByInformation(new TeacherInformation(id)).getClasses();
                        int upRow = informationDao.updateTeacherClasses(new TeacherInformation(id, newClasses));
                        systemService.addRecord(new RecordDTO(role.getLoginName(), TableEnum.TEACHER_INFORMATION.getValue(),
                                id, ColumnEnum.CLASSES.getValue(), newClasses, oldClass));
                        if (upRow != 1){
                            throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
                        }
                        /*TODO*//*还需要修改学生班级*/
                    }
                    Classes classes = new Classes();
                    BeanUtils.copyProperties(classManagerDTO, classes);
                    row = informationDao.updateClass(classes);
                    if (row == 1){
                        systemService.addRecord(new RecordDTO(role.getLoginName(), TableEnum.CLASSES.getValue(),
                                classManagerDTO.getId(), ColumnEnum.NAME.getValue(), classManagerDTO.getName(), classDate));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    throw new SimsException(ExceptionEnum.SYSTEM_ERROR);
                }
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
    public CheckVO deleteClass(String name) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        int departmentCount = informationDao.findTeacherDepartment(new TeacherInformation(role.getLoginName(), "学生处"));
        int stuCount = informationDao.findClassStuCount(name);
        List<Map<String, String>> teacherList = informationDao.findClassTeacher(name);
        if (stuCount != 0 || teacherList.size() != 0){
            return new CheckVO(ResultEnum.CLASS_EXIST_PERSON);
        }
        if (role.getRoleType() == 0 || departmentCount == 1){
            int id = informationDao.findClassIdByName(name);
            int row = informationDao.deleteClass(name);
            if (row == 1){
                systemService.addRecord(new RecordDTO(role.getLoginName(), TableEnum.CLASSES.getValue(), id, name));
                return new CheckVO(ResultEnum.SUCCESS);
            }
            throw  new SimsException(ExceptionEnum.DATA_BASE_ERROR);
        }else {
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    public PageVO<ClassVO> classPage(ClassManagerDTO classManagerDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        int departmentCount = informationDao.findTeacherDepartment(new TeacherInformation(role.getLoginName(), "学生处"));
        if (role.getRoleType() == 0 || departmentCount == 1){
            int count = informationDao.findClassCount(classManagerDTO.getName());
            Classes classes = new Classes();
            BeanUtils.copyProperties(classManagerDTO, classes);
            PageHelper.startPage(classManagerDTO.getPage(), classManagerDTO.getLimit());
            List<Classes> classesList = informationDao.findClasses(classes);
            List<ClassVO> classVOList = new ArrayList<>();
            for (int i = 0; i < classesList.size(); i++){
                ClassVO classVO = new ClassVO();
                BeanUtils.copyProperties(classesList.get(i), classVO);
                classVOList.add(classVO);
                List<Map<String, String>> teacherList = informationDao.findClassTeacher(classesList.get(i).getName());
                int stuCount = informationDao.findClassStuCount(classesList.get(i).getName());
                classVOList.get(i).setNumber(stuCount);
                TeacherVO teacherVO = Map2TeacherVO.map2TeacherVO(teacherList);
                classVOList.get(i).setTeacherVO(teacherVO);
                String createName = roleDao.findRoleById(classesList.get(i).getCreateId()).getLoginName();
                classVOList.get(i).setCreateName(createName);
                classVOList.get(i).setCreateTime(FormatConversionUtil.DateFormatUtil(classesList.get(i).getCreateTime()));
            }
            return new PageVO<ClassVO>(ResultEnum.SUCCESS, count,classVOList);
        }
        if (role.getRoleType() == 1 && departmentCount == 0){
            PageHelper.startPage(classManagerDTO.getPage(), classManagerDTO.getLimit());
            List<Classes> classesList = informationDao.findTeaClassCount(role.getLoginName(), classManagerDTO.getName());
            int count = classesList.size();
            List<ClassVO> classVOList = new ArrayList<>();
            for (int i = 0; i < classesList.size(); i++){
                ClassVO classVO = new ClassVO();
                BeanUtils.copyProperties(classesList.get(i), classVO);
                classVOList.add(classVO);
                List<Map<String, String>> teacherList = informationDao.findClassTeacher(classesList.get(i).getName());
                int stuCount = informationDao.findClassStuCount(classesList.get(i).getName());
                classVOList.get(i).setNumber(stuCount);
                TeacherVO teacherVO = Map2TeacherVO.map2TeacherVO(teacherList);
                classVOList.get(i).setTeacherVO(teacherVO);
                String createName = roleDao.findRoleById(classesList.get(i).getCreateId()).getLoginName();
                classVOList.get(i).setCreateName(createName);
                classVOList.get(i).setCreateTime(FormatConversionUtil.DateFormatUtil(classesList.get(i).getCreateTime()));
            }
            return new PageVO<ClassVO>(ResultEnum.SUCCESS, count,classVOList);
        }
        throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);

    }

    @Override
    public List<TeacherSubjectNameVO> teacherSubNameList(RoleManagerDTO roleManagerDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleManagerDTO, role);
        List<TeacherInformation> teacherInformationList = informationDao.informationList(role);
        List<TeacherSubjectNameVO> teacherSubjectNameVOList = new ArrayList<>();
        teacherInformationList.forEach(teacherInformation -> teacherSubjectNameVOList.add(new TeacherSubjectNameVO(teacherInformation.getId(), teacherInformation.getName(), teacherInformation.getSubject())));
        return teacherSubjectNameVOList;
    }

    @Override
    @Transactional
    public CheckVO updateTeacherClass(UpdateTeacherClassDTO updateTeacherClassDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        int departmentCount = informationDao.findTeacherDepartment(new TeacherInformation(role.getLoginName(), "学生处"));
        if (role.getRoleType() == 0 || departmentCount == 1) {
            if (updateTeacherClassDTO.getCurrentId() != null){
                String currentClasses = informationDao.findByInformation(new TeacherInformation(updateTeacherClassDTO.getCurrentId())).getClasses();
                String recordClasses = currentClasses;
                currentClasses = FormatConversionUtil.cutTeacherClass(currentClasses, updateTeacherClassDTO.getClasses());
                int currentRow = informationDao.updateTeacherClasses(new TeacherInformation(updateTeacherClassDTO.getCurrentId(), currentClasses));
                if (currentRow != 1){
                    new SimsException(ExceptionEnum.DATA_BASE_ERROR);
                }
                systemService.addRecord(new RecordDTO(role.getLoginName(), TableEnum.TEACHER_INFORMATION.getValue(), updateTeacherClassDTO.getCurrentId(),
                        ColumnEnum.CLASSES.getValue(), currentClasses, recordClasses));
            }
            String classes = informationDao.findByInformation(new TeacherInformation(updateTeacherClassDTO.getId())).getClasses();
            String classes1 = classes;
            classes += "," + updateTeacherClassDTO.getClasses();
            int row = informationDao.updateTeacherClasses(new TeacherInformation(updateTeacherClassDTO.getId(), classes));
            if (row == 1){
                systemService.addRecord(new RecordDTO(role.getLoginName(), TableEnum.TEACHER_INFORMATION.getValue(), updateTeacherClassDTO.getId(),
                        ColumnEnum.CLASSES.getValue(), classes, classes1));
                return new CheckVO(ResultEnum.SUCCESS);
            }
            throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
        }else {
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    @Transactional
    public CheckVO createStudentInformation(StudentInformationDTO studentInformationDTO) {
        RoleCheckVO roleCheckVO = roleService.createRole(new RoleDTO(
                studentInformationDTO.getLoginName(),
                studentInformationDTO.getLoginPassword(),
                studentInformationDTO.getRoleType()));
        if (roleCheckVO.getStatus() != 0){
            return new CheckVO(roleCheckVO.getStatus(), roleCheckVO.getMessage());
        }
        if (informationDao.findInformationLoginName(studentInformationDTO.getLoginName()) >= 1){
            throw new SimsException(ResultEnum.INFORMATION_EXIST);
        }
        if (informationDao.findStudentNumber(studentInformationDTO.getNumber()) >= 1){
            throw new SimsException(ResultEnum.STUDENT_NUMBER_EXIST);
        }
        StudentInformation studentInformation = new StudentInformation();
        BeanUtils.copyProperties(studentInformationDTO, studentInformation);
            if (informationDao.createStudentInformation(studentInformation) == 1){
            return new CheckVO(ResultEnum.SUCCESS);
        }else {
            throw new SimsException(ExceptionEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public PageVO<StudentInformationVO> studentInformationPage(RoleManagerDTO roleManagerDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (roleManagerDTO.getRoleType() - role.getRoleType() == 1){
            int count = informationDao.studentInformationCount(new StudentInformation(roleManagerDTO.getLoginName()));
            Role roles = new Role();
            BeanUtils.copyProperties(roleManagerDTO, roles);
            PageHelper.startPage(roleManagerDTO.getPage(), roleManagerDTO.getLimit());
            List<StudentInformation> studentInformationList = informationDao.studentList(roles);
            List<StudentInformationVO> studentInformationVOList = new ArrayList<StudentInformationVO>();
            for (StudentInformation studentInformation: studentInformationList){
                StudentInformationVO studentInformationVO = new StudentInformationVO();
                BeanUtils.copyProperties(studentInformation, studentInformationVO);
                studentInformationVO.setCreateTime(FormatConversionUtil.DateFormatUtil(studentInformation.getCreateTime()));
                studentInformationVOList.add(studentInformationVO);
            }
            PageVO<StudentInformationVO> pageVO = new PageVO(ResultEnum.SUCCESS, count,studentInformationVOList);
            return pageVO;
        }else {
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    @Transactional
    public CheckVO updateStudentInformation(StudentInformationDTO studentInformationDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        StudentInformation si = informationDao.findStudentInformation(new StudentInformation(studentInformationDTO.getLoginName()));
        if (FormatConversionUtil.roleTypeFormatUitl(studentInformationDTO.getRoleType()) - role.getRoleType() == 1){
            StudentInformation studentInformation = new StudentInformation();
            BeanUtils.copyProperties(studentInformationDTO, studentInformation);
            if (informationDao.findInformationNumber(studentInformation.getNumber()) >= 1){
                throw new SimsException(ResultEnum.STUDENT_NUMBER_EXIST);
            }
            if (studentInformation.getClasses() == ""){
                studentInformation.setClasses(ParameterEnum.NO_CLASSES.getValue());
            }
            int row = informationDao.updateStudentInformation(studentInformation);
            if (row == 1){
                List<RecordDTO> recordDTOList = CompareDataUtil.CompareStudentInformationData(si, studentInformation);
                for (RecordDTO recordDTO : recordDTOList){
                    recordDTO.setLoginName(role.getLoginName());
                    recordDTO.setTableName(TableEnum.STUDENT_INFORMATION.getValue());
                    recordDTO.setKeyId(si.getId());
                    systemService.addRecord(recordDTO);
                }
                return new CheckVO(ResultEnum.SUCCESS);
            }else{
                throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
            }
        }else {
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }

    @Override
    public TeacherInformationVO MyInformationT() {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        TeacherInformation teacherInformation = informationDao.findByInformation(new TeacherInformation(role.getLoginName()));
        if (teacherInformation == null) {
            throw new SimsException(ExceptionEnum.DATA_BASE_ERROR);
        }
        TeacherInformationVO teacherInformationVO = new TeacherInformationVO();
        BeanUtils.copyProperties(teacherInformation, teacherInformationVO);
        teacherInformationVO.setCreateTime(FormatConversionUtil.DateFormatUtil(teacherInformation.getCreateTime()));
        return teacherInformationVO;
    }

    @Override
    public PageVO<StudentInformationVO> MyStudent(RoleManagerDTO roleManagerDTO) {
        Role role = SessionUtil.LoginNameCheckSession(request, roleDao);
        if (role.getRoleType() == 1) {
            int count = informationDao.findTeacherOfStudent(role.getLoginName(), roleManagerDTO.getLoginName()).size();
            PageHelper.startPage(roleManagerDTO.getPage(), roleManagerDTO.getLimit());
            List<StudentInformation> studentInformationList = informationDao.findTeacherOfStudent(role.getLoginName(), roleManagerDTO.getLoginName());
            List<StudentInformationVO> studentInformationVOList = new ArrayList<StudentInformationVO>();
            for (StudentInformation studentInformation: studentInformationList){
                StudentInformationVO studentInformationVO = new StudentInformationVO();
                BeanUtils.copyProperties(studentInformation, studentInformationVO);
                studentInformationVO.setCreateTime(FormatConversionUtil.DateFormatUtil(studentInformation.getCreateTime()));
                studentInformationVOList.add(studentInformationVO);
            }
            PageVO<StudentInformationVO> pageVO = new PageVO(ResultEnum.SUCCESS, count,studentInformationVOList);
            return pageVO;
        }else {
            throw new SimsException(ExceptionEnum.UNAUTHORIZED_OPERATION);
        }
    }
}
