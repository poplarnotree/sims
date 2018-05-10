package org.ycm.sims.utils;

import org.ycm.sims.dto.MenuDTO;
import org.ycm.sims.dto.RecordDTO;
import org.ycm.sims.entity.Menu;
import org.ycm.sims.entity.StudentInformation;
import org.ycm.sims.entity.TeacherInformation;
import org.ycm.sims.enums.ColumnEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by yangchangmin
 * on 2018/5/6 23:04
 */
public class CompareDataUtil {

    /**
     * 比较两个TeacherInformation对象哪些字段不同
     * @param teacherInformationData 数据库中原始的TeacherInformation
     * @param teacherInformation 修改的TeacherInformation
     * @return
     */
    public static List<RecordDTO> CompareTeacherInformationData(TeacherInformation teacherInformationData, TeacherInformation teacherInformation){
        List<RecordDTO> recordDTOList = new ArrayList<>();
        if (!teacherInformationData.getLoginName().equals(teacherInformation.getLoginName())){
            recordDTOList.add(new RecordDTO(ColumnEnum.LOGIN_NAME.getValue(), teacherInformation.getLoginName(), teacherInformationData.getLoginName()));
        }
        if (!teacherInformationData.getNumber().equals(teacherInformation.getNumber())){
            recordDTOList.add(new RecordDTO(ColumnEnum.NUMBER.getValue(), teacherInformation.getNumber(), teacherInformationData.getNumber()));
        }
        if (!teacherInformationData.getName().equals(teacherInformation.getName())){
            recordDTOList.add(new RecordDTO(ColumnEnum.NAME.getValue(), teacherInformation.getName(), teacherInformationData.getName()));
        }
        if (!teacherInformationData.getSex().equals(teacherInformation.getSex())){
            recordDTOList.add(new RecordDTO(ColumnEnum.SEX.getValue(), teacherInformation.getSex(), teacherInformationData.getSex()));
        }
        if (!teacherInformationData.getIdCart().equals(teacherInformation.getIdCart())){
            recordDTOList.add(new RecordDTO(ColumnEnum.ID_CART.getValue(), teacherInformation.getIdCart(), teacherInformationData.getIdCart()));
        }
        if (!teacherInformationData.getPositionalTitles().equals(teacherInformation.getPositionalTitles())){
            recordDTOList.add(new RecordDTO(ColumnEnum.POSITIONAL_TITLES.getValue(), teacherInformation.getPositionalTitles(), teacherInformationData.getPositionalTitles()));
        }
        if (!teacherInformationData.getNation().equals(teacherInformation.getNation())){
            recordDTOList.add(new RecordDTO(ColumnEnum.NATION.getValue(), teacherInformation.getNation(), teacherInformationData.getNation()));
        }
        if (!teacherInformationData.getPlace().equals(teacherInformation.getPlace())){
            recordDTOList.add(new RecordDTO(ColumnEnum.PLACE.getValue(), teacherInformation.getPlace(), teacherInformationData.getPlace()));
        }
        if (!teacherInformationData.getAddress().equals(teacherInformation.getAddress())){
            recordDTOList.add(new RecordDTO(ColumnEnum.ADDRESS.getValue(), teacherInformation.getAddress(), teacherInformationData.getAddress()));
        }
        if (!teacherInformationData.getBirthday().equals(teacherInformation.getBirthday())){
            recordDTOList.add(new RecordDTO(ColumnEnum.BIRTHDAY.getValue(), teacherInformation.getBirthday(), teacherInformationData.getBirthday()));
        }
        if (!teacherInformationData.getPhone().equals(teacherInformation.getPhone())){
            recordDTOList.add(new RecordDTO(ColumnEnum.PHONE.getValue(), teacherInformation.getPhone(), teacherInformationData.getPhone()));
        }
        if (!teacherInformationData.getDepartment().equals(teacherInformation.getDepartment())){
            recordDTOList.add(new RecordDTO(ColumnEnum.DEPARTMENT.getValue(), teacherInformation.getDepartment(), teacherInformationData.getDepartment()));
        }
        if (!teacherInformationData.getClasses().equals(teacherInformation.getClasses())){
            recordDTOList.add(new RecordDTO(ColumnEnum.CLASSES.getValue(), teacherInformation.getClasses(), teacherInformationData.getClasses()));
        }
        if (!teacherInformationData.getSubject().equals(teacherInformation.getSubject())){
            recordDTOList.add(new RecordDTO(ColumnEnum.SUBJECT.getValue(), teacherInformation.getSubject(), teacherInformationData.getSubject()));
        }
        return recordDTOList;
    }

    /**
     * 比较两个StudentInformation对象哪些字段不同
     * @param studentInformationData 数据库中原始的StudentInformation
     * @param studentInformation 修改的StudentInformation
     * @return
     */
    public static List<RecordDTO> CompareStudentInformationData(StudentInformation studentInformationData, StudentInformation studentInformation){
        List<RecordDTO> recordDTOList = new ArrayList<>();
        if (!studentInformationData.getLoginName().equals(studentInformation.getLoginName())){
            recordDTOList.add(new RecordDTO(ColumnEnum.LOGIN_NAME.getValue(), studentInformation.getLoginName(), studentInformationData.getLoginName()));
        }
        if (!studentInformationData.getNumber().equals(studentInformation.getNumber())){
            recordDTOList.add(new RecordDTO(ColumnEnum.NUMBER.getValue(), studentInformation.getNumber(), studentInformationData.getNumber()));
        }
        if (!studentInformationData.getName().equals(studentInformation.getName())){
            recordDTOList.add(new RecordDTO(ColumnEnum.NAME.getValue(), studentInformation.getName(), studentInformationData.getName()));
        }
        if (!studentInformationData.getSex().equals(studentInformation.getSex())){
            recordDTOList.add(new RecordDTO(ColumnEnum.SEX.getValue(), studentInformation.getSex(), studentInformationData.getSex()));
        }
        if (!studentInformationData.getIdCart().equals(studentInformation.getIdCart())){
            recordDTOList.add(new RecordDTO(ColumnEnum.ID_CART.getValue(), studentInformation.getIdCart(), studentInformationData.getIdCart()));
        }
        if (!studentInformationData.getNation().equals(studentInformation.getNation())){
            recordDTOList.add(new RecordDTO(ColumnEnum.NATION.getValue(), studentInformation.getNation(), studentInformationData.getNation()));
        }
        if (!studentInformationData.getPlace().equals(studentInformation.getPlace())){
            recordDTOList.add(new RecordDTO(ColumnEnum.PLACE.getValue(), studentInformation.getPlace(), studentInformationData.getPlace()));
        }
        if (!studentInformationData.getAddress().equals(studentInformation.getAddress())){
            recordDTOList.add(new RecordDTO(ColumnEnum.ADDRESS.getValue(), studentInformation.getAddress(), studentInformationData.getAddress()));
        }
        if (!studentInformationData.getBirthday().equals(studentInformation.getBirthday())){
            recordDTOList.add(new RecordDTO(ColumnEnum.BIRTHDAY.getValue(), studentInformation.getBirthday(), studentInformationData.getBirthday()));
        }
        if (!studentInformationData.getPhone().equals(studentInformation.getPhone())){
            recordDTOList.add(new RecordDTO(ColumnEnum.PHONE.getValue(), studentInformation.getPhone(), studentInformationData.getPhone()));
        }
        if (!studentInformationData.getClasses().equals(studentInformation.getClasses())){
            recordDTOList.add(new RecordDTO(ColumnEnum.CLASSES.getValue(), studentInformation.getClasses(), studentInformationData.getClasses()));
        }
        if (!studentInformationData.getEnrolmentTime().equals(studentInformation.getEnrolmentTime())){
            recordDTOList.add(new RecordDTO(ColumnEnum.ENROLMENT_TIME.getValue(), studentInformation.getEnrolmentTime(), studentInformationData.getEnrolmentTime()));
        }
        return recordDTOList;
    }

    /**
     * 比较两个菜单的不同
     * @param menuData
     * @param menu
     * @return
     */
    public static List<RecordDTO> CompareMenuDataUtil(Menu menuData, Menu menu){
        List<RecordDTO> recordDTOList = new ArrayList<>();
        if (!menuData.getName().equals(menu.getName())){
            recordDTOList.add(new RecordDTO(ColumnEnum.NAME.getValue(), menu.getName(), menuData.getName()));
        }
        if (!menuData.getModular().equals(menu.getModular())){
            recordDTOList.add(new RecordDTO(ColumnEnum.MODULAR.getValue(), menu.getModular(), menuData.getModular()));
        }
        if (!menuData.getTeacherDisplay1().equals(menu.getTeacherDisplay1())){
            recordDTOList.add(new RecordDTO(ColumnEnum.TEACHER_DISPLAY.getValue(), menu.getTeacherDisplay1().toString(), menuData.getTeacherDisplay1().toString()));
        }
        if (!menuData.getTeacherDisplay2().equals(menu.getTeacherDisplay2())){
            recordDTOList.add(new RecordDTO(ColumnEnum.TEACHER_DISPLAY.getValue(), menu.getTeacherDisplay2().toString(), menuData.getTeacherDisplay2().toString()));
        }
        if (!menuData.getStudentDisplay().equals(menu.getStudentDisplay())){
            recordDTOList.add(new RecordDTO(ColumnEnum.STUDENT_DISPLAY.getValue(), menu.getStudentDisplay().toString(), menuData.getStudentDisplay().toString()));
        }
        if (!menuData.getUrl().equals(menu.getUrl())){
            recordDTOList.add(new RecordDTO(ColumnEnum.URL.getValue(), menu.getUrl(), menuData.getUrl()));
        }
        return recordDTOList;
    }

}
