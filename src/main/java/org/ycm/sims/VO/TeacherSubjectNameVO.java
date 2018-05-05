package org.ycm.sims.VO;

import lombok.Data;

/**
 * Create by yangchangmin
 * on 2018/5/5 21:55
 */
@Data
public class TeacherSubjectNameVO {

    private Integer id;

    private String name;

    private String subject;

    public TeacherSubjectNameVO() {
    }

    public TeacherSubjectNameVO(Integer id, String name, String subject) {
        this.id = id;
        this.name = name;
        this.subject = subject;
    }
}
