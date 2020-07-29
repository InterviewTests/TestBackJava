/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.model.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.santander.testbackjava.model.entity.GastoCartao;


/**
 * Classe responsável por realizar as operações de persistência da entidade GastoCartao.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 16:02:40
 * @version x.x
 */
@Repository
public interface IGastoCartaoRepository extends CassandraRepository<GastoCartao, UUID> {

	@AllowFiltering
	Optional<List<GastoCartao>> findByCodigoUsuarioAndDataLessThanEqual(Long codigoUsuario, Date data, Pageable pageable);
	
	@AllowFiltering
	Optional<List<GastoCartao>> findByCodigoUsuarioAndData(Long codigoUsuario, Date data, Pageable pageable);
	
	@AllowFiltering
	Optional<GastoCartao> findById(UUID id);
	
	@AllowFiltering
	Optional<List<GastoCartao>> findByDescricao(String descricao);
	

}
