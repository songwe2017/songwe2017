package com.admin.zookeeper.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Songwe
 * @date 2022/5/2 0:20
 */
@Data
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperProperties {
    
    /** Connection str  host:port,separate multiple entries with commas */
    private String connectString;

    /** Namespace for isolation */
    private String namespace;
    
    /** Session timeout */
    private int sessionTimeout = 60000;
    
    /** Times for waiting establish connect*/
    private int connectionTimeout = 15000;
    
    /** Initial waiting time */
    private int baseSleepTime = 1000;
    
    /** Max retry times */
    private int maxRetries = 3;
}
