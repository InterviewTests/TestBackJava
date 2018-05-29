/**
 *
 */
package br.com.santander.cartao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.santander.cartao.domain.ClassificacaoMovimentacao;

/**
 * @author Jorge Demetrio.
 * @version 1.0
 * @since 28 de mai de 2018
 */
@Repository
public interface ClassificacaoMovimentacaoRepository extends JpaRepository<ClassificacaoMovimentacao, Long> {

	public List<ClassificacaoMovimentacao> buscarClassificacaoPorNome(@Param("busca") final String busca);
}
