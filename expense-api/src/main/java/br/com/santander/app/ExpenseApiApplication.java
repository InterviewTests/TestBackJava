package br.com.santander.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class ExpenseApiApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ExpenseApiApplication.class, args);
	}
}
