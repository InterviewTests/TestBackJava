package br.com.santander.gastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class GastosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GastosApplication.class, args);
	}

}
