package com.gabrieldemery.gestaogastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrieldemery.gestaogastos.entities.Categoria;
import com.gabrieldemery.gestaogastos.repositories.CategoriasRepository;

@Service
public class CategoriasService {
	
	@Autowired
	private CategoriasRepository categoriasRepository;
	
	public void inserir(String nome) {
		Optional<List<Categoria>> lista = this.categoriasRepository.findByNome(nome);
		if ( !lista.isPresent() ) {
			Categoria categoria = new Categoria();
			categoria.setNome(nome);
			this.categoriasRepository.save(categoria);
		}
	}
	
	public List<Categoria> listar(String nome) {
		return this.categoriasRepository.findByNomeContains(nome);
	}
	
}
