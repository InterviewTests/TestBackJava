package com.santander.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.santander.interview"})
public class ExpenseManagement {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseManagement.class, args);
    }

}
