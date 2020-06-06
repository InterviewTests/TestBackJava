package com.teste.gft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.gft.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
