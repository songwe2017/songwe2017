package com.admin.demo.zookeeper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Songwe
 * @date 2022/5/2 13:40
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ZKOperator {
    
    @Value("${zookeeper.namespace}")
    private String namespace;
    
    private final CuratorFramework client;

    /**
     * create zookeeper node
     *
     * @param path node paht 
     * @param data node data
     * @return node path
     * @throws Exception
     */
    public String createNode (String path, String data) throws Exception {
        return createNode(path, data, false);
    }
    
    /**
     * create zookeeper node
     *
     * @param path node path 
     * @param data node data
     * @param isEphemeral is ephemeral node or persistent node
     * @return node path
     * @throws Exception
     */
    public String createNode(String path, String data, boolean isEphemeral) throws Exception {
        if (client == null) {
            throw new RuntimeException("[Create failed] - the client not connected");
        }

        String node = client.create()
                .creatingParentsIfNeeded()
                .withMode(isEphemeral ? CreateMode.EPHEMERAL : CreateMode.PERSISTENT)
                .forPath(path, data.getBytes());
        return node;
    }

    /**
     * check zNode exist or not, Stat is a map of zNode data。
     * if Stat == null mean zNode not exist
     *
     * @param path node paht
     * @return boolean
     * @throws Exception
     */
    public boolean isExist(String path) throws Exception {
        if (client == null) {
            throw new RuntimeException("[Check failed] - the client not connected");
        }
        
        return client.checkExists().forPath(path) != null;
    }

    /**
     * get zNode data 
     *
     * @param path node path
     * @return zNode data
     * @throws Exception
     */
    public String getNoeData(String path) throws Exception {
        if (client == null) {
            throw new RuntimeException("[Get failed] - the client not connected");
        }

        if (!isExist(path)) {
            log.info("Node for path {} not exist", namespace + path);
            return null;
        }

        byte[] data = client.getData().forPath(path);
        return new String(data);
    }

    /**
     * set zNode data
     *
     * @param path zNode path
     * @param data zNode data
     * @throws Exception
     */
    public void setNodeData(String path, String data) throws Exception {
        if (client == null) {
            throw new RuntimeException("[Set failed] - the client not connected");
        }

        if (!isExist(path)) {
            log.info("Node for path {} not exist, create new node", namespace + path);
            createNode(path, data);
            return;
        }
        
        client.setData().forPath(path, data.getBytes());
    }

    /**
     * delete node
     * @param path node path
     * @throws Exception
     */
    public void deleteNode(String path) throws Exception {
        if (client == null) {
            throw new RuntimeException("[Set failed] - the client not connected");
        }
        
        client.delete()
                .guaranteed() // 保障机制，若未删除成功，只要会话有效会在后台一直尝试删除
                .deletingChildrenIfNeeded() // 若当前节点包含子节点，子节点也删除
                .forPath(path);
    }

    /**
     * get children list
     *
     * @param path node path
     * @return children list
     * @throws Exception
     */
    public List<String> getChildren(String path) throws Exception {
        if (client == null) {
            throw new RuntimeException("[Get failed] - the client not connected");
        }

        if (!isExist(path)) {
            throw new RuntimeException("ZNode not exist for path " + namespace + path);
        }
        
        return client.getChildren().forPath(path);
    }

    /**
     * add watch for path node
     * 
     * @param path
     */
    public void addWatchWithNodeCache(String path) {
        if (client == null) {
            throw new RuntimeException("[Add failed] - the client not connected");
        }
        /*
         * Options.SINGLE_NODE_CACHE 只监听当前节点，不监听子节点，可以代替 NodeCache
         * 如果不指定该选项，默认代表监听当前节点及子节点，可以代替TreeCache 
         */
        final CuratorCache cache = CuratorCache.build(client, path, CuratorCache.Options.SINGLE_NODE_CACHE);

        CuratorCacheListener listener = CuratorCacheListener.builder()
                // .forNodeCache() 该方法是兼容 NodeCache，可以通过 CuratorCache.Options 参数代替，应尽量不用
                // .forPathChildrenCache() 该方法是兼容 PathChildrenCache，该监听器监听缓存中指定根节点的所有子节点，并且不包括根节点，虽无法被代替，但应尽量不用
                // .forTreeCache() 该方法是兼容 TreeCache, 可以不指定 CuratorCache.Options 参数代替应尽量不用
                // .forInitialized(() -> log.info("Cache initialized")) 初始化完成时调用
                // .forCreates(node -> log.info("Node created: {}", node)) 添加缓存中的数据时调用
                // .forCreatesAndChanges((oldNode, node) -> log.info("Node changed. Old: {} New: {}", oldNode, node)) 添加或更改缓存中的数据时调用
                // .forDeletes(node -> log.info("Node deleted: {}", node)) 删除缓存中的数据时调用
                // .forAll((type, oldData, data) -> log.info("[forAll] : type: {} {} {}", type, oldData, data)) 所有情况调用
                // 以上所有方法均会导致启动后立即执行一次 listener方法，只有 forChanges 是在状态变更后执行
                .forChanges((oldData, data) -> { // 更改缓存中的数据时调用
                    log.info("节点状态发生变更！");
                }).build();
        cache.listenable().addListener(listener);
        log.info("添加 [Watch] 至 {}", namespace + path);
        
        cache.start();
    }
    
    public void addWatchWithTreeNodeCache(String path) {
        if (client == null) {
            throw new RuntimeException("[Add failed] - the client not connected");
        }

        final CuratorCache cache = CuratorCache.build(client, path);

        CuratorCacheListener listener = CuratorCacheListener.builder()
                .forAll((type, oldData, data) -> {
                    log.info("[forAll] : type: {} {} {}", type, oldData, data);
                }).build();
        cache.listenable().addListener(listener);
        log.info("添加 [Watch] 至 {}", namespace + path);

        cache.start();
    }
    
}
