package br.com.brunots.testes.everis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brunots.testes.everis.dao.UserDAO;
import br.com.brunots.testes.everis.entity.UserEntity;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	
	@Override
	public void save(UserEntity entity) {
		dao.save(entity);
	}
	
	@Override
	public List<UserEntity> listAll() {
		return dao.listAll();
	}
	
	@Override
	public UserEntity findByUsername(String username) {
		return dao.fingByUsername(username);
	}
	
}
