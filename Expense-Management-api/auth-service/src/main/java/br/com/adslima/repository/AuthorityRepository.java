package br.com.adslima.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adslima.model.Authority;

/**
 * 
 * @author andrews.silva
 *
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {

	Authority findByName(String name);

}