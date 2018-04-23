package org.ycm.sims.exception;

import lombok.Data;
import org.ycm.sims.enums.ExceptionEnum;
import org.ycm.sims.enums.ResultEnum;

/**
 * Created by yangchangmin
 * on 2018/4/17 16:13
 */
@Data
public class SimsException extends RuntimeException {
    private Integer code;

    public SimsException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

    public SimsException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
