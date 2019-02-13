package org.gateway.service;

import org.gateway.service.prefilters.SimpleLoggingPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayServiceApplication {

	@Bean
	public SimpleLoggingPreFilter simplePreFilter() {
		return new SimpleLoggingPreFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
}