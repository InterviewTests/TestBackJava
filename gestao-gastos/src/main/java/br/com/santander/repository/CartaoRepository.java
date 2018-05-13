package br.com.santander.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.santander.model.Cartao;
import br.com.santander.model.GastoPorCartao;

/**
 * Repositório de Cartões
 * @author AntonioJolvino
 *
 */
public interface CartaoRepository extends CrudRepository<Cartao, Long>{
	
	@Query("SELECT * FROM cartoes WHERE numerocartao = ?0")
	Optional<Cartao> findById(Long numeroCartao);
	
	@Query("SELECT * FROM cartoes WHERE codigousuario = ?0 ALLOW FILTERING")
	List<GastoPorCartao> findByCodigoUsuario(Long codigoUsuario);
}
