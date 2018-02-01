package com.Exception;

import com.Utils.ResultUtils;
import com.domain.Results;
import com.enums.ResultEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Wudi
 * @Description: 自定义异常进行捕获
 * @date 21:31  2017/12/28
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(SellException.class)
    @ResponseBody
    public Results catchExceptionDeal(Exception e){

        if (e instanceof SellException){//异常是否含有SellException,如果含有就进行捕获

            return ResultUtils.Error(ResultEnum.PARAM_NOT_EXIST.getCode(),e.getMessage());

        }else {

            return ResultUtils.Error(ResultEnum.RESULT_ERROR.getCode(),e.getMessage());
        }


    }
}
