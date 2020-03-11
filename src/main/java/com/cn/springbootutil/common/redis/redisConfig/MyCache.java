package com.cn.springbootutil.common.redis.redisConfig;

/**
 * @description
 * @author: YZH 封装redis缓存实现自定义超时时间，对数据一致性要求不高的时候使用
 *
 * @create: 2020-01-14 15:25
 **/

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})

@Retention(RetentionPolicy.RUNTIME)

@Documented

public @interface MyCache {

    String key() default "";
    //返回类型
    Class type();
    //默认缓存时间是一天
    long expire() default 60 * 60 * 24L;

}
