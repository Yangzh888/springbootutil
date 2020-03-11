package com.cn.springbootutil.common.result;

/**
 * @description
 * @author: YZH
 * @create: 2020-02-28 10:48
 **/

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;

/**
 * @author Administrator
 */
@ApiModel(value = "返回结果",description = "返回结果类，包含code(状态码)和msg(操作信息)两个key")
public class ResultData<T> {
    private static final long serialVersionUID = 1L;



    public static  final  Integer SUCCESS_CODE=20000;
    public static  final  String SUCCESS_MSG="操作成功";



    public static  final  Integer FAIL_CODE=20001;


    public static  final  String FAIL_MSG="操作失败";


    @ApiModelProperty(value = "状态码")
    private   int code;

    @ApiModelProperty(value = "返回消息")
    private   String msg;
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据")
    private  T  data;
    /**
     * 返回操作失败
     * @return
     */
    public static ResultData error() {
        return customResult(FAIL_CODE, FAIL_MSG);
    }
    /**
     * 操作成功
     * @return
     */
    public static ResultData ok() {
        return customResult(SUCCESS_CODE, SUCCESS_MSG);
    }

    public static ResultData error(String msg) {

        return customResult(FAIL_CODE, msg);
    }
    /**
     * 自定义状态码和消息
     * @param code
     * @param msg
     * @return
     */
    public static ResultData customResult(int code, String msg) {
        ResultData r = new ResultData();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    /**
     * 操作成功加入 返回数据
     * @param data
     * @return
     */
    public  ResultData ok(T data) {
        ResultData r = customResult(SUCCESS_CODE, SUCCESS_MSG);
        r.setData(data);
        return r;
    }




    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
