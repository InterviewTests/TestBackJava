package br.com.brunots.testes.everis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brunots.testes.everis.dao.GastosDAO;

@Component
public class GastosServiceImpl implements GastosService {

	@SuppressWarnings("unused")
	@Autowired
	private GastosDAO dao;
	
}
