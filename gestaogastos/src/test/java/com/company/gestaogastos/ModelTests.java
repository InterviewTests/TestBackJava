package com.company.gestaogastos;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.gestaogastos.domain.Categoria;
import com.company.gestaogastos.domain.Gasto;
import com.company.gestaogastos.domain.Usuario;
import com.company.gestaogastos.domain.dto.CategoriaDTO;
import com.company.gestaogastos.domain.dto.GastoDTO;
import com.company.gestaogastos.domain.dto.UsuarioDTO;
import com.company.gestaogastos.domain.repository.CategoriaRepository;
import com.company.gestaogastos.domain.repository.GastoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModelTests {
	
	@MockBean
	private GastoRepository gastoRepository;

	@MockBean
	private CategoriaRepository categoriaRepository;
	
	static final int CATEGORIAS_PAGE_SIZE = 4;

	private static long idTeste = 1;

	@Test
	public void retrieveAllGastos() {
		PageRequest pageRequest = PageRequest.of(0, 20, new Sort(Sort.Direction.DESC,"data"));
		List<Gasto> gastos = new ArrayList<Gasto>();
		Gasto gasto = new Gasto();
		gasto.setUsuario(new Usuario(1L, "Usuario 01"));
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
		
		Long start = pageRequest.getOffset();
		Long end = (start + pageRequest.getPageSize()) > gastos.size() ? gastos.size() : (start + pageRequest.getPageSize());
		Page<Gasto> pageGasto = new PageImpl<Gasto>(gastos.subList(start.intValue(), end.intValue()), pageRequest, gastos.size());
		
		when(gastoRepository.findAllGastos(pageRequest)).thenReturn(pageGasto);

		Gasto gastoDomain = new Gasto(gastoRepository, categoriaRepository);
		Page<GastoDTO> pageGastoDTO = gastoDomain.convertPageGastoToPageGastoDTO(pageGasto);
		
		Page<GastoDTO> gastosBase = gastoDomain.retrieveAllGastos(pageRequest);
		assertTrue(gastosBase.getContent().toString().equals(pageGastoDTO.getContent().toString() ));
	}

	@Test
	public void retrieveGasto() {
		Gasto gasto = new Gasto();
		gasto.setUsuario(new Usuario(1L, "Usuario 01"));
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(1L);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new Categoria(1L, "Categoria 01"));
		Optional<Gasto> gastoOp = Optional.of(gasto);
		Long id = 1L;
		when(gastoRepository.findById(id )).thenReturn(gastoOp);

		Gasto gastoDomain = new Gasto(gastoRepository, categoriaRepository);
		gastoDomain.setId(id);
		GastoDTO gastoDTO = gastoDomain.convertGastoToGastoDTO(gasto);
		Gasto gastoReturn = gastoDomain.retrieveGasto();
		GastoDTO gastoDTOReturn = gastoDomain.convertGastoToGastoDTO(gastoReturn);
		assertTrue(gastoDTOReturn.toString().equals(gastoDTO.toString()));

	}
	
	@Test
	public void retrieveGastoByUser() {
		Long id = 1L;
		PageRequest pageRequest = PageRequest.of(0, 4, new Sort(Sort.Direction.DESC,"data"));
		final Page<Gasto> page = Mockito.mock(Page.class);
	    when(page.getTotalElements()).thenReturn(8L);
	    when(page.getTotalPages()).thenReturn(2);
	    when(page.getNumber()).thenReturn(3);
	    when(page.getNumberOfElements()).thenReturn(5);
		when(gastoRepository.findByUsuarioIdOrderByDataDesc(id, pageRequest)).thenReturn(page);

		
		Gasto gastoDomain = new Gasto(gastoRepository, categoriaRepository);
		Page<Gasto> gastosReturn = gastoDomain.retrieveGastoByUser(id, pageRequest);
		assertTrue(gastosReturn.equals(page));
	}

	@Test
	public void retrieveGastoByUserDate() {
		Long id = 1L;
		String date = "16/11/2018";
		PageRequest pageRequest = PageRequest.of(0, 4, new Sort(Sort.Direction.DESC,"data"));
		final Page<Gasto> page = Mockito.mock(Page.class);
	    when(page.getTotalElements()).thenReturn(8L);
	    when(page.getTotalPages()).thenReturn(2);
	    when(page.getNumber()).thenReturn(3);
	    when(page.getNumberOfElements()).thenReturn(5);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date parsedDate;
		try {
			parsedDate = dateFormat.parse(date);
		    Timestamp dataInferior = new Timestamp(parsedDate.getTime());
		    System.out.println("timestamp=" + dataInferior);
		    Timestamp dataSuperior = new Timestamp(parsedDate.getTime() + (24*60*60*1000)-1);
		    System.out.println("timestamp=" + dataSuperior);
			when(gastoRepository.findByCodigousuarioOrderByDataDesc(id, dataInferior, dataSuperior, pageRequest)).thenReturn(page);

			Gasto gastoDomain = new Gasto(gastoRepository, categoriaRepository);
			Page<Gasto> gastosReturn = gastoDomain.retrieveGastoByUserDate(id, date, pageRequest);
			assertTrue(gastosReturn.equals(page));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}

	@Test
	public void createGasto() {
		Gasto gasto = new Gasto();
		gasto.setUsuario(new Usuario(1L, "Usuario 01"));
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(1L);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new Categoria(1L, "Categoria 01"));

		Gasto gastoDomain = new Gasto(gastoRepository, categoriaRepository);
		GastoDTO gastoDTO = gastoDomain.convertGastoToGastoDTO(gasto);
		gastoDomain.convertGastoDTOToGasto(gastoDTO);
		when(gastoRepository.save(gastoDomain)).thenReturn(gasto);
		Gasto gastoReturn = gastoDomain.createGasto();
		GastoDTO gastoDTOReturn = gastoDomain.convertGastoToGastoDTO(gastoReturn);
		assertTrue(gastoDTOReturn.toString().equals(gastoDTO.toString()));
	}
    
	@Test
	public void updateGasto() {
		long id = 1L;
		Gasto gasto = new Gasto();
		gasto.setUsuario(new Usuario(1L, "Usuario 01"));
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(1L);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new Categoria(1L, "Categoria 01"));

		Optional<Gasto> gastoOp = Optional.of(gasto);
		when(gastoRepository.findById(id)).thenReturn(gastoOp);

		Gasto gastoDomain = new Gasto(gastoRepository, categoriaRepository);
		GastoDTO gastoDTO = gastoDomain.convertGastoToGastoDTO(gasto);
		gastoDomain.convertGastoDTOToGasto(gastoDTO);
		when(gastoRepository.save(gastoDomain)).thenReturn(gasto);
		Gasto gastoReturn = gastoDomain.updateGasto();
		GastoDTO gastoDTOReturn = gastoDomain.convertGastoToGastoDTO(gastoReturn);
		
		assertTrue(gastoDTOReturn.toString().equals(gastoDTO.toString()));
	}
	
	@Test
	public void retrieveCategoria() {
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		categoria.setNome("Categoria 01");
		Optional<Categoria> categoriaOp = Optional.of(categoria);
		Long id = 1L;
		when(categoriaRepository.findById(id)).thenReturn(categoriaOp);

		Categoria categoriaDomain = new Categoria(categoriaRepository);
		categoriaDomain.setId(id);
		Categoria categoriaReturn = categoriaDomain.retrieveCategoria();
		CategoriaDTO categoriaDTOReturn = categoriaDomain.toDTO(categoriaReturn);
		CategoriaDTO categoriaDTO = categoriaDomain.toDTO(categoria);
		assertTrue(categoriaDTOReturn.toString().equals(categoriaDTO.toString()));
	}

	@Test
	public void retrieveCategorias() {
		String nome = "Categoria 01";
		PageRequest pageRequest = PageRequest.of(0, CATEGORIAS_PAGE_SIZE, new Sort(Sort.Direction.ASC,"nome"));
		final Page<Categoria> page = Mockito.mock(Page.class);
	    when(page.getTotalElements()).thenReturn(3L);
	    when(page.getTotalPages()).thenReturn(1);
	    when(page.getNumber()).thenReturn(1);
	    List<Categoria> categoriaList = new ArrayList<>();
	    categoriaList.add(new Categoria(1L, "Categoria 01"));
	    categoriaList.add(new Categoria(2L, "Categoria 02"));
	    categoriaList.add(new Categoria(3L, "Categoria 03"));
		when(page.getContent()).thenReturn(categoriaList);
	    Pageable pageable = PageRequest.of(0, 20, new Sort(Sort.Direction.ASC,"nome"));;
		when(page.getPageable()).thenReturn(pageable );
	    when(page.getNumberOfElements()).thenReturn(3);
		when(categoriaRepository.findByNome(nome, pageRequest)).thenReturn(page);
		
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("nome", "Categoria 01");

		Categoria categoriaDomain = new Categoria(categoriaRepository);
		Page<CategoriaDTO> pageCategoriaDTO = categoriaDomain.convertPageCategoriaToPageCategoriaDTO(page);
		Page<Categoria> categoriasReturn = categoriaDomain.retrieveCategorias(params);
		assertTrue(categoriasReturn.getContent().toString().equals(pageCategoriaDTO.getContent().toString() ));
	}

	@Test
	public void createCategoria() {
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		categoria.setNome("Categoria 01");
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setId(1L);
		categoriaDTO.setNome("Categoria 01");

		Categoria categoriaDomain = new Categoria(categoriaRepository);
		categoriaDomain.toDomain(categoriaDTO);
		when(categoriaRepository.save(categoriaDomain)).thenReturn(categoria);
		Categoria categoriaReturn = categoriaDomain.createCategoria();
		CategoriaDTO categoriaDTOReturn = categoriaDomain.toDTO(categoriaReturn);
		assertTrue(categoriaDTOReturn.toString().equals(categoriaDTO.toString()));
	}

	@Test
	public void updateCategoria() {
		long id = 1L;
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		categoria.setNome("Categoria 01");
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setId(1L);
		categoriaDTO.setNome("Categoria 01");

		Optional<Categoria> categoriaOp = Optional.of(categoria);
		when(categoriaRepository.findById(id)).thenReturn(categoriaOp);

		Categoria categoriaDomain = new Categoria(categoriaRepository);
		categoriaDomain.toDomain(categoriaDTO);
		when(categoriaRepository.save(categoriaDomain)).thenReturn(categoria);
		Categoria categoriaReturn = categoriaDomain.updateCategoria();
		CategoriaDTO categoriaDTOReturn = categoriaDomain.toDTO(categoriaReturn);
		assertTrue(categoriaDTOReturn.toString().equals(categoriaDTO.toString()));
	}

	private Gasto cloneGasto(Gasto gasto, String descricao) {
		idTeste = idTeste + 1;
		Gasto clone = new Gasto();
		clone.setId(idTeste);
		clone.setDescricao(descricao);
		clone.setValor(gasto.getValor());
		clone.setData(gasto.getData());
		clone.setCategoria(gasto.getCategoria());
		clone.setUsuario(gasto.getUsuario());
		return clone;
	}

	private CategoriaDTO cloneCategoria(CategoriaDTO categoria, String nome) {
		idTeste = idTeste + 1;
		CategoriaDTO clone = new CategoriaDTO();
		clone.setId(idTeste);
		clone.setNome(nome);
		return clone;
	}

}
