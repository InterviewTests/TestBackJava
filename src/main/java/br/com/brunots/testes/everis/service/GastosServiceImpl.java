package br.com.brunots.testes.everis.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brunots.testes.everis.dao.GastosDAO;
import br.com.brunots.testes.everis.entity.CategoriaEntity;
import br.com.brunots.testes.everis.entity.GastoEntity;
import br.com.brunots.testes.everis.entity.UserEntity;

@Component
public class GastosServiceImpl implements GastosService {

	@Autowired
	private GastosDAO dao;
	@Autowired
	private UserService userService;
	
	@Override
	public void save(GastoEntity entity) {
		dao.save(entity);
	}
	
	@Override
	public List<GastoEntity> listAll() {
		return dao.listAll();
	}

	@Override
	public List<GastoEntity> listAllByName(String name) {
		UserEntity userEntity = userService.findByUsername(name);
		return dao.listAllByCodigousuario(userEntity.getCodigousuario());
	}

	@Override
	public List<GastoEntity> listAllByNameWithDate(String name, Date date) {
		UserEntity userEntity = userService.findByUsername(name);
		return dao.listAllByCodigousuarioWithDate(userEntity.getCodigousuario(), date);
	}

	@Override
	public void incluirCategoria(String gastoId, CategoriaEntity categoria) {
		GastoEntity gastoEntity = dao.getById(Long.valueOf(gastoId));
		gastoEntity.setCategoria(categoria);
		dao.save(gastoEntity);
	}

	@Override
	public GastoEntity getById(String gastoId) {
		return dao.getById(Long.valueOf(gastoId));
	}

	@Override
	public List<CategoriaEntity> listarCategorias(String startsWith) {
		return dao.listarCategorias(startsWith);
	}
	
}
