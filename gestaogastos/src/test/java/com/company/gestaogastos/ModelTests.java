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

import com.company.gestaogastos.domain.CategoriaDomain;
import com.company.gestaogastos.domain.GastoDomain;
import com.company.gestaogastos.domain.dto.CategoriaDTO;
import com.company.gestaogastos.domain.dto.GastoDTO;
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
		List<com.company.gestaogastos.domain.entity.Gasto> gastos = new ArrayList<com.company.gestaogastos.domain.entity.Gasto>();
		com.company.gestaogastos.domain.entity.Gasto gasto = new com.company.gestaogastos.domain.entity.Gasto();
		gasto.setUsuario(new com.company.gestaogastos.domain.entity.Usuario(1L, "Usuario 01"));

		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(idTeste);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new com.company.gestaogastos.domain.entity.Categoria(1L, "Categoria 01"));
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
		Page<com.company.gestaogastos.domain.entity.Gasto> pageGasto = new PageImpl<com.company.gestaogastos.domain.entity.Gasto>(gastos.subList(start.intValue(), end.intValue()), pageRequest, gastos.size());
		
		when(gastoRepository.findAllGastos(pageRequest)).thenReturn(pageGasto);

		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Page<GastoDTO> pageGastoDTO = gastoDomain.convertPageGastoToPageGastoDTO(pageGasto);
		
		Page<GastoDTO> gastosBase = gastoDomain.retrieveAllGastos(pageRequest);
		assertTrue(gastosBase.getContent().toString().equals(pageGastoDTO.getContent().toString() ));
	}

	@Test
	public void retrieveGasto() {
		com.company.gestaogastos.domain.entity.Gasto gasto = new com.company.gestaogastos.domain.entity.Gasto();
		gasto.setUsuario(new com.company.gestaogastos.domain.entity.Usuario(1L, "Usuario 01"));
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(1L);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new com.company.gestaogastos.domain.entity.Categoria(1L, "Categoria 01"));
		Optional<com.company.gestaogastos.domain.entity.Gasto> gastoOp = Optional.of(gasto);
		Long id = 1L;
		when(gastoRepository.findById(id )).thenReturn(gastoOp);

		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		gastoDomain.setId(id);
		GastoDTO gastoDTO = gastoDomain.convertGastoToGastoDTO(gasto);
		com.company.gestaogastos.domain.entity.Gasto gastoReturn = gastoDomain.retrieveGasto();
		GastoDTO gastoDTOReturn = gastoDomain.convertGastoToGastoDTO(gastoReturn);
		assertTrue(gastoDTOReturn.toString().equals(gastoDTO.toString()));

	}
	
	@Test
	public void retrieveGastoByUser() {
		Long id = 1L;
		PageRequest pageRequest = PageRequest.of(0, 4, new Sort(Sort.Direction.DESC,"data"));
		final Page<com.company.gestaogastos.domain.entity.Gasto> page = Mockito.mock(Page.class);
	    when(page.getTotalElements()).thenReturn(8L);
	    when(page.getTotalPages()).thenReturn(2);
	    when(page.getNumber()).thenReturn(3);
	    when(page.getNumberOfElements()).thenReturn(5);
		when(gastoRepository.findByUsuarioIdOrderByDataDesc(id, pageRequest)).thenReturn(page);

		
		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		Page<com.company.gestaogastos.domain.entity.Gasto> gastosReturn = gastoDomain.retrieveGastoByUser(id, pageRequest);
		assertTrue(gastosReturn.equals(page));
	}

	@Test
	public void retrieveGastoByUserDate() {
		Long id = 1L;
		String date = "16/11/2018";
		PageRequest pageRequest = PageRequest.of(0, 4, new Sort(Sort.Direction.DESC,"data"));
		final Page<com.company.gestaogastos.domain.entity.Gasto> page = Mockito.mock(Page.class);
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

			GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
			Page<com.company.gestaogastos.domain.entity.Gasto> gastosReturn = gastoDomain.retrieveGastoByUserDate(id, date, pageRequest);
			assertTrue(gastosReturn.equals(page));
		} catch (ParseException e) {
			assertTrue(false);
		}
	}

	@Test
	public void createGasto() {
		com.company.gestaogastos.domain.entity.Gasto gasto = new com.company.gestaogastos.domain.entity.Gasto();
		gasto.setUsuario(new com.company.gestaogastos.domain.entity.Usuario(1L, "Usuario 01"));
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(1L);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new com.company.gestaogastos.domain.entity.Categoria(1L, "Categoria 01"));

		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		GastoDTO gastoDTO = gastoDomain.convertGastoToGastoDTO(gasto);
		gastoDomain.convertGastoDTOToGasto(gastoDTO);
		when(gastoRepository.save(gasto)).thenReturn(gasto);
		com.company.gestaogastos.domain.entity.Gasto gastoReturn = gastoDomain.createGasto(gasto);
		GastoDTO gastoDTOReturn = gastoDomain.convertGastoToGastoDTO(gastoReturn);
		assertTrue(gastoDTOReturn.toString().equals(gastoDTO.toString()));
	}
    
	@Test
	public void updateGasto() {
		long id = 1L;
		com.company.gestaogastos.domain.entity.Gasto gasto = new com.company.gestaogastos.domain.entity.Gasto();
		gasto.setUsuario(new com.company.gestaogastos.domain.entity.Usuario(1L, "Usuario 01"));
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(1L);
		gasto.setDescricao("gasto 01");
		gasto.setCategoria(new com.company.gestaogastos.domain.entity.Categoria(1L, "Categoria 01"));

		Optional<com.company.gestaogastos.domain.entity.Gasto> gastoOp = Optional.of(gasto);
		when(gastoRepository.findById(id)).thenReturn(gastoOp);

		GastoDomain gastoDomain = new GastoDomain(gastoRepository, categoriaRepository);
		GastoDTO gastoDTO = gastoDomain.convertGastoToGastoDTO(gasto);
		gastoDomain.convertGastoDTOToGasto(gastoDTO);
		when(gastoRepository.save(gasto)).thenReturn(gasto);
		com.company.gestaogastos.domain.entity.Gasto gastoReturn = gastoDomain.updateGasto(gasto);
		GastoDTO gastoDTOReturn = gastoDomain.convertGastoToGastoDTO(gastoReturn);
		
		assertTrue(gastoDTOReturn.toString().equals(gastoDTO.toString()));
	}
	
	@Test
	public void retrieveCategoria() {
		com.company.gestaogastos.domain.entity.Categoria categoria = new com.company.gestaogastos.domain.entity.Categoria();
		categoria.setId(1L);
		categoria.setNome("Categoria 01");
		Optional<com.company.gestaogastos.domain.entity.Categoria> categoriaOp = Optional.of(categoria);
		Long id = 1L;
		when(categoriaRepository.findById(id)).thenReturn(categoriaOp);

		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		categoriaDomain.setId(id);
		com.company.gestaogastos.domain.entity.Categoria categoriaReturn = categoriaDomain.retrieveCategoria();
		CategoriaDTO categoriaDTOReturn = categoriaDomain.toCategoriaDTO(categoriaReturn);
		CategoriaDTO categoriaDTO = categoriaDomain.toCategoriaDTO(categoria);
		assertTrue(categoriaDTOReturn.toString().equals(categoriaDTO.toString()));
	}

	@Test
	public void retrieveCategorias() {
		String nome = "Categoria 01";
		PageRequest pageRequest = PageRequest.of(0, CATEGORIAS_PAGE_SIZE, new Sort(Sort.Direction.ASC,"nome"));
		final Page<com.company.gestaogastos.domain.entity.Categoria> page = Mockito.mock(Page.class);
	    when(page.getTotalElements()).thenReturn(3L);
	    when(page.getTotalPages()).thenReturn(1);
	    when(page.getNumber()).thenReturn(1);
	    List<com.company.gestaogastos.domain.entity.Categoria> categoriaList = new ArrayList<>();
	    categoriaList.add(new com.company.gestaogastos.domain.entity.Categoria(1L, "Categoria 01"));
	    categoriaList.add(new com.company.gestaogastos.domain.entity.Categoria(2L, "Categoria 02"));
	    categoriaList.add(new com.company.gestaogastos.domain.entity.Categoria(3L, "Categoria 03"));
		when(page.getContent()).thenReturn(categoriaList);
	    Pageable pageable = PageRequest.of(0, 20, new Sort(Sort.Direction.ASC,"nome"));;
		when(page.getPageable()).thenReturn(pageable );
	    when(page.getNumberOfElements()).thenReturn(3);
		when(categoriaRepository.findByNome(nome, pageRequest)).thenReturn(page);
		
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("nome", "Categoria 01");

		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		Page<CategoriaDTO> pageCategoriaDTO = categoriaDomain.toPageCategoriaDTO(page);
		Page<com.company.gestaogastos.domain.entity.Categoria> categoriasReturn = categoriaDomain.retrieveCategorias(params);
		assertTrue(categoriasReturn.getContent().toString().equals(pageCategoriaDTO.getContent().toString() ));
	}

	@Test
	public void createCategoria() {
		com.company.gestaogastos.domain.entity.Categoria categoria = new com.company.gestaogastos.domain.entity.Categoria();
		categoria.setId(1L);
		categoria.setNome("Categoria 01");
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setId(1L);
		categoriaDTO.setNome("Categoria 01");

		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		when(categoriaRepository.save(categoria)).thenReturn(categoria);
		com.company.gestaogastos.domain.entity.Categoria categoriaReturn = categoriaDomain.createCategoria(categoria);
		CategoriaDTO categoriaDTOReturn = categoriaDomain.toCategoriaDTO(categoriaReturn);
		assertTrue(categoriaDTOReturn.toString().equals(categoriaDTO.toString()));
	}

	@Test
	public void updateCategoria() {
		long id = 1L;
		com.company.gestaogastos.domain.entity.Categoria categoria = new com.company.gestaogastos.domain.entity.Categoria();
		categoria.setId(1L);
		categoria.setNome("Categoria 01");
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setId(1L);
		categoriaDTO.setNome("Categoria 01");

		Optional<com.company.gestaogastos.domain.entity.Categoria> categoriaOp = Optional.of(categoria);
		when(categoriaRepository.findById(id)).thenReturn(categoriaOp);

		CategoriaDomain categoriaDomain = new CategoriaDomain(categoriaRepository);
		when(categoriaRepository.save(categoria)).thenReturn(categoria);
		com.company.gestaogastos.domain.entity.Categoria categoriaReturn = categoriaDomain.updateCategoria(categoria);
		CategoriaDTO categoriaDTOReturn = categoriaDomain.toCategoriaDTO(categoriaReturn);
		assertTrue(categoriaDTOReturn.toString().equals(categoriaDTO.toString()));
	}

	private com.company.gestaogastos.domain.entity.Gasto cloneGasto(com.company.gestaogastos.domain.entity.Gasto gasto, String descricao) {
		idTeste = idTeste + 1;
		com.company.gestaogastos.domain.entity.Gasto clone = new com.company.gestaogastos.domain.entity.Gasto();
		clone.setId(idTeste);
		clone.setDescricao(descricao);
		clone.setValor(gasto.getValor());
		clone.setData(gasto.getData());
		clone.setCategoria(gasto.getCategoria());
		clone.setUsuario(gasto.getUsuario());
		return clone;
	}
}
