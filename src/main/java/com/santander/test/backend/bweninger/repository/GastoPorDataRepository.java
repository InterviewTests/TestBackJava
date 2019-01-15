package com.santander.test.backend.bweninger.repository;

import com.santander.test.backend.bweninger.model.GastoPorData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by BWeninger on 14/01/2019.
 */
public interface GastoPorDataRepository extends CassandraRepository<GastoPorData, String>{

    @Query("Select * from gastos_por_data where cpf_usuario = :cpfUsuario AND data >= :dataIni AND data <= :dataFim")
    List<GastoPorData> findByCpfndDate(@Param("cpfUsuario") String cpf, @Param("dataIni") LocalDateTime dataIni,
                                @Param("dataFim") LocalDateTime dataFim);
}
