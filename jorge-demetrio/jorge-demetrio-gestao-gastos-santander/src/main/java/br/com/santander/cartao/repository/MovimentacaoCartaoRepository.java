/**
 *
 */
package br.com.santander.cartao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.santander.cartao.domain.MovimentacaoCartao;

/**
 * @author Jorge Demetrio.
 * @version 1.0
 * @since 28 de mai de 2018
 */
@Repository
public interface MovimentacaoCartaoRepository extends JpaRepository<MovimentacaoCartao, Long> {

	public List<MovimentacaoCartao> gastosCliente(@Param("id") final Long id, Pageable pageable);

	public MovimentacaoCartao gastosClienteDetalhe(@Param("id") final Long id, @Param("gasto") final Long gasto);

	public List<MovimentacaoCartao> gastosClienteFiltroData(@Param("id") final Long id, @Param("dataIni") final Date dataIni,
			@Param("dataFim") final Date dataFim);

}
