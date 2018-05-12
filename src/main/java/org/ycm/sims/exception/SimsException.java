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
    private Integer status;

    public SimsException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.status = exceptionEnum.getStatus();
    }

    public SimsException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.status = resultEnum.getCode();
    }
}
