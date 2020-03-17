package com.cn.springbootutil.common.exception;

import com.cn.springbootutil.common.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description
 * @author: YZH
 * 统一异常处理类
 * @create: 2020-03-05 14:09
 **/
@RestControllerAdvice()
public class BaseExceptionController {

    /**
     *     //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validationErrorHandler(MethodArgumentNotValidException  e){
        List<String> stringList=e.getBindingResult().getAllErrors().stream().map(ObjectError::getObjectName).collect(Collectors.toList());
        String message = stringList.stream().collect(Collectors.joining(","));
        return Result.error(message);
    }

    /**
     *     处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result validationErrorHandlerOther(ConstraintViolationException  e){
        List<String> stringList=e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        String message = stringList.stream().collect(Collectors.joining(","));
        return Result.error(message);
    }
    @ExceptionHandler
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error("服务器错误");
    }
    @ExceptionHandler(BusinessException.class)
    public Result errorBusiness(BusinessException e){
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
    /**
     *     处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
     */
    @ExceptionHandler(BindException.class)
    public Result error(BindException e){
        List<String> stringList = e.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        String message = stringList.stream().collect(Collectors.joining(","));
        e.printStackTrace();
        return Result.error(message);
    }
}
