/**
 *
 */
package br.com.santander.cartao.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.cartao.service.GastosService;
import br.com.santander.cartao.vo.MovimentacaoCartaoVO;

/**
 * @author Jorge Demetrio
 * @version 1.0
 * @since 29 de mai de 2018
 */
@RestController
@RequestMapping("/filtroGastos")
public class FiltroGastosController {

	@Autowired
	GastosService gastosService;

	/**
	 * @return the gastosService
	 */
	public GastosService getGastosService() {
		return gastosService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MovimentacaoCartaoVO> listarGastos(@RequestParam(value = "token") final String token,
			@RequestParam(value = "data", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date data) {
		List<MovimentacaoCartaoVO> retorno;
		if (data != null) {
			retorno = gastosService.listaGastos(token);
		} else {
			retorno = gastosService.listaGastos(token, data);
		}
		return retorno;
	}

	/**
	 * @param gastosService
	 *            the gastosService to set
	 */
	public void setGastosService(final GastosService gastosService) {
		this.gastosService = gastosService;
	}

}
