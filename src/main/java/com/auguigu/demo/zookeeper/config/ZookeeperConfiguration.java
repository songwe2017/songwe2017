package com.auguigu.demo.zookeeper.config;

import com.auguigu.demo.zookeeper.property.ZookeeperProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Songwe
 * @date 2022/5/2 0:20
 */
@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@EnableConfigurationProperties(ZookeeperProperties.class)
public class ZookeeperConfiguration {
    
    private final ZookeeperProperties properties;
    
    @Bean
    @ConditionalOnMissingBean
    public CuratorFramework curatorFramework() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(properties.getBaseSleepTime(), properties.getMaxRetries());
        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
                .connectString(properties.getConnectString())
                .sessionTimeoutMs(properties.getSessionTimeout()) // 会话超时时间
                .connectionTimeoutMs(properties.getConnectionTimeout()) // 连接超时时间
                .retryPolicy(retryPolicy)
                .namespace(properties.getNamespace())
                .build();
        
        zkClient.getConnectionStateListenable().addListener((client, state) -> {
            if (state == ConnectionState.CONNECTED) {
                log.info("Curator 连接成功！");
            }
            if (state == ConnectionState.LOST) {
                log.info("Curator 连接断开！");
            }
        });
        zkClient.start();
        return zkClient;
    }
}
