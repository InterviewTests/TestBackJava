package br.com.santandertec.gestaodegastos.testes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.santandertec.gestaodegastos.dtos.GastoDTO;
import br.com.santandertec.gestaodegastos.models.Gasto;

public class CadastrarGastoServico {
	
	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		Random gerador = new Random();
		int valor = gerador.nextInt(1000) + 1;
		
		String uri = "http://localhost:8080/gestaodegastos/servico/gastos";
		
		GastoDTO gastoDTO = new GastoDTO();
		gastoDTO.setDescricao("Compras na Santa Branca");
		gastoDTO.setValor(String.valueOf(valor));
		gastoDTO.setCodigoUsuario("1");
		
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		gastoDTO.setData(formatador.format(new Date(Calendar.getInstance().getTimeInMillis())));
		
		ResponseEntity<Gasto> gastoEntity = restTemplate.postForEntity(uri, gastoDTO, Gasto.class);
		
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
