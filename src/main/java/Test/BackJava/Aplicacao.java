package Test.BackJava;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Aplicacao {

	public static void main(String[] args) {
		SpringApplication.run(Aplicacao.class, args);
	}
	
	private static final Logger log = LoggerFactory.getLogger(Aplicacao.class);

	@Bean
	public CommandLineRunner demo(RepositorioDeGastos repositorio) {
		return (args) -> {
			log.info("--------------------------------");
			log.info("Gastos encontrados com findAll()");
			log.info("--------------------------------");
			for (Gasto gasto : repositorio.findAll()) {
			  log.info(gasto.toString());
			}
			List<Gasto> gastos = repositorio.findByCodigousuario(1);
			log.info("-------------------------------------------");
			log.info("Gasto encontrado com findByCodigousuario(1)");
			log.info("-------------------------------------------");
			for (Gasto g : gastos) {
				log.info(g.toString());
			}
		};
	}

}
