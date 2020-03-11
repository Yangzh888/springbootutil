package com.cn.springbootutil.common.util.bean;

import org.omg.CORBA.Environment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YZH on 2019/11/28
 * 对象工具类
 */
public class BeanUtil {

    /**
     * 将JavaBean对象封装到Map集合当中
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map<String, Object> bean2Map(Object bean) throws Exception
    {

        //创建Map集合对象
        Map<String,Object> map=new HashMap<String, Object>();
        //获取对象字节码信息,不要Object的属性
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(),Object.class);
        //获取bean对象中的所有属性
        PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : list) {
            //获取属性名
            String key = pd.getName();
            //调用getter()方法,获取内容
            Object value = pd.getReadMethod().invoke(bean);
            //增加到map集合当中
            map.put(key, value);
        }
        return map;
    }
    /**
     * 将Map集合中的数据封装到JavaBean对象中
     * @param map        集合
     * @param classType 封装javabean对象
     * @throws Exception
     */
    public static <T> T map2Bean(Map<String, Object> map,Class<T> classType) throws Exception
    {
        //采用反射动态创建对象
        T obj = classType.newInstance();
        //获取对象字节码信息,不要Object的属性
        BeanInfo beanInfo = Introspector.getBeanInfo(classType,Object.class);
        //获取bean对象中的所有属性
        PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : list) {
            //获取属性名
            String key = pd.getName();
            //获取属性值
            Object value=map.get(key);
            //调用属性setter()方法,设置到javabean对象当中
            pd.getWriteMethod().invoke(obj, value);
        }
        return obj;
    }

    /**
     * Bean类转换
     * @param s 资源
     * @param t 目标
     * @return
     */
    public static Object  beans2Bean(Object s,Object t){
        BeanUtils.copyProperties(s,t);
        return t;
    }
}
