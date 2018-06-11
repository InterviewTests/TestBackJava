package br.com.santander.card.core.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
