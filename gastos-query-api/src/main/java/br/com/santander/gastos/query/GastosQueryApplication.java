package br.com.santander.gastos.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GastosQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(GastosQueryApplication.class, args);
    }
}

