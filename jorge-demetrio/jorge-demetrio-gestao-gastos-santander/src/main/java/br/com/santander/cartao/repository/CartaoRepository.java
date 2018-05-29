/**
 *
 */
package br.com.santander.cartao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.cartao.domain.Cartao;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 28 de mai de 2018 .
 */
@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

}
