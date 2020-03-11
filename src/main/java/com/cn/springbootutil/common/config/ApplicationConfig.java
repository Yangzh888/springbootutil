package com.cn.springbootutil.common.config;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author: YZH 获取配置文件中以demo为前缀的数据内容
 * @create: 2020-03-04 16:56
 **/
@Component
@ConfigurationProperties(prefix="demo")
public class ApplicationConfig {
    /**
     * 上传路径
     */
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
