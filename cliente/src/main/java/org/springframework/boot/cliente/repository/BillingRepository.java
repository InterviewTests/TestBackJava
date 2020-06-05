package org.springframework.boot.cliente.repository;

import org.springframework.boot.cliente.model.Billing;
import org.springframework.boot.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.awt.print.Pageable;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    List<Billing> findByCodigousuario(@Param("codigousuario") Long codigousuario);


    @Query("SELECT t FROM Billing t WHERE LOWER(t.codigousuario) = LOWER(:codigousuario) and t.data = :paramData ")
    List<Billing>  findByData(@Param("codigousuario") Long codigousuario,@Param("paramData") Date paramData );

}
