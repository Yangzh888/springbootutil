package com.cn.springbootutil.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.springbootutil.common.result.Result;
import com.cn.springbootutil.common.result.ResultCodeEnum;
import com.cn.springbootutil.common.result.ResultData;
import com.cn.springbootutil.common.token.JwtToken;
import com.cn.springbootutil.entity.User;
import com.cn.springbootutil.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YZH
 * @since 2020-02-27
 */
@Api(tags = "用户Controller")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;
    /**
     * 登录接口
     * 测试Result全局统一返回对象
     * @param user
     * @return
     */
    @ApiOperation(value = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "账号",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)
    })
    @RequestMapping("/login")
    public Result login(@Validated User user) {
        Result result = userService.login(user.getUserId(), user.getPassword());
        return result ;
    }

    /**
     * http://localhost:8801/demo/user/findById?id=1 访问路径
     * 测试自定义redis
     * 测试通过xml写sql
     * @param id
     * @return
     */
    @ApiOperation(value = "通过id获取对象")
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public ResultData<User> findById( @NotNull(message = "参数异常") Integer id) {
        ResultData resultData=new ResultData();
        User user = userService.findById(id);
        return  resultData.ok(user);
    }

    /**
     * 测试Redis的移除
     * @param id
     * @return
     */
    @ApiOperation(value = "通过id删除对象")
    @RequestMapping("/deleteById")
    public ResultData deleteById( Integer id) {
        ResultData resultData=new ResultData();
        List list = userService.deleteById(id);
        return  resultData.ok(list);
    }

    /**
     * 测试全局处理异常
     * @return
     */
    @RequestMapping("/getBug")
    public Result getBug() {
        String s=null;
        System.out.println(s.length());
        return  Result.ok();
    }

    /**
     * 测试mybatis-plus分页插件
     * @param current
     * @return
     */
    @RequestMapping("/testResult")
    public Result testResult(Integer current) {
        IPage<User> page=  userService.selectPageVo(new Page<>(current,10),1);
        return  Result.ok(page);
    }
}
