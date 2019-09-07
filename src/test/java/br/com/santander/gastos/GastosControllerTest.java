package br.com.santander.gastos;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.santander.gastos.model.Gasto;
import br.com.santander.gastos.repository.GastoRepository;
import br.com.santander.gastos.service.GastoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GastosControllerTest {

	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	private Gasto gasto;
	
	@Autowired
	private GastoService service;
	
	@MockBean
	private GastoRepository repository;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		
		gasto = new Gasto();
		gasto.setDescricao("Primeiro Gasto");
		gasto.setValor(new Double(1.0));
		gasto.setIdUsuario(Long.valueOf(1));
		gasto.setData(LocalDateTime.now());
	}
	
	@Test
	public void cadastrarGasto() {
		service.save(gasto);
		verify(repository).save(gasto);
	}
	
	@Test
	public void buscarGastos() {
		Long userId = Long.valueOf(1);
		List<Gasto> listaGastos = service.findByIdUsuarioOrderByDataDesc(userId);
		assertTrue(listaGastos.size() > 0);
	}
}
