package com.santander.gastos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.gastos.domain.Categoria;
import com.santander.gastos.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscarPorId(Integer id) {
		
		Optional<Categoria> cat = repo.findById(id);
			
		return cat.orElse(null);
	}

	public List<Categoria> findAll() {
		 
		return repo.findAll();
	}
	
}
