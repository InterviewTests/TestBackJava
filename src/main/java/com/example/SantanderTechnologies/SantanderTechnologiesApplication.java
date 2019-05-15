package com.example.SantanderTechnologies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SantanderTechnologiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SantanderTechnologiesApplication.class, args);
	}

}
