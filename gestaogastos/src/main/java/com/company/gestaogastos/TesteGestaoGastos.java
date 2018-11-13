package com.company.gestaogastos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.company.gestaogastos.domain.entity.Categoria;
import com.company.gestaogastos.domain.entity.Gasto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TesteGestaoGastos {

	static final String URL_GASTOS = "http://localhost:8080/gastos";
	static final String URL_GASTOS_2 = "http://localhost:8080/gastos";
	static final String URL_CATEGORIAS = "http://localhost:8080/categorias";
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
	
	public TesteGestaoGastos() {

	}
	
	public static void main(String[] args) {
		
		insertCategoria("Categoria 01");
		insertCategoria("Categoria 02");
		insertCategoria("Categoria 03");
		
		loadGastosEcategorizacaoAutomaticaGastos();
		
		categorizacaoGasto();

		listagemGastos();
		
		listagemGastosDiaEspecifico();
		
		sugestaoCategoria("GORia 01");
	}
	
	public static Boolean sugestaoCategoria(String categoriaSearch) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.getForEntity(URL_CATEGORIAS + "/nomes/" + categoriaSearch, String.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("sugestaoCategoria ================ Ok");
			System.out.println(response.getBody());
		} else {
			System.out.println("sugestaoCategoria ================ NOk");
		}

		return true;
	}
	
	public static Boolean listagemGastos() {
		RestTemplate restTemplate = new RestTemplate();

		String codigousuario = "1";		
		ResponseEntity<String> response = restTemplate.getForEntity(URL_GASTOS + "/users/" + codigousuario, String.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("listagemGastos ================ Ok");
			System.out.println(response.getBody());
		} else {
			System.out.println("listagemGastos ================ NOk");
		}

		return true;
	}
	
	public static Boolean listagemGastosDiaEspecifico() {
		RestTemplate restTemplate = new RestTemplate();

		String codigousuario = "1";		
		String dateFilter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
		ResponseEntity<String> response = restTemplate.getForEntity(URL_GASTOS + "/users/" + codigousuario + "/" + dateFilter, String.class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("listagemGastosDiaEspecifico ================ Ok ");
			System.out.println(response.getBody());
		} else {
			System.out.println("listagemGastosDiaEspecifico ================ NOk");
		}

		return true;
	}
	
	public static Boolean categorizacaoGasto() {
		RestTemplate restTemplate = new RestTemplate();

		// Update Gasto
		Gasto gasto = new Gasto();
		gasto.setCodigousuario(1);
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		gasto.setId(2L);
		gasto.setDescricao("UPDATE GASTO 111111");
		gasto.setCategoria(new Categoria(1L, "Categoria 01"));
	    //final String uri = URL_GASTOS + "/{id}";
	    final String uri = URL_GASTOS;
	    Map<String, String> params = new HashMap<String, String>();
	    //params.put("id", "6");
	    restTemplate = new RestTemplate();
	    restTemplate.put ( uri, gasto, params);

		return true;
	}
	
	public static Boolean insertCategoria(String categoriaNome) {
		RestTemplate restTemplate = new RestTemplate();
		
		// Insert Categoria
		Categoria categoria01 = new Categoria();
		//categoria01.setId(1L);
		categoria01.setNome(categoriaNome);
        String gastoJson = gson.toJson(categoria01);
		System.out.println(gastoJson);
		String requestJson = gastoJson;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		String answer = restTemplate.postForObject(URL_CATEGORIAS, entity, String.class);
		System.out.println(answer);
		
		return true;
	}

	public static Boolean insertGastos(Gasto gasto) {
		RestTemplate restTemplate = new RestTemplate();
		
        String requestJson = gson.toJson(gasto);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		String answer = restTemplate.postForObject(URL_GASTOS_2, entity, String.class);
		System.out.println(answer);
		
		return true;
	}

//	private static Boolean insertGastos2(Gasto gasto) {
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.postForObject(URL_GASTOS, gasto, Gasto.class);
//		return true;
//	}

	public static Boolean loadGastosEcategorizacaoAutomaticaGastos() {
		long deltaTempo = 8*60*60*1000;
		// Insert gastos
		Gasto gasto = new Gasto();
		gasto.setCodigousuario(1);
		gasto.setDescricao("gasto 1");
		gasto.setCategoria(new Categoria(null, "Categoria 01"));
		// gasto.setCategoria(null);
		gasto.setData(new Timestamp(System.currentTimeMillis()));
		gasto.setValor(new BigDecimal("10.5"));
		insertGastos(gasto);
		// ----------
        gasto.setDescricao("gasto 2");
        gasto.setCategoria(new Categoria(null, "Categoria 04"));
        gasto.setData(new Timestamp(System.currentTimeMillis() + (deltaTempo)));
		insertGastos(gasto);
		// ----------
        gasto.setDescricao("gasto 3");
        gasto.setCategoria(new Categoria(null, "Categoria 01"));
        gasto.setData(new Timestamp(System.currentTimeMillis() + (2 * deltaTempo)));
		insertGastos(gasto);
		// ----------
        gasto.setDescricao("gasto 4");
        gasto.setCategoria(null);
        gasto.setData(new Timestamp(System.currentTimeMillis() + (18 * deltaTempo)));
		insertGastos(gasto);
		// ----------
        gasto.setDescricao("gasto 1");
        gasto.setData(new Timestamp(System.currentTimeMillis() + (4 * deltaTempo)));
		insertGastos(gasto);
		// ----------
        gasto.setDescricao("gasto 2");
        gasto.setData(new Timestamp(System.currentTimeMillis() + (5 * deltaTempo)));
		insertGastos(gasto);
		// ----------
        gasto.setDescricao("gasto 2");
        gasto.setData(new Timestamp(System.currentTimeMillis() + (6 * deltaTempo)));
		insertGastos(gasto);
		// ----------
        gasto.setDescricao("gasto 8");
        gasto.setData(new Timestamp(System.currentTimeMillis() + (7 * deltaTempo)));
		insertGastos(gasto);
		// ----------
        gasto.setDescricao("gasto 3");
        gasto.setData(new Timestamp(System.currentTimeMillis() + (8 * deltaTempo)));
		insertGastos(gasto);
		// ----------
        gasto.setDescricao("gasto 10");
        gasto.setData(new Timestamp(System.currentTimeMillis() + (9 * deltaTempo)));
		insertGastos(gasto);
		// ----------
		gasto.setDescricao("despesas 1");
        gasto.setCodigousuario(2);
		insertGastos(gasto);
		
		return true;
	}
}
