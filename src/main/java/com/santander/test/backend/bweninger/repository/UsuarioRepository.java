package com.santander.test.backend.bweninger.repository;

import com.santander.test.backend.bweninger.model.Usuario;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by BWeninger on 10/01/2019.
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    @Query("Select u from Usuario u where u.cpf = :cpf and senha = :senha")
    Optional<Usuario> autenticar(@Param("cpf") String cpf, @Param("senha") String senha);


}
