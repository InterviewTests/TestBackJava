package br.com.adslima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ExpenseManagementAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagementAuthServerApplication.class, args);
	}
}
