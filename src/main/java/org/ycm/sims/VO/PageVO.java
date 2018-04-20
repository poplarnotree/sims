package org.ycm.sims.VO;

import lombok.Data;
import org.ycm.sims.enums.ResultEnum;

import java.util.List;

/**
 * 分页
 * Create by yangchangmin
 * on 2018/4/21 1:26
 */
@Data
public class PageVO<T> {

    /*提示码*/
    private Integer code;

    /*提示信息*/
    private String msg;

    /*总条数*/
    private Integer count;

    /*数据*/
    private List<T> data;

    public PageVO(ResultEnum resultEnum, Integer count, List<T> data) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.count = count;
        this.data = data;
    }
}
