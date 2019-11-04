package com.gabrieldemery.gestaogastos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrieldemery.gestaogastos.entities.Gasto;
import com.gabrieldemery.gestaogastos.entities.Usuario;
import com.gabrieldemery.gestaogastos.repositories.GastosRepository;

@Service
public class GastosService {
	
	@Autowired
	private GastosRepository gastosRepository;
	
	@Autowired
	private CategoriasService categoriasService;

	public Gasto inserir(Gasto gasto) {
		if( gasto.getCategoria() == null || gasto.getCategoria().isEmpty() ) {
			Optional<Gasto> gastoCategorizado = this.gastosRepository.findFirstByDescricaoAndCategoriaIsNotNull(gasto.getDescricao());
			if (gastoCategorizado.isPresent()) {
				gasto.setCategoria(gastoCategorizado.get().getCategoria());
			}
		}
		return this.gastosRepository.save(gasto);
	}
	
	public void inserirLista(List<Gasto> gastos) {
		System.out.println(gastos.toString());
		gastos.forEach(gasto -> {
			this.inserir(gasto);
		});
	}
	
	public List<Gasto> listar(Usuario usuario, Optional<String> data) {
		List<Gasto> lista = new ArrayList<Gasto>();
		
		if (data.isPresent()) {
			lista = this.gastosRepository.findByCodigousuarioAndData(usuario.getCodigo(), data.get());
		} else {
			lista = this.gastosRepository.findByCodigousuario(usuario.getCodigo());
		}
		
		return lista;
	}
	
	public Gasto detalhar(Usuario usuario, Long codigo) {
		return this.gastosRepository.findByCodigoAndCodigousuario(codigo, usuario.getCodigo());
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
