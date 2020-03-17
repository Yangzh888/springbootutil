package com.cn.springbootutil.common.filter;

import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.interfaces.Claim;
import com.cn.springbootutil.common.exception.BusinessException;
import com.cn.springbootutil.common.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    /**
     * 简化获取token数据的代码编写（判断是否登录）
     *  1.通过request获取请求token信息
     *  2.从token中解析获取claims
     *  3.将claims绑定到request域中
     *  若检验不通过抛出特定异常让统一拦截器捕获
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BusinessException , IllegalArgumentException, UnsupportedEncodingException {
        // 1.通过request获取请求token信息
        System.out.println("拦截成功");
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        //判断请求头信息是否为空，或者是否已Bearer开头
            if(!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")) {
                //获取token数据
                String token = authorization.replaceFirst("Bearer", "");
                //解析token获取claims
                Map<String, Claim> user = JwtToken.verifyToken(token, "Savior");
                String userId = user.get("userId").asString();
                System.out.println("当前登录人ID： " + userId);
            }else {
                throw new BusinessException("登录超时或者权限不足");
            }
        return true;
    }
}
