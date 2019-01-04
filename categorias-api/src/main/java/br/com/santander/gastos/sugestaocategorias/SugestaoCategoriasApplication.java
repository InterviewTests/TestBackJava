package br.com.santander.gastos.sugestaocategorias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SugestaoCategoriasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SugestaoCategoriasApplication.class, args);
	}

}

