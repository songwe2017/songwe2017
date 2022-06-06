package com.admin;

import com.admin.common.util.JsonUtils;
import com.admin.redis.RedissonManager;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;

@Slf4j
@EnableScheduling
@SpringBootApplication
@ComponentScan(excludeFilters = {
		//@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.admin.redis.*"),
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.admin.zookeeper.*")
})
public class DemoApplication {
	public static void main(String[] args) throws Exception {
		ApplicationContext application = SpringApplication.run(DemoApplication.class, args);
	}
}
