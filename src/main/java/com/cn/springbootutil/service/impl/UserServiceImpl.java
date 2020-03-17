package com.cn.springbootutil.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.springbootutil.common.config.ApplicationConfig;
import com.cn.springbootutil.common.redis.redisConfig.MyCache;
import com.cn.springbootutil.common.result.Result;
import com.cn.springbootutil.common.token.JwtToken;
import com.cn.springbootutil.common.util.bean.BeanUtil;
import com.cn.springbootutil.entity.User;
import com.cn.springbootutil.dao.UserMapper;
import com.cn.springbootutil.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author YZH
 * @since 2020-02-27
 */
@Service("UserServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 都是获取配置表的数据
     * private Environment environment
     * applicationConfig和这个异曲同工
     */
    @Autowired
    private ApplicationConfig applicationConfig;
    @Resource
    private UserMapper userMapper;

    /**
     * 根据ID获取数据
     * MyCachek可以设置自定义超时时间，对数据的更新不敏感的可以使用，多用于首页图表等数据
     *
     * @return
     */
    @Override
    public User findById(Integer id) {
        //测试获取配置文件中的上传文件地址
        String uploadPath = applicationConfig.getUploadPath();
        System.out.println(uploadPath);
        return userMapper.selectById(id);
    }

    /**
     * allEntries移除所有key为findById 不考虑参数
     *
     * @param id
     */
    @CacheEvict(value = "findById", allEntries = true)
    @Override
    public List deleteById(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id");
        List<User> list = userMapper.selectList(queryWrapper);
        User user = userMapper.getUser();
        list.add(user);
        return list;
    }

    @Override
    public IPage<User> selectPageVo(Page<User> page, int i) {
        return userMapper.selectPageVo(page, i);
    }

    @Override
    public Result login(Integer userId, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id");
        queryWrapper.eq("user_Id", userId);
        queryWrapper.eq("password", password);
        User user = userMapper.selectOne(queryWrapper);
        if (BeanUtil.isNotEmpty(user)) {
            try {
               return Result.ok(JwtToken.createToken(userId.toString(), password)) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Result.error("账号或者密码错误");
    }

}

