package br.com.camaroti.alex.res.api.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"br.com.camaroti.alex.res.api.model"})
@ComponentScan(basePackages = {"br.com.camaroti.alex.res.api.controller", "br.com.camaroti.alex.res.api.service"})
@EnableJpaRepositories("br.com.camaroti.alex.res.api.repository")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
