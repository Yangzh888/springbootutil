package com.cn.springbootutil.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.springbootutil.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YZH
 * @since 2020-02-27
 */
public interface IUserService extends IService<User> {

    /**
     * 根据ID获取数据
     * @param id
     * @return
     */
    User findById(Integer id);
    /**
     * 根据ID删除数据
     * @param id
     * @return
     */
    List deleteById(Integer id);

    /**
     * 测试分页参数
     * @param page
     * @param i
     * @return
     */
    IPage<User> selectPageVo(Page<User> page, int i);
}
