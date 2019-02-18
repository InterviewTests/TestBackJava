package br.com.adslima.repository;

import br.com.adslima.entity.Users;

public interface UsersRepository extends BaseRepository<Users, Integer> {
	Users findByUserName(String username);
}
