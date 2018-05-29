/**
 *
 */
package br.com.santander.cartao.dao.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.santander.cartao.dao.UsuarioDAO;
import br.com.santander.cartao.domain.UsuarioCartao;
import br.com.santander.cartao.repository.UsuarioCartaoRepository;
import br.com.santander.cartao.vo.CartaoVO;
import br.com.santander.cartao.vo.UsuarioCartaoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
@Component
public class UsuarioDAOJPA implements UsuarioDAO {

	@Autowired
	private UsuarioCartaoRepository usuarioCartaoRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsuarioCartaoVO buscarUserByUsuarioAndPass(final UsuarioCartaoVO usuarioCartaoVO) {
		final UsuarioCartao usuarioCartao = usuarioCartaoRepository.buscaPorUsuarioSenha(usuarioCartaoVO.getUsuario().trim().toUpperCase(),
				usuarioCartaoVO.getSenha().trim());
		if (usuarioCartao == null) {
			return null;
		}

		final UsuarioCartaoVO retorno = new UsuarioCartaoVO();
		BeanUtils.copyProperties(usuarioCartao, retorno);

		if (usuarioCartao.getCartao() != null) {
			retorno.setCartao(new CartaoVO());
			BeanUtils.copyProperties(usuarioCartao.getCartao(), retorno.getCartao());
		}

		// Não retorna a senha
		retorno.setSenha(null);

		return retorno;
	}

	/**
	 * @return the usuarioCartaoRepository
	 */
	public UsuarioCartaoRepository getUsuarioCartaoRepository() {
		return usuarioCartaoRepository;
	}

	/**
	 * @param usuarioCartaoRepository
	 *            the usuarioCartaoRepository to set
	 */
	public void setUsuarioCartaoRepository(final UsuarioCartaoRepository usuarioCartaoRepository) {
		this.usuarioCartaoRepository = usuarioCartaoRepository;
	}

}
