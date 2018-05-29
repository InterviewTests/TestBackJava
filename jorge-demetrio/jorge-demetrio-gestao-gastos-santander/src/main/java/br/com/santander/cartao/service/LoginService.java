/**
 *
 */
package br.com.santander.cartao.service;

import br.com.santander.cartao.vo.UsuarioCartaoVO;

/**
 * @author Jorge Demetrio.
 * @version 1.0
 * @since 28 de mai de 2018
 */
public interface LoginService {

	/**
	 * Retorna o objeto usuário
	 *
	 *
	 * @param token
	 * @return
	 */
	public UsuarioCartaoVO getUsuarioLogadoByToken(final String token);

	/**
	 * Login retorna um token caso de sucesso
	 *
	 * @param usuario
	 * @param senha
	 * @return
	 */
	public String login(final String usuario, final String senha);
}
