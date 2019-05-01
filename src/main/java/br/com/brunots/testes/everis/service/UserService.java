package br.com.brunots.testes.everis.service;

import java.util.List;

import br.com.brunots.testes.everis.entity.UserEntity;

public interface UserService {

	void save(UserEntity entity);
	
	List<UserEntity> listAll();

	UserEntity findByUsername(String username);

}
