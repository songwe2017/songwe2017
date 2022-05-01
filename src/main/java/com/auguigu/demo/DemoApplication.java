package com.auguigu.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.SystemProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@MapperScan(basePackages = "com.auguigu.demo.mapper")
@EnableScheduling
@SpringBootApplication
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.auguigu.demo.redisson.*")
})
public class DemoApplication {
	public static void main(String[] args) throws Exception {
		ApplicationContext application = SpringApplication.run(DemoApplication.class, args);
	}
}
