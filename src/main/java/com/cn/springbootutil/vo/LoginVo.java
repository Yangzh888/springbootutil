package com.cn.springbootutil.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description
 * @author: YZH 登录返回vo
 * @create: 2020-03-16 16:14
 **/
@Data
public class LoginVo implements Serializable {
    private String token;
}
