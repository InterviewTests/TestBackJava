package br.com.testesantanderway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class TesteSantanderWayApplication {
	public static void main(String[] args) {
		SpringApplication.run(TesteSantanderWayApplication.class, args);
	}

}
