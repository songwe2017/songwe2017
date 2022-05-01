package com.auguigu.demo.zk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Songwe
 * @date 2022/5/2 0:20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "zookeeper")
public class ZooKeeperProperties {
    private String connectString;
    private String namespace;
    private int sessionTimeout = 60000;
    private int connectionTimeout = 15000;
    private int baseSleepTime = 1000;
    private int maxRetries = 3;
}
