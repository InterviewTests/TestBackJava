package microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.core.task.TaskExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.beans.factory.annotation.Value;

@EnableAsync
@SpringBootApplication
public class SpendsMicroservice {

    @Value("${thread.pool.core.size}")
    private int corePoolSize;

    @Value("${thread.pool.max.size}")
    private int maxPoolSize;

    @Value("${thread.queue.capacity}")
    private int queueCapacity;


    @Bean(name = "ThreadPoolExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("ThreadPoolExecutor-");
        executor.initialize();
        return executor;
    }

    
    public static void main(String[] args) {
        SpringApplication.run(SpendsMicroservice.class, args);
    }
    
}
