/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.model.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.testbackjava.model.entity.Categoria;

/**
 * Classe responsável por realizar as operações de persistência da entidade Categoria.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 16:02:40
 * @version x.x
 */
@Repository
public interface ICategoriaRepository extends CassandraRepository<Categoria, UUID> {
	
	@AllowFiltering
	Optional<List<Categoria>> findByDescricaoContains(String descricao);
	
	@AllowFiltering
	Optional<Categoria> findByDescricao(String descricao);

}
