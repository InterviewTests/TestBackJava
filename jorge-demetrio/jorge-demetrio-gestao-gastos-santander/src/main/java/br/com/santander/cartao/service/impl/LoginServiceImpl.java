/**
 *
 */
package br.com.santander.cartao.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.santander.cartao.dao.UsuarioDAO;
import br.com.santander.cartao.service.LoginService;
import br.com.santander.cartao.vo.UsuarioCartaoVO;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author Jorge Demetrio.
 * @version 1.0
 * @since 28 de mai de 2018
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = Logger.getLogger(GastosServiceImpl.class);
	private static MessageDigest MESSAGE_DIGEST_MD5;
	private static MessageDigest MESSAGE_DIGEST_SHA;
	private static final CacheManager sessoesToken;
	private static final String TOKENS = "tokens";

	static {
		sessoesToken = CacheManager.create();
		sessoesToken.addCache(LoginServiceImpl.TOKENS);
		LOGGER.info("INICIO DE CARREGANDO OBJETOS DE CRIPTOGRAFIA");
		try {
			MESSAGE_DIGEST_MD5 = MessageDigest.getInstance("MD5");
		} catch (final NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
		}
		try {
			MESSAGE_DIGEST_SHA = MessageDigest.getInstance("SHA-1");
		} catch (final NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

	/**
	 * @return the sessoestoken
	 */
	public static CacheManager getSessoestoken() {
		return sessoesToken;
	}

	@Autowired
	UsuarioDAO usuarioDAO;

	/**
	 * Gera um token
	 *
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	private String gerarToken() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		final String uuid = UUID.randomUUID().toString();

		return uuid;
	}

	/**
	 * @return the usuarioDAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsuarioCartaoVO getUsuarioLogadoByToken(final String token) {

		if (token == null || token.isEmpty()) {
			LOGGER.error("Tentantiva de acesso com token inválido");
			throw new RuntimeException("Tentantiva de acesso com token inválido");
		}

		final Element elemento = sessoesToken.getCache(LoginServiceImpl.TOKENS).get(token);

		if (elemento == null) {
			LOGGER.error("Sessão inspirada do token " + token);
			throw new RuntimeException("Sessão inspirada");
		}

		final UsuarioCartaoVO usuarioCartaoVO = UsuarioCartaoVO.class.cast(elemento);

		return usuarioCartaoVO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String login(final String usuario, final String senha) {
		String token = null;
		if (senha == null || senha.isEmpty() || senha.length() < 8) {
			throw new RuntimeException("Campo Senha inválido");
		}
		if (usuario == null || usuario.isEmpty() || usuario.length() < 5) {
			throw new RuntimeException("Campo Usuário inválido");
		}

		final String novaCriptografada = new String(MESSAGE_DIGEST_MD5.digest(senha.getBytes())) + ":"
				+ new String(MESSAGE_DIGEST_SHA.digest(senha.getBytes()));

		UsuarioCartaoVO usuarioCartaoVO = new UsuarioCartaoVO();

		usuarioCartaoVO.setSenha(novaCriptografada);
		usuarioCartaoVO.setUsuario(usuario.trim());

		usuarioCartaoVO = usuarioDAO.buscarUserByUsuarioAndPass(usuarioCartaoVO);

		if (usuarioCartaoVO == null) {
			return null;
		}

		try {
			final Cache cache = sessoesToken.getCache(LoginServiceImpl.TOKENS);
			int tentativas = 0;
			do {
				if (tentativas++ > 100) {
					LOGGER.error("Erro ao tentar gerar um token novo, encerraram as 100 tentantivas");
					throw new RuntimeException("Impossível gerar um token novo no momento tente mais tarde");
				}
				token = gerarToken();

			} while (cache.get(token) != null);
			cache.put(new Element(token, usuarioCartaoVO));

		} catch (final NoSuchAlgorithmException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new RuntimeException(ex);
		} catch (final UnsupportedEncodingException ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new RuntimeException(ex);
		} catch (final Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new RuntimeException(ex);
		}

		return token;
	}

	/**
	 * @param usuarioDAO
	 *            the usuarioDAO to set
	 */
	public void setUsuarioDAO(final UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
