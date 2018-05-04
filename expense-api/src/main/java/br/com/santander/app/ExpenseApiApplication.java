package br.com.santander.app;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.santander.app.model.Category;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class ExpenseApiApplication {

	@Autowired
	private RedisTestes redisTestes;

	@PostConstruct
	private void init() {
		final Category category = new Category();
		category.setId(1L);
		category.setDescription("Description Category");
		redisTestes.insert(category);
	}

	public static void main(final String[] args) {
		SpringApplication.run(ExpenseApiApplication.class, args);
	}
}
