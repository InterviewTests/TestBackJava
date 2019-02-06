package br.com.gestao.gastos.service;

import java.util.ArrayList;
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
	public List<Gastos> listAll() {
		List<Gastos> gastos = new ArrayList<>();
		gastosRepository.findAll().forEach(gastos::add);
		return gastos;
	}

	@Override
	public Gastos findByCodigoUsuario(String codigousuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Gastos save(Gastos gastos) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
