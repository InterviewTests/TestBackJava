package br.com.santander.gastos.service;

import java.util.Optional;

import br.com.santander.gastos.model.User;

public interface UserService {

	String getUsuarioLogado();

	Optional<User> findByEmail(String userName);

}
