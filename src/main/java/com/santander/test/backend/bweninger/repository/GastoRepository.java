package com.santander.test.backend.bweninger.repository;

import com.santander.test.backend.bweninger.model.Gasto;
import com.santander.test.backend.bweninger.model.GastoPorData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by BWeninger on 10/01/2019.
 */
@Repository
public interface GastoRepository extends CassandraRepository<Gasto, UUID> {

    @Query("Select * from gastos where cpf_usuario = :cpfUsuario")
    List<Gasto> findByCpf(@Param("cpfUsuario") String cpf);


    @Query("Select * from gastos where id = :idGasto and cpf_usuario = :cpfUsuario")
    Optional<Gasto> findByIdAndCpf(@Param("idGasto") UUID idGasto, @Param("cpfUsuario") String cpfUsuario);

}
