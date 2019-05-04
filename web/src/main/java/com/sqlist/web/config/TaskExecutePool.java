package com.sqlist.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author SqList
 * @date 2019/5/4 21:58
 * @description 线程池
 **/
@Configuration
public class TaskExecutePool {

    @Value("${task.pool.corePoolSize}")
    private Integer corePoolSize;

    @Value("${task.pool.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${task.pool.keepAliveSeconds}")
    private Integer keepAliveSeconds;

    @Value("${task.pool.queueCapacity}")
    private Integer queueCapacity;

    @Bean
    public Executor taskAsyncExecutePool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        executor.setCorePoolSize(corePoolSize);
        // 最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 队列容量
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 活跃时间
        executor.setQueueCapacity(queueCapacity);
        // 线程名字前缀
        executor.setThreadNamePrefix("taskExecutor-");

        /**
         * setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
         *     CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
