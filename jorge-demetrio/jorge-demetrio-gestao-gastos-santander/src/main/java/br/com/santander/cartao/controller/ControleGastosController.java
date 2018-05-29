/**
 *
 */
package br.com.santander.cartao.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.cartao.service.GastosService;
import br.com.santander.cartao.vo.MovimentacaoCartaoVO;
import br.com.santander.protocolo.ErrorVO;
import br.com.santander.protocolo.RetornoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
@RestController
@RequestMapping("/controleGastos")
public class ControleGastosController {

	@Autowired
	GastosService gastosService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MovimentacaoCartaoVO detalheGasto(@RequestParam(value = "token") final String token,
			@RequestParam(value = "codigogasto") final Long codigogasto) {
		return gastosService.gasto(token, codigogasto);
	}

	/**
	 * @return the gastosService
	 */
	public GastosService getGastosService() {
		return gastosService;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody RetornoVO gravarGasto(@RequestParam(value = "token") final String token, @RequestParam(value = "descricao") final String descricao,
			@RequestParam(value = "valor") final Double valor, @RequestParam(value = "codigousuario") final Long codigoUsuario,
			@RequestParam(value = "data") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") final Date data) {

		final RetornoVO retornoVO = new RetornoVO();
		retornoVO.setSucesso(Boolean.TRUE);

		try {
			gastosService.adicionarGasto(token, descricao, valor, data);
		} catch (final Exception e) {
			retornoVO.setSucesso(Boolean.FALSE);
			retornoVO.getErrors().add(new ErrorVO(e.getMessage()));
		}
		return retornoVO;
	}

	/**
	 * @param gastosService
	 *            the gastosService to set
	 */
	public void setGastosService(final GastosService gastosService) {
		this.gastosService = gastosService;
	}

}
