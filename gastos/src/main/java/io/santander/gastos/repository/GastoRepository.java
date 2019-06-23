package io.santander.gastos.repository;

import io.santander.gastos.domain.Gasto;
import io.santander.gastos.vo.GastosVO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    @Query(value = "FROM Gastos g WHERE g.codigoUsuario = ?1 " +
            "AND (?2 IS NULL OR g.descricao = ?2) " +
            "AND (?3 IS NULL OR g.valor = ?3) " +
            "AND (?4 IS NULL OR g.data = ?4)")
    PageImpl<GastosVO> buscaTodosOsGastoPorCliente(Long codigoUsuario, String descricao, Double valor, Date data, Pageable pageable);
}
