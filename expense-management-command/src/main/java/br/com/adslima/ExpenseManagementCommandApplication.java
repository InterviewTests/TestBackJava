package br.com.adslima;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author andrews.silva
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableResourceServer
@ComponentScan("br.com.adslima")
public class ExpenseManagementCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagementCommandApplication.class, args);
	}

	@RestController
	class ServiceInstanceRestController {

		@Autowired
		private DiscoveryClient discoveryClient;

		@Value("${spring.application.name}")
		private String appName;

		@RequestMapping("/instances")
		public List<ServiceInstance> serviceInstancesByApplicationName() {
			return this.discoveryClient.getInstances(appName);
		}
	}

	@RefreshScope
	@RestController
	class MessageRestController {

		@Value("${message}")
		private String message;

		@RequestMapping("/message")
		String getMessage() {
			return this.message;
		}
	}
}
