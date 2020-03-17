package com.cn.springbootutil.common.exception;

/**
 * @description
 * @author: YZH 简易的自定义异常
 * @create: 2020-03-16 15:45
 **/
public class BusinessException extends RuntimeException {


    /**
     * 定义无参构造方法
     */
    public BusinessException() {
        super();
    }



    /**
     * 定义有参构造方法
     * @param message
     */
    public BusinessException(String message) {
        super(message);
    }
}
