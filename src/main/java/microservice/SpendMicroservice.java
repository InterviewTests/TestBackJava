package microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.core.task.TaskExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@EnableAsync
@SpringBootApplication
public class SpendMicroservice {

    @Bean(name = "ThreadPoolExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("ThreadPoolExecutor-");
        executor.initialize();
        return executor;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpendMicroservice.class, args);
    }
    
}
