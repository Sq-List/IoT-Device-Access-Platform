package com.sqlist.web.exception;

import com.sqlist.web.result.CodeMsg;
import com.sqlist.web.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author SqList
 * @date 2019/4/9 9:44
 * @descriptio 全局异常处理器
 **/
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GlobalException.class)
    public Result handleGlobalException(GlobalException e) {
        log.error("raised GlobalException: {}", e.getCodeMsg().toString());
        return Result.fail(e.getCodeMsg(), e.getData());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public Result validationError(Exception ex) {
        BindingResult result = null;
        if (ex instanceof MethodArgumentNotValidException) {
            result = ((MethodArgumentNotValidException)ex).getBindingResult();
            log.error("raised MethodArgumentNotValidException: {}", ex);
        } else {
            result = (BindException)ex;
            log.error("raised BindException: {}", ex);
        }

        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();

        for (FieldError error : fieldErrors) {
            builder.append(error.getField() + " " + error.getDefaultMessage() + ", ");
        }
        return Result.fail(CodeMsg.BIND_ERROR.fillArgs(builder));
    }

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.fail(CodeMsg.SERVER_ERROR);
    }
}
