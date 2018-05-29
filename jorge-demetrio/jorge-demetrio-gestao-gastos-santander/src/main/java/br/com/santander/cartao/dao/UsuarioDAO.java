package br.com.santander.cartao.dao;

import br.com.santander.cartao.vo.UsuarioCartaoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
public interface UsuarioDAO {

	/**
	 * Retorna true ous false se exite um usuário com o usuário e senha.
	 *
	 * @param usuarioCartaoVO
	 * @return retorna null se não achar o usuário
	 */
	public UsuarioCartaoVO buscarUserByUsuarioAndPass(final UsuarioCartaoVO usuarioCartaoVO);
}
