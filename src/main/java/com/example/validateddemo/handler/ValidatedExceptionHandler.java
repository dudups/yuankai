package com.example.validateddemo.handler;

import com.example.validateddemo.base.Result;
import com.example.validateddemo.enums.ResultEnum;
import com.example.validateddemo.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author He Changjie on 2020/9/5
 */
@Slf4j
@ControllerAdvice
public class ValidatedExceptionHandler {

    /**
     * 处理@Validated参数校验失败异常
     * @param exception 异常类
     * @return 响应
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result exceptionHandler(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null) {
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    log.warn("Bad Request Parameters: dto entity [{}],field [{}],message [{}]",fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                    stringBuilder.append(fieldError.getDefaultMessage());
                });
            }
        }
        return ResultUtil.validatedException(stringBuilder.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleValidationException(ConstraintViolationException e){
        for(ConstraintViolation<?> s:e.getConstraintViolations()){
            return ResultUtil.validatedException(s.getInvalidValue()+": "+s.getMessage());
        }

        return ResultUtil.validatedException("请求不合法");
    }
}
