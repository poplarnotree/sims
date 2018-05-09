package org.ycm.sims.dto;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/5/7 21:53
 */
@Data
public class MenuDTO {

    private Integer id;

    private String name;

    private String modular;

//    学生处教师可见
    private Integer teacherDisplay1;

//    教研组教师可见
    private Integer teacherDisplay2;

    private Integer studentDisplay;

    private String url;

    public MenuDTO() {
    }

    public MenuDTO(Integer id, String name, String modular, Integer teacherDisplay1, Integer teacherDisplay2, Integer studentDisplay, String url) {
        this.id = id;
        this.name = name;
        this.modular = modular;
        this.teacherDisplay1 = teacherDisplay1;
        this.teacherDisplay2 = teacherDisplay2;
        this.studentDisplay = studentDisplay;
        this.url = url;
    }

    public MenuDTO(String name, String modular, Integer teacherDisplay1, Integer teacherDisplay2, Integer studentDisplay, String url) {
        this.name = name;
        this.modular = modular;
        this.teacherDisplay1 = teacherDisplay1;
        this.teacherDisplay2 = teacherDisplay2;
        this.studentDisplay = studentDisplay;
        this.url = url;
    }
}
