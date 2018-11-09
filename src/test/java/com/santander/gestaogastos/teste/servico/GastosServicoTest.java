package com.santander.gestaogastos.teste.servico;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.santander.gestaogastos.model.Categoria;
import com.santander.gestaogastos.model.Gasto;
import com.santander.gestaogastos.model.Usuario;
import com.santander.gestaogastos.repository.GastosRepositorio;
import com.santander.gestaogastos.service.GastosServicoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class GastosServicoTest {
	
	@Mock
	private GastosRepositorio gastosRepository;
	
	@InjectMocks
	private GastosServicoImpl gastosService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllGasto(){
		List<Gasto> gastoList = new ArrayList<Gasto>();
		
		Categoria categoria = new Categoria(1, "teste");
		Usuario user = new Usuario(1,"user", "admin");
		
		gastoList.add(new Gasto(1,"Gasto teste 1",new BigDecimal(1),categoria,user,new Date()));
		gastoList.add(new Gasto(2,"Gasto teste 2",new BigDecimal(1),categoria,user,new Date()));
		gastoList.add(new Gasto(3,"Gasto teste 3",new BigDecimal(1),categoria,user,new Date()));
		when(gastosRepository.findAll()).thenReturn(gastoList);
		
		List<Gasto> result = gastosService.listaGastos();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetGastoById(){
		Categoria categoria = new Categoria(1, "teste");
		Usuario user = new Usuario(1,"user", "admin");
		Gasto gasto = new Gasto(1,"Gasto teste 1",new BigDecimal(1),categoria,user,new Date());
		when(gastosRepository.getOne(new Integer(1))).thenReturn(gasto);
		Gasto result  = (Gasto) gastosService.pesquisarGasto(new Integer(1));
		assertEquals(new Integer(1), result.getId());
		assertEquals("Gasto teste 1", result.getDescricao());
		assertEquals(new BigDecimal(1), result.getValor());
	}
	
	@Test
	public void saveGasto(){
		Categoria categoria = new Categoria(1, "teste");
		Usuario user = new Usuario(1,"user", "admin");
		Gasto gasto = new Gasto(4,"Gasto teste 4",new BigDecimal(1),categoria,user,new Date());
		when(gastosRepository.save(gasto)).thenReturn(gasto);
		Gasto result = gastosService.salvarGasto(gasto);
		assertEquals(new Integer(4), result.getId());
		assertEquals("Gasto teste 4", result.getDescricao());
		assertEquals(new BigDecimal(1), result.getValor());
	}
	
	@Test
	public void removeGasto(){
		Categoria categoria = new Categoria(1, "teste");
		Usuario user = new Usuario(1,"user", "admin");
		Gasto gasto = new Gasto(4,"Gasto teste 1",new BigDecimal(1),categoria,user,new Date());
		gastosService.removeGasto(gasto);
        verify(gastosRepository, times(1)).delete(gasto);
	}
	
	

}

