package br.com.resource.testbackjava.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication(scanBasePackages={"br.com.resource.testbackjava"})
@ComponentScan({"br.com.resource.testbackjava"})
@EnableCassandraRepositories({"br.com.resource.testbackjava.data"})
@EnableAspectJAutoProxy
public class TestbackjavaApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TestbackjavaApplication.class, args);
	}
}
