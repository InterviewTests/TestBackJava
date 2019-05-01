package br.com.brunots.testes.everis.dao;

import java.util.List;

import br.com.brunots.testes.everis.entity.UserEntity;

public interface UserDAO {

	void save(UserEntity entity);

	List<UserEntity> listAll();

	UserEntity fingByUsername(String username);

}
