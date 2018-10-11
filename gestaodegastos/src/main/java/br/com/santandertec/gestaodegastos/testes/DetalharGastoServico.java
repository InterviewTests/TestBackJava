package br.com.santandertec.gestaodegastos.testes;

import java.text.SimpleDateFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.santandertec.gestaodegastos.models.Gasto;

public class DetalharGastoServico {
	
	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Integer id = new Integer(9);
		String uri = "http://localhost:8080/gestaodegastos/servico/gastos/" + id;
		
		ResponseEntity<Gasto> gastoEntity = restTemplate.getForEntity(uri, Gasto.class);
		
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		System.out.println("***** [GASTO] *****");
		System.out.println("ID: " + gastoEntity.getBody().getId());
		System.out.println("DESCRIÇÃO: " + gastoEntity.getBody().getDescricao());
		System.out.println("VALOR: " + gastoEntity.getBody().getValor());
		System.out.println("USUÁRIO: " + gastoEntity.getBody().getUsuario().getNome());
		System.out.println("DATA: " + formatador.format(gastoEntity.getBody().getData()));
		if (gastoEntity.getBody().getCategoria() != null) {
			System.out.println("CATEGORIA: " + gastoEntity.getBody().getCategoria().getNomeCategoria());
		}
	}

}
