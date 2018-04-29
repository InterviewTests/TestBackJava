package br.com.santander.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.api.model.Gasto;
import br.com.santander.api.repository.GastosRepository;

@Service
public class GastosService{

	@Autowired
	private GastosRepository gastosRepository;

	public Gasto insert(Gasto gasto) {
		return gastosRepository.save(gasto);
	}
	
	public void delete(Long idGasto){
		gastosRepository.delete(idGasto);;
	}

	public Iterable<Gasto> listarGastosPorIdCliente(Long idCliente){
		return gastosRepository.pesquisarGastosPorIdCliente(idCliente);
	}
	
	public List<Gasto> pesquisarGastosUltimosCincoSegundosPorIdCliente(Long idCliente, LocalDateTime data){
		return gastosRepository.pesquisarGastosUltimosCincoSegundosPorIdCliente(idCliente, data);
	}
	
	public List<Gasto> pesquisarGastosIntervaloDatasPorIdCliente(Long idCliente, LocalDateTime dataInicio, LocalDateTime dataFim){
		return gastosRepository.pesquisarGastosIntervaloDatasPorIdCliente(idCliente, dataInicio, dataFim);
	}
	
}
