package Test.BackJava;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ConfiguracaoBancoDeDados {

  private static final Logger log = LoggerFactory.getLogger(ConfiguracaoBancoDeDados.class);

  @Bean
  CommandLineRunner initDatabase(RepositorioDeGastos repository) {
	  Calendar cal = Calendar.getInstance();
	  cal.set(Calendar.YEAR, 2020);
	  cal.set(Calendar.MONTH, Calendar.JUNE);
	  cal.set(Calendar.DAY_OF_MONTH, 2);
	  Date data1 = cal.getTime();
	  
	  cal.set(Calendar.DAY_OF_MONTH, 3);
	  Date data2 = cal.getTime();
	
	  return args -> {
		  log.info("--------------------------------------");
		  log.info("Gastos pre-definidos no banco de dados");
		  log.info("--------------------------------------");
		  // codigousuario = 1
		  log.info(repository.save(new Gasto("Supermercado", 222.12, 1, data1)).toString());
		  log.info(repository.save(new Gasto("Barbearia", 30.0, 1, data1)).toString());
		  log.info(repository.save(new Gasto("Feira", 120.14, 1, data2)).toString());
		  log.info(repository.save(new Gasto("Condominio", 1500.0, 1, data2)).toString());
		  log.info(repository.save(new Gasto("Seguro", 230.0, 1, data2)).toString());
		  // codigousuario = 2
		  log.info(repository.save(new Gasto("Restaurante", 25.0, 2, data1)).toString());
		  log.info(repository.save(new Gasto("Carro", 30000.0, 2, data1)).toString());
		  log.info(repository.save(new Gasto("Farmacia", 50.32, 2, data2)).toString());
		  // codigousuario = 3
		  log.info(repository.save(new Gasto("Hospital", 1000.0, 3, data2)).toString());
		  log.info(repository.save(new Gasto("Eletronico", 200.0, 3, data2)).toString());
	  };
	}
}
