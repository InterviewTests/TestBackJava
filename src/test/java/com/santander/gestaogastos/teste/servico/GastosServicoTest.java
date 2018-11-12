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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.santander.gestaogastos.exception.GastosException;
import com.santander.gestaogastos.model.Categoria;
import com.santander.gestaogastos.model.Gasto;
import com.santander.gestaogastos.model.Usuario;
import com.santander.gestaogastos.repository.CategoriaRepositorio;
import com.santander.gestaogastos.repository.GastosRepositorio;
import com.santander.gestaogastos.repository.UsuarioRepositorio;

@RunWith(SpringJUnit4ClassRunner.class)
public class GastosServicoTest {
	
	@Mock
	private GastosRepositorio gastosRepository;
	
	@Mock
	private CategoriaRepositorio categoriaRepository;
	
	@Mock
	private UsuarioRepositorio usuarioRepository;
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllGasto(){
		List<Gasto> gastoList = new ArrayList<Gasto>();
		
		Categoria categoria = new Categoria (categoriaRepository);
		categoria.setId(1);
		categoria.setDescricao("teste");
		
		Usuario usuario = new Usuario(usuarioRepository);
		usuario.setId(1);
		usuario.setNome("user");
		usuario.setRole("admin");
		
		Gasto gasto = new Gasto(gastosRepository);
		
		Gasto gasto1 = new Gasto(gastosRepository);
		gasto1.setId(1);
		gasto1.setDescricao("Gasto teste 1");
		gasto1.setValor(new BigDecimal(1));
		gasto1.setCategoria(categoria);
		gasto1.setUsuario(usuario);
		gasto1.setData(new Date());
		
		Gasto gasto2 = new Gasto(gastosRepository);
		gasto2.setId(2);
		gasto2.setDescricao("Gasto teste 2");
		gasto2.setValor(new BigDecimal(1));
		gasto2.setCategoria(categoria);
		gasto2.setUsuario(usuario);
		gasto2.setData(new Date());
		
		Gasto gasto3 = new Gasto(gastosRepository);
		gasto3.setId(3);
		gasto3.setDescricao("Gasto teste 3");
		gasto3.setValor(new BigDecimal(1));
		gasto3.setCategoria(categoria);
		gasto3.setUsuario(usuario);
		gasto3.setData(new Date());
		
		gastoList.add(gasto1);
		gastoList.add(gasto2);
		gastoList.add(gasto3);
		
		when(gasto.listaGastos()).thenReturn(gastoList);
		
		List<Gasto> result = gasto.listaGastos();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetGastoById() throws GastosException{
		Categoria categoria = new Categoria (categoriaRepository);
		categoria.setId(1);
		categoria.setDescricao("teste");
		
		Usuario usuario = new Usuario(usuarioRepository);
		usuario.setId(1);
		usuario.setNome("user");
		usuario.setRole("admin");
		
		Gasto gasto1 = new Gasto(gastosRepository);
		gasto1.setId(1);
		gasto1.setDescricao("Gasto teste 1");
		gasto1.setValor(new BigDecimal(1));
		gasto1.setCategoria(categoria);
		gasto1.setUsuario(usuario);
		gasto1.setData(new Date());
		
		when(gasto1.pesquisarGasto(1)).thenReturn(gasto1);
		
		Gasto result  = (Gasto) gasto1.pesquisarGasto(new Integer(1));
		assertEquals(new Integer(1), result.getId());
		assertEquals("Gasto teste 1", result.getDescricao());
		assertEquals(new BigDecimal(1), result.getValor());
	}
	
	@Test
	public void saveGasto() throws GastosException{
		Categoria categoria = new Categoria (categoriaRepository);
		categoria.setId(1);
		categoria.setDescricao("teste");
		
		Usuario usuario = new Usuario(usuarioRepository);
		usuario.setId(1);
		usuario.setNome("user");
		usuario.setRole("admin");
		
		Gasto gasto1 = new Gasto(gastosRepository);
		gasto1.setId(4);
		gasto1.setDescricao("Gasto teste 4");
		gasto1.setValor(new BigDecimal(1));
		gasto1.setCategoria(categoria);
		gasto1.setUsuario(usuario);
		gasto1.setData(new Date());
		
		when(gasto1.salvarGasto(gasto1)).thenReturn(gasto1);
		Gasto result = gasto1.salvarGasto(gasto1);
		assertEquals(new Integer(4), result.getId());
		assertEquals("Gasto teste 4", result.getDescricao());
		assertEquals(new BigDecimal(1), result.getValor());
	}
	
	@Test
	public void removeGasto() throws GastosException{
		Categoria categoria = new Categoria (categoriaRepository);
		categoria.setId(1);
		categoria.setDescricao("teste");
		
		Usuario usuario = new Usuario(usuarioRepository);
		usuario.setId(1);
		usuario.setNome("user");
		usuario.setRole("admin");
		
		Gasto gasto1 = new Gasto(gastosRepository);
		gasto1.setId(4);
		gasto1.setDescricao("Gasto teste ");
		gasto1.setValor(new BigDecimal(1));
		gasto1.setCategoria(categoria);
		gasto1.setUsuario(usuario);
		gasto1.setData(new Date());
		gasto1.removeGasto(gasto1);
		
        verify(gasto1, times(1)).removeGasto(gasto1);
	}
	
	

}

