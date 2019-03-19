package br.com.adslima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("br.com.adslima")
@EnableJpaRepositories(basePackages = { "br.com.adslima.repository" })
@EntityScan(basePackages = { "br.com.adslima.entity" })
public class ExpenseManagementAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagementAuthServerApplication.class, args);
	}
}
