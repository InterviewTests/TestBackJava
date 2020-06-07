package com.teste.gft.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.gft.entities.Gasto;

@Transactional
@Commit
@SpringBootTest
@RunWith(SpringRunner.class)

class GastoServiceTest {
	@Autowired
	GastoService gastoService;

	@Test
	void testCriarGasto() throws JsonProcessingException {
		Gasto gasto = new Gasto();
		gasto.setValor(10.50);
		gasto.setDescricao("Primeira Descrição");
		gasto.setData(LocalDate.now());
		gasto.setCodigoUsuario((long) 1);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(gasto);
		System.out.println("JSON = " + json);

		assertEquals(gasto, gastoService.criarGasto(gasto));

	}

	@Test
	void testListarGastosUsuario() {
		String email = "Guilherme";
		String password = "senha";
		Long id = (long) 1;
		List<Gasto> lista = gastoService.listarGastosUsuario(id, email, password);
		assertNotEquals(null, lista);
	}

}
