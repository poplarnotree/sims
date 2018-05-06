package org.ycm.sims.utils;

import org.ycm.sims.dto.RecordDTO;
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

}
