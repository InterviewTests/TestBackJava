package br.com.rbs.testebackjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class TesteBackJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteBackJavaApplication.class, args);
	}
}
