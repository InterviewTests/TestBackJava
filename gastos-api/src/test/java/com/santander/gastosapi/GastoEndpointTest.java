package com.santander.gastosapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.santander.gastosapi.model.Gasto;
import com.santander.gastosapi.repository.GastosRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GastoEndpointTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@MockBean
	private GastosRepository gastoRepository;
		
	@TestConfiguration
	static class Config {
		
		@Bean
		public RestTemplateBuilder restTemplateBuilder(){
			return new RestTemplateBuilder().basicAuthorization("sistema01", "asd321");
		}
	}
	
	@Test
	public void listGastosIncorretUser(){
		testRestTemplate = testRestTemplate.withBasicAuth("cliente02", "asd123");
		ResponseEntity<String> response = testRestTemplate.getForEntity("/v1/protected/gastos/listagem", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void listGastosCorretUser() throws ParseException{
		List<Gasto> gastos = Arrays.asList(
			new Gasto("id01", "descricao gasto 1", 1.31, 1, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45")), 
			new Gasto("id02", "descricao gasto 2", 2.31, 2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45"))
			);
		testRestTemplate = testRestTemplate.withBasicAuth("cliente01", "asd123");
		BDDMockito.when(gastoRepository.findAll()).thenReturn(gastos);
		ResponseEntity<Gasto[]> response = testRestTemplate.getForEntity("/v1/protected/gastos/listagem", Gasto[].class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
		Assertions.assertThat(response.getBody().length).isEqualTo(2);
	}
	
	
	@Test
	public void filtroGastosEndpoint() throws ParseException{
		List<Gasto> gastos = Arrays.asList(
			new Gasto("id01", "descricao gasto 1", 1.31, 1, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("25/12/2015 10:11:32")),
			new Gasto("id02", "descricao gasto 2", 2.31, 2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2020 12:11:45")),
			new Gasto("id03", "descricao gasto 2", 2.31, 2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2020 14:15:00")),
			new Gasto("id04", "descricao gasto 3", 3.31, 3, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("11/07/2017 12:11:45"))
			);
		
		testRestTemplate = testRestTemplate.withBasicAuth("cliente01", "asd123");
		BDDMockito.when(gastoRepository.findAll()).thenReturn(gastos);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("filtro", "01/01/2020");
		
        ResponseEntity<Gasto[]> responseEntity = testRestTemplate.exchange("/v1/protected/gastos/listagem/filtro", HttpMethod.GET, new HttpEntity<>(headers), Gasto[].class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Assertions.assertThat(responseEntity.getBody().length).isEqualTo(2);
	}
	
	@Test
	public void categorizacaoEndpoint() throws ParseException, JsonProcessingException {
		List<Gasto> gastos = Arrays.asList(
				new Gasto("id01", "descricao gasto 1", 1.31, 1, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("25/12/2015 10:11:32")),
				new Gasto("id02", "descricao gasto 2", 2.31, 2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2020 12:11:45")),
				new Gasto("id03", "descricao gasto 2", 2.31, 2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2020 14:15:00")),
				new Gasto("id04", "descricao gasto 3", 3.31, 3, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("11/07/2017 12:11:45"))
				);
			
		testRestTemplate = testRestTemplate.withBasicAuth("cliente01", "asd123");
		BDDMockito.when(gastoRepository.findOne("id03")).thenReturn(gastos.get(2));
		BDDMockito.when(gastoRepository.findAll()).thenReturn(gastos);
		
		Assertions.assertThat(gastos.get(0).getCategoria()).isNull();
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("categoria", "Categoria 025");
        headers.set("gastoID", "id03");
        
        // categoriza um gasto específico
        String requestJson = ow.writeValueAsString("Categoria 025"); 
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/v1/protected/gastos/categoria", HttpMethod.PUT, entity, Void.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        
        // Verifica se o gasto foi categorizado
        BDDMockito.when(gastoRepository.findAll()).thenReturn(gastos);
		ResponseEntity<Gasto[]> response = testRestTemplate.getForEntity("/v1/protected/gastos/listagem", Gasto[].class);
		Assertions.assertThat(response.getBody()[2].getCategoria()).isNotNull();
		Assertions.assertThat(response.getBody()[2].getCategoria()).isEqualTo("Categoria 025");
	}
	
	@Test
	public void gastosPorCartaoEndpoint() throws ParseException, JsonProcessingException {
		List<String> categorias = Arrays.asList(
				"Categoria01",
				"Categoria02",
				"Categoria03",
				"Categoria04"
				);
		
		List<Gasto> gastos = Arrays.asList(
				new Gasto("id01", "descricao gasto 1", 1.31, 1, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("25/12/2015 10:11:32"), categorias.get(0)),
				new Gasto("id02", "descricao gasto 2", 2.31, 2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2020 12:11:45"), categorias.get(1)),
				new Gasto("id03", "descricao gasto 3", 2.31, 2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2020 14:15:00"), categorias.get(2)),
				new Gasto("id04", "descricao gasto 4", 3.31, 3, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("11/07/2017 12:11:45"), categorias.get(3))
				);
		
		Gasto novoGasto = new Gasto("descricao gasto 3", 4.2, 2, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("11/07/2017 12:11:45"));

		BDDMockito.when(gastoRepository.findAll()).thenReturn(gastos);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String requestJson = ow.writeValueAsString(novoGasto); 
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        
        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/v1/accredited/gastos/cartao", HttpMethod.POST, entity, Void.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void listSugestaoCategoria() throws ParseException{
		List<Gasto> gastos = Arrays.asList(
			new Gasto("id01", "descricao gasto 1", 1.31, 1, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45"), "Categoria X"), 
			new Gasto("id01", "descricao gasto 1", 1.31, 1, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45"), "Categoria X"),
			new Gasto("id01", "descricao gasto 1", 1.31, 1, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45"), "Categoria X"),
			new Gasto("id01", "descricao gasto 1", 1.31, 1, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45"), "Categoria Y"),
			new Gasto("id01", "descricao gasto 1", 1.31, 1, new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("21/04/2019 12:11:45"), "Categoria Y")
			);
		testRestTemplate = testRestTemplate.withBasicAuth("cliente01", "asd123");
		BDDMockito.when(gastoRepository.findAll()).thenReturn(gastos);
		ResponseEntity<String[]> response = testRestTemplate.getForEntity("/v1/protected/gastos/categoria/sugestao", String[].class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
		Assertions.assertThat(response.getBody().length).isEqualTo(2);
	}
	
	
}
