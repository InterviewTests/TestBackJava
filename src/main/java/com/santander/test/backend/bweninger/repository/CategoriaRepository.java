package com.santander.test.backend.bweninger.repository;

import com.santander.test.backend.bweninger.model.Categoria;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by BWeninger on 10/01/2019.
 */
@Repository
public interface CategoriaRepository extends CassandraRepository<Categoria, String>{

    @Query("Select * from Categorias Where descricao = :descricao")
    List<Categoria> findAllByDescricao(@Param("descricao") String descricao);

    @Query("Select * from Caegorias where cpf_usuario = :cpfUsuario")
    List<Categoria> findByCpfUsuario(@Param("cpfUsuario") String cpf);

    @Query("Select * from Categorias Where descricao = :descricao AND cpf_usuario = :cpfUsuario")
    Optional<Categoria> findByDescricaoAndCpfUsuario(@Param("descricao") String descricao, @Param("cpfUsuario") String cpf);

}
