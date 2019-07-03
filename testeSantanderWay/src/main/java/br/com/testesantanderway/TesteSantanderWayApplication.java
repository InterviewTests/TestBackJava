package br.com.testesantanderway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class TesteSantanderWayApplication {
//	@PostConstruct
//	void started(){
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//	}

	public static void main(String[] args) {
		SpringApplication.run(TesteSantanderWayApplication.class, args);
	}
}