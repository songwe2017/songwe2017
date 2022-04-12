package com.auguigu.demo;

import com.auguigu.demo.model.User;
import com.auguigu.demo.service.A;
import com.auguigu.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.Arrays;
import java.util.List;

@Slf4j
@MapperScan(basePackages = "com.auguigu.demo.mapper")
@SpringBootApplication
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.auguigu.demo.redisson.*")
})

public class DemoApplication {
	public static void main(String[] args) throws Exception {
		ApplicationContext application = SpringApplication.run(DemoApplication.class, args);

		A a = application.getBean(A.class);
		a.testAop("zhangsan");
		//System.out.println(user);
		

	}
}