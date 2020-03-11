package com.cn.springbootutil.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description
 * 配置redis序列化器
 * StringRedisTemplate默认选择的StringRedisSerializer序列化器
 * 如我们把Value的类型改为Object呢
 * 所生成的json串会导致乱码
 * 这是因为没有设置序列化器，RedisTemplate选择了默认的序列化器JdkSerializationRedisSerializer -> RedisTemplate<String,String>
 * 更多的时候采用这种方法呢，实际情况中可能有多种需求，泛型各不相同，比如有<String,User>
 * 直接在Config类里面在加一个方法就行。
 * @author: YZH
 * @create: 2020-02-28 10:29
 **/
@Configuration
public class RedisConfig{
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object>template=new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}
