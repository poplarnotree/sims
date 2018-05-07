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

    private Integer teacherDisplay;

    private Integer studentDisplay;

    private String url;

    public MenuDTO() {
    }

    public MenuDTO(String name, String modular, Integer teacherDisplay, Integer studentDisplay, String url) {
        this.name = name;
        this.modular = modular;
        this.teacherDisplay = teacherDisplay;
        this.studentDisplay = studentDisplay;
        this.url = url;
    }

    public MenuDTO(Integer id, String name, String modular, Integer teacherDisplay, Integer studentDisplay, String url) {
        this.id = id;
        this.name = name;
        this.modular = modular;
        this.teacherDisplay = teacherDisplay;
        this.studentDisplay = studentDisplay;
        this.url = url;
    }
}
