package gestaogasto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
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

import gestaogasto.model.Gasto;
import gestaogasto.service.GastoService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class GestaoGastosApplicationTests {
	
	@MockBean
	private GastoService mockGastoService;
	private static Gasto gasto1;
	private static Gasto gasto2;

	
	@Before
	public void init() {
		
		MockitoAnnotations.initMocks(this);
		
		LocalDate hoje = LocalDate.now();
		gasto1 = new Gasto("5c366e0da094820f30d104eb","POSTO SHELL LTDA", 127.00, 1223, hoje,"7a5f28cc3178058b0fafe1d1");
		gasto2 = new Gasto("5c35f7cf09f00441f0cd164c", "PHARMACIA BRASILIA LTDA", 150.00, 123456, hoje, "5c35055a4be10a0394fda3c4");
		
		when(mockGastoService.listaGastosMaisAtuais()).thenReturn(Arrays.asList(gasto1, gasto2));
	
	}
	
	
	@Test
	public void testeListaGastosAcessoUri() throws URISyntaxException {
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:8080/santander/gasto/maisatuais";
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	    
	    assertEquals(200, result.getStatusCodeValue());
	    assertEquals(true, result.getBody().contains("data"));
	}
	
	@Test
	public void testeListaTodosGastos() {
		
		List<Gasto> gastos = mockGastoService.listaGastosMaisAtuais();
		assertNotNull(gastos);
		assertEquals(2, gastos.size());
	}

}

