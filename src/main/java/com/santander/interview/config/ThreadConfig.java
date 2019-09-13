package com.santander.interview.config;

import com.santander.interview.utils.ExpenseManagementUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadConfig {
    public static final String THREAD_NAME_PREFIX = "interview";

//    @Value("#{environment['ASYNC_CORE_POOL_SIZE']}")
    @Value("${thread.async_core_pool_size}")
    private String ASYNC_CORE_POOL_SIZE;

//    @Value("#{environment['ASYNC_MAX_POOL_SIZE']}")
    @Value("${thread.async_max_pool_size}")
    private String ASYNC_MAX_POOL_SIZE;

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(
                ExpenseManagementUtils.convertStringtoInt(10, ASYNC_CORE_POOL_SIZE)
        );
        threadPoolTaskExecutor.setMaxPoolSize(
                ExpenseManagementUtils.convertStringtoInt(100, ASYNC_MAX_POOL_SIZE)
        );
        threadPoolTaskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        threadPoolTaskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        threadPoolTaskExecutor.initialize();

        return threadPoolTaskExecutor;
    }
}
