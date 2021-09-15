package com.auguigu.demo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@Slf4j
@MapperScan(basePackages = "com.auguigu.demo.mapper")
@SpringBootApplication
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.auguigu.demo.redisson.*")
})
public class DemoApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication springApplication = new SpringApplication(DemoApplication.class);
		springApplication.run(args);
	}
}