package com.santander.gastos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.gastos.domain.Movimento;
import com.santander.gastos.repositories.MovimentoRepository;

@Service
public class MovimentoService {
	
	@Autowired
	private MovimentoRepository repo;
	
	public Movimento buscarPorId(Integer id) {
		
		Optional<Movimento> mov = repo.findById(id);
			
		return mov.orElse(null);
	}
	
}
