package com.cn.springbootutil.common.util;

import org.springframework.util.StringUtils;

/**
 * @description
 * @author: YZH 统一工具类
 * @create: 2020-02-27 14:52
 **/
public class StringKit {
    public static Boolean isEmpty(String str){
        return  StringUtils.isEmpty(str);
    }
    public static Boolean isNotEmpty(String str){
        return  !StringUtils.isEmpty(str);
    }
}
