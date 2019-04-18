package com.sqlist.web.exception;

import com.sqlist.web.result.CodeMsg;
import com.sqlist.web.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author SqList
 * @date 2019/4/9 9:44
 * @descriptio 全局异常处理器 相当于Controller
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return Result.fail(globalException.getCodeMsg());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.fail(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            return Result.fail(CodeMsg.SERVER_ERROR);
        }
    }
}
