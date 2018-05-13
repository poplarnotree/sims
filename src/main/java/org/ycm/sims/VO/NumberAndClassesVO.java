package org.ycm.sims.VO;

import lombok.Data;

import java.util.List;

/**
 * 最大工号和班级VO
 * Create by yangchangmin
 * on 2018/4/22 21:35
 */
@Data
public class NumberAndClassesVO {

    /*班级名称*/
    private List<String> classesName;

    /*最大工号*/
    private String maxNumber;

    public NumberAndClassesVO() {
    }

    public NumberAndClassesVO(List<String> classesName) {
        this.classesName = classesName;
    }

    public NumberAndClassesVO(List<String> classesName, String maxNumber) {
        this.classesName = classesName;
        this.maxNumber = maxNumber;
    }
}
