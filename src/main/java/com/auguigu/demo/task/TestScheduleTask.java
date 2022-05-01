package com.auguigu.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Songwe
 * @date 2022/4/24 20:41
 */
//@Component
public class TestScheduleTask {
    
    @Scheduled(cron = "0/5 * * * * ?")
    public void run() {
        System.out.println("启动定时任务。。。");
    }
}
