package org.ycm.sims.VO;

import lombok.Data;

import java.util.Date;

/**
 * Create by yangchangmin
 * on 2018/5/8 0:56
 */
@Data
public class MenuVO {

    private Integer id;

    private String name;

    private String modular;

    private Integer teacherDisplay;

    private Integer studentDisplay;

    private String url;

    private String createName;

    private String createTime;
}
