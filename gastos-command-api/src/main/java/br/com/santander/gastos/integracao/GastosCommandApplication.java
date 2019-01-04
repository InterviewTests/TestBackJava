package br.com.santander.gastos.integracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GastosCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(GastosCommandApplication.class, args);
    }

}

