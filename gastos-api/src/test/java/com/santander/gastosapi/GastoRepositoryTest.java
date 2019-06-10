package com.santander.gastosapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.santander.gastosapi.model.Gasto;
import com.santander.gastosapi.repository.GastosRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GastosApiApplication.class, loader=SpringBootContextLoader.class)
public class GastoRepositoryTest {
	
	@Autowired
	private GastosRepository gastosRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createGasto() throws ParseException{
		gastosRepository.save(new Gasto("id-teste-create-gasto-001", "Descrição do gasto 01", 2.64, 3, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45")));
		Gasto novoGasto = gastosRepository.findOne("id-teste-create-gasto-001");
		Assertions.assertThat(novoGasto).isNotNull();
		Assertions.assertThat(novoGasto.getId()).isEqualTo("id-teste-create-gasto-001");
	}
		
	@Test
	public void getGasto() throws ParseException{
		gastosRepository.save(new Gasto("id-teste-get-gasto-001", "Descrição do gasto 99", 2.64, 3, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45")));
		Gasto novoGasto = gastosRepository.findOne("id-teste-get-gasto-001");
		Assertions.assertThat(novoGasto).isNotNull();
		Assertions.assertThat(novoGasto.getId()).isEqualTo("id-teste-get-gasto-001");
	}
	
	
	@Test
	public void updateGasto() throws ParseException{
		gastosRepository.save(new Gasto("id-teste-update-gasto-001", "Descrição do gasto 99", 2.64, 3, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45")));
		gastosRepository.save(new Gasto("id-teste-update-gasto-001", "Descrição do gasto 100", 2.64, 3, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45")));
		Gasto novoGasto = gastosRepository.findOne("id-teste-update-gasto-001");
		Assertions.assertThat(novoGasto.getId()).isEqualTo("id-teste-update-gasto-001");
		Assertions.assertThat(novoGasto.getDescricao()).isEqualTo("Descrição do gasto 100");
	}
}
