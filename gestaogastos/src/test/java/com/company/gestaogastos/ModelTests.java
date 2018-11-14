package com.company.gestaogastos;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.gestaogastos.domain.CategoriaDomain;
import com.company.gestaogastos.domain.GastoDomain;
import com.company.gestaogastos.domain.entity.Categoria;
import com.company.gestaogastos.domain.entity.Gasto;
import com.company.gestaogastos.domain.repository.CategoriaRepository;
import com.company.gestaogastos.domain.repository.GastoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModelTests {
	
	@MockBean
	private GastoRepository gastoRepository;

	@MockBean
	private CategoriaRepository categoriaRepository;
	
	private static long idTeste = 1;
	
	@Test
	public void retrieveAllGastos() {
		List<Gasto> gastos = new ArrayList<Gasto>();
		Gasto gasto = new Gasto();
		gasto.setCodigousuario(1);
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(idTeste);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new Categoria(1L, "Categoria 01"));
		gastos.add(gasto);
		gastos.add(cloneGasto(gasto, "gasto 02"));
		gastos.add(cloneGasto(gasto, "gasto 03"));
		gastos.add(cloneGasto(gasto, "gasto 04"));
		gastos.add(cloneGasto(gasto, "gasto 05"));
		gastos.add(cloneGasto(gasto, "gasto 06"));
		gastos.add(cloneGasto(gasto, "gasto 07"));
		gastos.add(cloneGasto(gasto, "gasto 08"));
		gastos.add(cloneGasto(gasto, "gasto 09"));
		gastos.add(cloneGasto(gasto, "gasto 10"));
		when(gastoRepository.findAll()).thenReturn(gastos);
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		
		List<Gasto> gastosDomain = gastoDomain.retrieveAllGastos();
		assertTrue(gastosDomain.equals(gastos ));
	}

	@Test
	public void retrieveGasto() {
		Gasto gasto = new Gasto();
		gasto.setCodigousuario(1);
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(idTeste);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new Categoria(1L, "Categoria 01"));
		Optional<Gasto> gastoOp = Optional.of(gasto);
		Long id = 1L;
		when(gastoRepository.findById(id )).thenReturn(gastoOp);

		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Gasto gastoReturn = gastoDomain.retrieveGasto(id);
		assertTrue(gastoReturn.equals(gasto ));
	}
	
	@Test
	public void retrieveGastoByUser() {
		Integer id = 1;
		PageRequest pageRequest = new PageRequest(0, 4);
		final Page<Gasto> page = Mockito.mock(Page.class);
	    when(page.getTotalElements()).thenReturn(8L);
	    when(page.getTotalPages()).thenReturn(2);
	    when(page.getNumber()).thenReturn(3);
	    when(page.getNumberOfElements()).thenReturn(5);
		when(gastoRepository.findByCodigousuarioOrderByDataDesc(id, pageRequest)).thenReturn(page);

		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Page<Gasto> gastosReturn = gastoDomain.retrieveGastoByUser(id);
		assertTrue(gastosReturn.equals(page));
	}

	@Test
	public void retrieveGastoByUserDate() {
		Integer id = 1;
		String date = "13-11-2018";
		PageRequest pageRequest = new PageRequest(0, 4);
		final Page<Gasto> page = Mockito.mock(Page.class);
	    when(page.getTotalElements()).thenReturn(8L);
	    when(page.getTotalPages()).thenReturn(2);
	    when(page.getNumber()).thenReturn(3);
	    when(page.getNumberOfElements()).thenReturn(5);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    Date parsedDate;
		try {
			parsedDate = dateFormat.parse(date);
		    Timestamp dataInferior = new Timestamp(parsedDate.getTime());
		    System.out.println("timestamp=" + dataInferior);
		    Timestamp dataSuperior = new Timestamp(parsedDate.getTime() + (24*60*60*1000)-1);
		    System.out.println("timestamp=" + dataSuperior);
			when(gastoRepository.findByCodigousuarioOrderByDataDesc(id, dataInferior, dataSuperior, pageRequest)).thenReturn(page);

			GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
			Page<Gasto> gastosReturn = gastoDomain.retrieveGastoByUserDate(id, date);
			assertTrue(gastosReturn.equals(page));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}

	@Test
	public void createGasto() {
		Gasto gasto = new Gasto();
		gasto.setCodigousuario(1);
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(idTeste);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new Categoria(1L, "Categoria 01"));
		when(gastoRepository.save(gasto)).thenReturn(gasto);

		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Gasto gastosReturn = gastoDomain.createGasto(gasto);
		assertTrue(gastosReturn.equals(gasto));
	}
    
	@Test
	public void updateGasto() {
		long id = 1L;
		Gasto gasto = new Gasto();
		gasto.setCodigousuario(1);
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(idTeste);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new Categoria(1L, "Categoria 01"));
		when(gastoRepository.save(gasto)).thenReturn(gasto);

		Optional<Gasto> gastoOp = Optional.of(gasto);
		when(gastoRepository.findById(id)).thenReturn(gastoOp);

		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Gasto gastosReturn = gastoDomain.updateGasto(gasto, id);
		assertTrue(gastosReturn.equals(gasto));
	}
	
	@Test
	public void retrieveAllCategorias() {
		idTeste = 1;
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria categoria = new Categoria();
		categoria.setId(idTeste);
		categoria.setNome("Categoria 01");
		categorias.add(categoria);
		categorias.add(cloneCategoria(categoria, "Categoria 02"));
		categorias.add(cloneCategoria(categoria, "Categoria 03"));
		when(categoriaRepository.findAll()).thenReturn(categorias);
		
		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		List<Categoria> categoriasReturn = categoriaDomain.retrieveAllCategorias();
		assertTrue(categoriasReturn.equals(categorias ));
	}

	@Test
	public void retrieveCategoria() {
		Categoria categoria = new Categoria();
		categoria.setId(idTeste);
		categoria.setNome("Categoria 01");
		Optional<Categoria> categoriaOp = Optional.of(categoria);
		Long id = 1L;
		when(categoriaRepository.findById(id)).thenReturn(categoriaOp);

		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		Categoria categoriaReturn = categoriaDomain.retrieveCategoria(id);
		assertTrue(categoriaReturn.equals(categoria));
	}

	@Test
	public void retrieveCategoria2() {
		String nome = "Categoria 01";
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		categoria.setNome("Categoria 01");

		PageRequest pageRequest = new PageRequest(0, 4);
		final Page<Categoria> page = Mockito.mock(Page.class);
	    when(page.getTotalElements()).thenReturn(8L);
	    when(page.getTotalPages()).thenReturn(2);
	    when(page.getNumber()).thenReturn(3);
	    when(page.getNumberOfElements()).thenReturn(5);
		when(categoriaRepository.findByNome(nome, pageRequest)).thenReturn(page);
		
		
		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		Page<Categoria> categoriasReturn = categoriaDomain.retrieveCategoria2(nome);
		assertTrue(categoriasReturn.equals(page));
	}

	@Test
	public void createCategoria() {
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		categoria.setNome("Categoria 01");
		when(categoriaRepository.save(categoria)).thenReturn(categoria);
		
		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		Categoria categoriaReturn = categoriaDomain.createCategoria(categoria);
		assertTrue(categoriaReturn.equals(categoria));
	}
	
	@Test
	public void updateCategoria() {
		long id = 1L;
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		categoria.setNome("Categoria 01");
		when(categoriaRepository.save(categoria)).thenReturn(categoria);

		Optional<Categoria> categoriaOp = Optional.of(categoria);
		when(categoriaRepository.findById(id)).thenReturn(categoriaOp);

		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		Categoria categoriaReturn = categoriaDomain.updateCategoria(categoria, id);
		assertTrue(categoriaReturn.equals(categoria));
	}

	private Gasto cloneGasto(Gasto gasto, String descricao) {
		idTeste = idTeste + 1;
		Gasto clone = new Gasto();
		clone.setId(idTeste);
		clone.setDescricao(descricao);
		clone.setValor(gasto.getValor());
		clone.setCodigousuario(gasto.getCodigousuario());
		clone.setData(gasto.getData());
		clone.setCategoria(gasto.getCategoria());
		return clone;
	}

	private Categoria cloneCategoria(Categoria categoria, String nome) {
		idTeste = idTeste + 1;
		Categoria clone = new Categoria();
		clone.setId(idTeste);
		clone.setNome(nome);
		return clone;
	}

}
