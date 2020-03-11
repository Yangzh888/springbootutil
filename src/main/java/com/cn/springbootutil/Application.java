package com.cn.springbootutil;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.cn.springbootutil.dao")
@EnableSwagger2
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
public class Application  /*extends SpringBootServletInitializer */{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
