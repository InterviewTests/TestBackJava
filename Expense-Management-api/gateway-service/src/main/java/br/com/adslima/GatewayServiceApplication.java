package br.com.adslima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import br.com.adslima.prefilters.SimpleLoggingPreFilter;

@SpringBootApplication
@EnableZuulProxy
@ComponentScan("br.com.adslima")
public class GatewayServiceApplication {

	@Bean
	public SimpleLoggingPreFilter simplePreFilter() {
		return new SimpleLoggingPreFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
}