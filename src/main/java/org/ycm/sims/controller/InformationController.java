package org.ycm.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ycm.sims.VO.*;
import org.ycm.sims.dto.*;
import org.ycm.sims.entity.TeacherInformation;
import org.ycm.sims.enums.ParameterEnum;
import org.ycm.sims.service.InformationService;
import org.ycm.sims.utils.ControllerJumpUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/4/22 1:14
 */
@Controller
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private InformationService informationService;

    @RequestMapping("/classes")
    @ResponseBody
    public NumberAndClassesVO classes(){
        return informationService.createInformationVO();
    }

    @RequestMapping("/create")
    @ResponseBody
    public CheckVO create(@RequestBody TeacherInformationDTO teacherInformationDTO){
        return informationService.createInformation(teacherInformationDTO);
    }

    @RequestMapping("/createStudent")
    @ResponseBody
    public CheckVO createStudent(@RequestBody StudentInformationDTO studentInformationDTO){
        return informationService.createStudentInformation(studentInformationDTO);
    }

    @RequestMapping("/page")
    public String teacherList(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "admin/information", "role/information");
    }

    @RequestMapping("/teacherPage")
    @ResponseBody
    public PageVO<TeacherInformationVO> teacherPage(RoleManagerDTO roleManagerDTO){
        return informationService.teacherInformationPage(roleManagerDTO);
    }

    @RequestMapping("/updateTeacher")
    @ResponseBody
    public CheckVO updateTeacher(@RequestBody TeacherInformationDTO teacherInformationDTO){
        return informationService.updateTeacherInformation(teacherInformationDTO);
    }

    @RequestMapping("/classInformation")
    public String classInformation(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "admin/class_manager", "role/class_manager");
    }

    @RequestMapping("/classPage")
    @ResponseBody
    public PageVO<ClassVO> classPage(ClassManagerDTO classManagerDTO){
        return informationService.classPage(classManagerDTO);
    }

    @RequestMapping("/myClassPage")
    @ResponseBody
    public PageVO<ClassVO> myClassPage(ClassManagerDTO classManagerDTO){
        return informationService.myClassPage(classManagerDTO);
    }

    @RequestMapping("/classManager")
    @ResponseBody
    public CheckVO createClass(ClassManagerDTO classManagerDTO){
        return informationService.classManage(classManagerDTO);
    }

    @RequestMapping("/deleteClass")
    @ResponseBody
    public CheckVO deleteClass(String name){
        return informationService.deleteClass(name);
    }

    @RequestMapping("/teacherSubjectName")
    @ResponseBody
    public List<TeacherSubjectNameVO> teacherSubjectName(Integer roleType){
        return informationService.teacherSubNameList(new RoleManagerDTO(roleType));
    }

    @RequestMapping("/updateTeacherClass")
    @ResponseBody
    public CheckVO updateTeacherClass(UpdateTeacherClassDTO updateTeacherClassDTO){
        return informationService.updateTeacherClass(updateTeacherClassDTO);
    }

//    @RequestMapping("/studentPage")
//    @ResponseBody
//    public PageVO<StudentInformationVO> studentPage(RoleManagerDTO roleManagerDTO){
//        return informationService.studentInformationPage(roleManagerDTO);
//    }

    @RequestMapping("/updateStudent")
    @ResponseBody
    public CheckVO updateStudent(@RequestBody StudentInformationDTO studentInformationDTO){
        return informationService.updateStudentInformation(studentInformationDTO);
    }

    @RequestMapping("/tClassManager")
    public String tClassManager(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "", "role/t_class_manager");
    }

    @RequestMapping("/myInformationHtmlT")
    public String myInformationHtml(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "", "role/my_information_t");
    }

    @RequestMapping("/myInformationVOT")
    @ResponseBody
    public TeacherInformationVO myInformationVOT(){
        return informationService.myInformationT();
    }

    @RequestMapping("/myStudentHtml")
    public String myStudentHtml(){
        return ControllerJumpUtil.ControllerJumpUtil(request, "", "role/my_student");
    }

    @RequestMapping("/myStudent")
    @ResponseBody
    public PageVO<StudentInformationVO> myStudent(StudentInformationPageDTO studentInformationPageDTO){
        return informationService.myStudent(studentInformationPageDTO);
    }

    @RequestMapping("/classesStudent")
    @ResponseBody
    public PageVO<StudentInformationVO> classesStudent(StudentInformationPageDTO studentInformationPageDTO){
        return informationService.classesStudent(studentInformationPageDTO);
    }

    @RequestMapping("/teacherClasses")
    @ResponseBody
    public NumberAndClassesVO teacherClasses(){
        return informationService.teacherClasses();
    }
}
