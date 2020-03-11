package com.cn.springbootutil.common.aop;

/**
 * @description
 * @author: YZH 封装@Cach注解,实现注解时可直接设置超时时间
 * 发现缺陷，暂时不能更新和删除缓存，待处理
 * @create: 2020-01-14 15:26
 **/

import com.alibaba.fastjson.JSON;
import com.cn.springbootutil.common.redis.redisConfig.MyCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class AopCachHandle {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut(value = "@annotation(com.cn.springbootutil.common.redis.redisConfig.MyCache)")
    public void pointcut() {
    }

    @Around(value = "pointcut() && @annotation(cache)")
    public Object around(ProceedingJoinPoint point, MyCache cache) {

        Method method = getMethod(point);
        //根据类名、方法名和参数生成key
        final String key = parseKey(cache.key(), method, point.getArgs());
        String value = stringRedisTemplate.opsForValue().get(key);
        if (null != value) {
            return JSON.parseObject(value, cache.type());
        }
        try {
            Object proceed = point.proceed();
            stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(proceed), cache.expire(), TimeUnit.SECONDS);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     *     * 获取被拦截方法对象
     *     * MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
     *     * 而缓存的注解在实现类的方法上
     *     * 所以应该使用反射获取当前对象的方法对象
     *    
     */

    private Method getMethod(ProceedingJoinPoint pjp) {
        //获取参数的类型
        Class[] argTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    private String parseKey(String key, Method method, Object[] args) {
        StringBuilder result=new StringBuilder();
        if (StringUtils.isEmpty(key)) {
            result.append(method.getName());
        }else {
            result.append(key);
        }
        //获得被拦截方法参数列表
        LocalVariableTableParameterNameDiscoverer nd = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = nd.getParameterNames(method);
        for (int i = 0; i < parameterNames.length; i++) {
          //  key = key.replace(parameterNames[i] + "", args[i] + "");
            result.append("(");
            result.append(parameterNames[i] + ":"+" ");
            result.append(args[i]);
            result.append(")");
        }
        return result.toString();
    }

}

