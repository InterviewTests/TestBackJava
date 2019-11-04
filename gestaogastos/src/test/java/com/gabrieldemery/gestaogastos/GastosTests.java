package com.gabrieldemery.gestaogastos;

import java.sql.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.gabrieldemery.gestaogastos.entities.Gasto;
import com.gabrieldemery.gestaogastos.repositories.GastosRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class GastosTests {
	
	@Mock
	private GastosRepository gastosRepository;
	
	@Test
	public void inserir() {
		
		Gasto gasto = new Gasto();
		gasto.setCodigousuario(Long.valueOf(2));
		gasto.setData(new Date(System.currentTimeMillis()));
		gasto.setDescricao("PRIMEIRO TESTE");
		gasto.setValor(Double.valueOf(1.50));
		
		Gasto gastoSalvo = this.gastosRepository.save(gasto);
		
		Assert.assertNotNull(gastoSalvo);
		
	}
	
	@Test
	public void listar() {
		
		for (int i = 0; i < 3; i++) {
			Gasto gasto = new Gasto();
			gasto.setCodigousuario(Long.valueOf(2));
			gasto.setData(new Date(System.currentTimeMillis()));
			gasto.setDescricao("TESTE " + i);
			gasto.setValor(Double.valueOf(i * 1.50));
			this.gastosRepository.save(gasto);
		}
		
		List<Gasto> lista = this.gastosRepository.findAll();
		
		Assert.assertTrue(lista.size() > 0);
		
	}
	
	
}
