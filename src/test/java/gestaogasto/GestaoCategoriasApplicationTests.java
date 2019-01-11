package gestaogasto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import gestaogasto.model.Categoria;
import gestaogasto.service.CategoriaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GestaoCategoriasApplicationTests {
	
	@MockBean
	private CategoriaService mockCategoriaService;
	private static Categoria cat1;
	private static Categoria cat2;
	
	@Before
	public void init() {
		
		MockitoAnnotations.initMocks(this);
		
		mockCategoriaService = mock(CategoriaService.class);
		
		cat1 = new Categoria("7a5f28cc3178058b0fafe1d1", "Combustível");
		cat2 = new Categoria("5c35055a4be10a0394fda3c4", "Medicamentos");
		
		when(mockCategoriaService.listaCategorias()).thenReturn(Arrays.asList(cat1, cat2));

	}
	
	
	@Test
	public void testeListarTodasCategoriasAcessoUri() throws URISyntaxException
	{
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:8080/santander/categoria/all";
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	    
	    assertEquals(200, result.getStatusCodeValue());
	    assertEquals(true, result.getBody().contains("data"));
	}
	
	@Test
	public void testeListaTodasCategorias() {
		
		List<Categoria> categorias = mockCategoriaService.listaCategorias();
		assertNotNull(categorias);
		assertEquals(2, categorias.size());

	}

}
