package com.example.his.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName:
 * Package: com.example.his.api.config
 * Description: 测试
 *
 * @Author Ocean_jhl
 * @Create 2024/6/10 11:46
 * @Version 1.0
 */
@Configuration
public class ThreadPoolConfig {
    // 注册的名字为 AsyncTaskExecutor
    @Bean("AsyncTaskExecutor")
    public AsyncTaskExecutor taskExecutor() {
        // 创建线程池执行者
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(8);
        // 设置最大线程数
        executor.setMaxPoolSize(16);
        // 设置队列容量【等待处理的任务数量】
        executor.setQueueCapacity(1000);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 线程名称的前缀
        executor.setThreadNamePrefix("task-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化线程池
        executor.initialize();
        return executor;
    }
}
