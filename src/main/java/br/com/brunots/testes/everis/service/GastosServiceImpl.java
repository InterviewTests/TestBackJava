package br.com.brunots.testes.everis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brunots.testes.everis.dao.GastosDAO;
import br.com.brunots.testes.everis.entity.GastoEntity;

@Component
public class GastosServiceImpl implements GastosService {

	@Autowired
	private GastosDAO dao;
	
	@Override
	public void save(GastoEntity entity) {
		dao.save(entity);
	}
	
	@Override
	public List<GastoEntity> listAll() {
		return dao.listAll();
	}
	
}
