package br.com.santander.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.api.model.Gasto;
import br.com.santander.api.repository.GastosRepository;

@Service
public class GastosService{

	@Autowired
	private GastosRepository gastosRepository;

//	public void incluirGasto(Gasto gasto){
//		gastosRepository.incluirGasto(gasto);
//	}
	
	public Iterable<Gasto> listarTodos(){
		return gastosRepository.findAll();
	}
	
	public void delete(Long idGasto){
		gastosRepository.delete(idGasto);;
	}

	public List<Gasto> findByCodigoUsuario(Long codigoUsuario){
		return gastosRepository.findByCodigoUsuario(codigoUsuario);
	}
	
	public List<Gasto> pesquisarGastosPorDataGasto(Long codigoUsuario, LocalDate dataGasto){
		return gastosRepository.findByDataGasto(codigoUsuario, dataGasto);
	}

	//	public List<Gasto> pesquisarGastosUltimosCincoSegundosPorIdCliente(Long idCliente, LocalDateTime data){
//		return gastosRepository.pesquisarGastosUltimosCincoSegundosPorIdCliente(idCliente, data);
//	}
//	
	
}
