package com.teste.gft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.gft.repositories.GastoRepository;

@Service
public class GastoService {
	@Autowired
	public GastoRepository gastoRepository;
}
