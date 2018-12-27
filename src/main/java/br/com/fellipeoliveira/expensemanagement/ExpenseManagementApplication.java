package br.com.fellipeoliveira.expensemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ExpenseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagementApplication.class, args);
	}

}

