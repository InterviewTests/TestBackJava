/**
 *
 */
package br.com.santander.cartao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.cartao.service.GastosService;
import br.com.santander.cartao.vo.ClassificacaoMovimentacaoVO;
import br.com.santander.protocolo.ErrorVO;
import br.com.santander.protocolo.RetornoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
@RestController
@RequestMapping("/classificarGasto")
public class ClassificarGastoController {

	@Autowired
	GastosService gastosService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ClassificacaoMovimentacaoVO> busca(@RequestParam(value = "nome") final String nome) {

		return gastosService.buscaClassificao(nome);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody RetornoVO classificar(@RequestParam(value = "token") final String token, @RequestParam(value = "codigogasto") final Long codigogasto,
			@RequestParam(value = "descricaoclassificacao") final String descricaoClassificacao,
			@RequestParam(value = "codigoClassificacao") final Long codigoClassificacao) {
		final RetornoVO retornoVO = new RetornoVO();
		retornoVO.setSucesso(Boolean.TRUE);
		try {
			gastosService.classificarGasto(token, codigogasto, descricaoClassificacao, codigoClassificacao);
		} catch (final Exception e) {
			retornoVO.setSucesso(Boolean.FALSE);
			retornoVO.getErrors().add(new ErrorVO(e.getMessage()));
		}
		return retornoVO;
	}

	/**
	 * @return the gastosService
	 */
	public GastosService getGastosService() {
		return gastosService;
	}

	/**
	 * @param gastosService
	 *            the gastosService to set
	 */
	public void setGastosService(final GastosService gastosService) {
		this.gastosService = gastosService;
	}

}
