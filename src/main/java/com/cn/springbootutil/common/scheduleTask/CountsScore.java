package com.cn.springbootutil.common.scheduleTask;

import com.cn.springbootutil.common.scheduleTask.utils.ApplicationContextUtil;
import com.cn.springbootutil.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @description 定时任务demo
 * @author: YZH
 * @create: 2019-12-12 09:48
 **/
@Configuration
@EnableScheduling
public class CountsScore {

    /**
     * 每个月1号凌晨一点执行
     * 注入的service要加注解  如：@Service（"UserServiceImpl"）
     * @Scheduled(cron = "0 0 3 1 4,7,10 ?")每个季度1号凌晨3点执行
     * @Scheduled(cron = " 0/30 * * * * ?")  /* 每30秒计算一次
     * @Scheduled(cron = "0 0 1 1 * ?")    //每月一号一点执行
     * */
        @Scheduled(cron = "0 0 1 1 * ?")
        private void countScoreByMonth() {
            //获取Bean 举例：注入的service要加注解  如：@Service（"UserServiceImpl"）
            UserServiceImpl userService = (UserServiceImpl) ApplicationContextUtil.getBean("UserServiceImpl");
            System.out.println("定时任务countScoreByMonth方法执行成功");
    }

}
