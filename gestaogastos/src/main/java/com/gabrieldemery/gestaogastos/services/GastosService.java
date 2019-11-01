package com.gabrieldemery.gestaogastos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrieldemery.gestaogastos.entities.Gasto;
import com.gabrieldemery.gestaogastos.repositories.GastosRepository;

@Service
public class GastosService {
	
	@Autowired
	private GastosRepository gastosRepository;
	
	@Autowired
	private CategoriasService categoriasService;

	public void inserir(Gasto gasto) {
		if( gasto.getCategoria() == null || gasto.getCategoria().isEmpty() ) {
			Optional<Gasto> gastoCategorizado = this.gastosRepository.findFirstByDescricaoAndCategoriaIsNotNull(gasto.getDescricao());
			if (gastoCategorizado.isPresent()) {
				gasto.setCategoria(gastoCategorizado.get().getCategoria());
			}
		}
		this.gastosRepository.save(gasto);
	}
	
	public void inserirLista(List<Gasto> gastos) {
		System.out.println(gastos.toString());
		gastos.forEach(gasto -> {
			this.inserir(gasto);
		});
	}
	
	public List<Gasto> listar(Optional<String> data) {
		List<Gasto> lista = new ArrayList<Gasto>();
		
		if (data.isPresent()) {
			lista = this.gastosRepository.findByData(data.get());
		} else {
			lista = this.gastosRepository.findAll();
		}
		
		return lista;
	}
	
	public Gasto detalhar(Long codigo) {
		return this.gastosRepository.findByCodigo(codigo);
	}
	
	public void categorizar(Long codigo, String categoria) throws Exception {
		Gasto gasto = this.gastosRepository.getOne(codigo);
		if ( gasto.getCategoria() != null && !gasto.getCategoria().isEmpty() ) {
			throw new Exception("Gasto já categorizado!");
		}
		gasto.setCategoria(categoria);
		this.gastosRepository.save(gasto);
		this.categoriasService.inserir(categoria);
	}
}
