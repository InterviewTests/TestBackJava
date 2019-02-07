package br.com.gestao.gastos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao.gastos.model.Gastos;
import br.com.gestao.gastos.repository.GastosRepository;

@Service
public class GastosServiceImpl implements GastosService {

	private GastosRepository gastosRepository;
	
	@Autowired
	public GastosServiceImpl(GastosRepository gastosRepository){
			this.gastosRepository = gastosRepository;
	}
	
	@Override
	public List<Gastos> listaDeGastos(int codigousuario) {
		return gastosRepository.findAllByCodigoUsuario(codigousuario);
	}
	
	@Override
	public Gastos save(Gastos gastos) {
		gastosRepository.save(gastos);
		return null;
	}
	
}
