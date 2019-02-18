package br.com.adslima.service;

import br.com.adslima.entity.Users;

public interface UserService {
	Users findByUsername(String username);

	Users findByUserId(Integer userId);
}
