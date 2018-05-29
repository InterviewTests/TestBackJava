/**
 *
 */
package br.com.santander.cartao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.cartao.service.LoginService;
import br.com.santander.protocolo.ErrorVO;
import br.com.santander.protocolo.RetornoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	/**
	 * @return the loginService
	 */
	public LoginService getLoginService() {
		return loginService;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody RetornoVO login(@RequestParam(value = "usuario") final String usuario, @RequestParam(value = "senha") final String senha) {
		final RetornoVO retornoVO = new RetornoVO();
		retornoVO.setSucesso(Boolean.TRUE);
		try {
			final String token = loginService.login(usuario, senha);
			if (token != null) {
				retornoVO.setToken(token);
			} else {
				retornoVO.setSucesso(Boolean.FALSE);
				retornoVO.getErrors().add(new ErrorVO("Usuário inválido"));
			}
		} catch (final Exception e) {
			retornoVO.setSucesso(Boolean.FALSE);
			retornoVO.getErrors().add(new ErrorVO(e.getMessage()));
		}
		return retornoVO;
	}

	/**
	 * @param loginService
	 *            the loginService to set
	 */
	public void setLoginService(final LoginService loginService) {
		this.loginService = loginService;
	}
}
