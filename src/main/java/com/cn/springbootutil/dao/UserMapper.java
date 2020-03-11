package com.cn.springbootutil.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.springbootutil.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YZH
 * @since 2020-02-27
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * ss
     * @return
     */
    User getUser();

    /**
     * 测试分页插件的sql
     * @param page
     * @param state
     * @return
     */
    IPage<User> selectPageVo(Page<?> page, Integer state);
}
