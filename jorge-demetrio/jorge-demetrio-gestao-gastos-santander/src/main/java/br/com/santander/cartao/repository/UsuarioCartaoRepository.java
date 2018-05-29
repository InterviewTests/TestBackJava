/**
 *
 */
package br.com.santander.cartao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.santander.cartao.domain.UsuarioCartao;

/**
 * @author Jorge Demetrio.
 * @version 1.0
 * @since 28 de mai de 2018
 */
@Repository
public interface UsuarioCartaoRepository extends JpaRepository<UsuarioCartao, Long> {

	public UsuarioCartao buscaPorUsuarioSenha(@Param("usuario") final String usuario, @Param("senha") final String senha);
}
