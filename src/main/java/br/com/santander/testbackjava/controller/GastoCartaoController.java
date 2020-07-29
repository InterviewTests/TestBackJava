/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.testbackjava.exception.GastoCartaoException;
import br.com.santander.testbackjava.facade.GastoCartaoFacade;
import br.com.santander.testbackjava.model.dto.CategoriaGastoCartaoDTO;
import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import br.com.santander.testbackjava.service.GastoCartaoService;
import lombok.extern.log4j.Log4j2;

/**
 * API de Integração de gastos por cartão.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 17:55:41
 * @version x.x
 */
@RestController
@RequestMapping("api/v1/gasto-cartao")
@Log4j2
public class GastoCartaoController {

	/**
	 * Atributo gastoCartaoService
	 */
	@Autowired
	private GastoCartaoService gastoCartaoService;
	
	/**
	 * Atributo gastoCartaoFacade
	 */
	@Autowired
	private GastoCartaoFacade gastoCartaoFacade;

	/**
	 * Método responsável por incluir gasto cartão.
	 *
	 * @param gastoCartaoDTO {@link GastoCartaoDTO} - gastoCartaoDTO.
	 * @return {@link ResponseEntity} - Retorna Response Entity.
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 18:13:58
	 */
	@PostMapping(path = "/incluir", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> incluirGastoCartao(@RequestBody final GastoCartaoDTO gastoCartaoDTO) {

		log.info("INICIO: Método - incluirGastoCartao");

		ResponseEntity<?> response;

		try {

			gastoCartaoFacade.incluirGastoCartao(gastoCartaoDTO);

			response = new ResponseEntity<>(HttpStatus.OK);

		} catch (final GastoCartaoException e) {
			log.error("[GastoCartaoController - incluirGastoCartao] ERRO: " + e.getMessage(), e);
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("Saída response........................: " + response);
		log.info("FIM: Método - incluirGastoCartao");

		return response;
	}

	/**
	 * Método responsável por obter os gastos atuais.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 17:54:14
	 * @param codigoUsuario {@link Long} - codigoUsuario.
	 * @param pagina {@link Integer} - pagina.
	 * @param totalPagina {@link Integer} - totalPagina.
	 * @return {@link ResponseEntity} - Retorna Response Entity.
	 */
	@GetMapping(path = "/listar-atual", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obterGastosAtuaisCartao(@RequestParam("codigo-usuario") final Long codigoUsuario,
			@RequestParam Integer pagina, @RequestParam("total-pagina") Integer totalPagina) {

		log.info("INICIO: Método - obterGastosAtuaisCartao");

		ResponseEntity<?> response;

		try {

			response = new ResponseEntity<List<GastoCartaoDTO>>(
					gastoCartaoService.obterGastosAtuaisCartao(codigoUsuario, pagina, totalPagina), HttpStatus.OK);

		} catch (final GastoCartaoException e) {
			log.error("[GastoCartaoController - obterGastosAtuaisCartao] ERRO: " + e.getMessage(), e);
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("Saída response........................: " + response);
		log.info("FIM: Método - obterGastosAtuaisCartao");

		return response;
	}

	/**
	 * Método responsável por obter os gastos do cartao do usuario de acordo com o filtro data.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 17:54:14
	 * @param codigoUsuario {@link Long} - codigoUsuario.
	 * @param dataFiltro {@link Date} - dataFiltro.
	 * @param pagina {@link Integer} - pagina.
	 * @param totalPagina {@link Integer} - totalPagina.
	 * @return {@link ResponseEntity} - Retorna Response Entity.
	 */
	@GetMapping(path = "/listar-filtro-data", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obterGastosCartaoFiltroData(@RequestParam(name = "codigo-usuario") final Long codigoUsuario,
			@RequestParam(name = "data-filtro") final Date dataFiltro, @RequestParam Integer pagina,
			@RequestParam("total-pagina") Integer totalPagina) {

		log.info("INICIO: Método - obterGastosCartaoFiltroData");

		ResponseEntity<?> response;

		try {

			response = new ResponseEntity<List<GastoCartaoDTO>>(
					gastoCartaoService.obterGastosCartaoFiltroData(codigoUsuario, dataFiltro, pagina, totalPagina),
					HttpStatus.OK);

		} catch (final GastoCartaoException e) {
			log.error("[GastoCartaoController - obterGastosAtuaisCartao] ERRO: " + e.getMessage(), e);
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("Saída response........................: " + response);
		log.info("FIM: Método - obterGastosCartaoFiltroData");

		return response;
	}

	/**
	 * Método responsável por obter detalhe do gasto cartão.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 20:14:11
	 * @param id {@link String} - id.
	 * @return {@link ResponseEntity} - Retorna Response Entity.
	 */
	@GetMapping(path = "/detalhar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obterGastoCartaoPorId(@PathVariable("id") final String id) {

		log.info("INICIO: Método - obterGastoCartaoPorId");

		ResponseEntity<?> response;

		try {

			response = new ResponseEntity<GastoCartaoDTO>(gastoCartaoService.obterGastoCartaoPorId(id), HttpStatus.OK);

		} catch (final GastoCartaoException e) {
			log.error("[GastoCartaoController - obterGastoCartaoPorId] ERRO: " + e.getMessage(), e);
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("Saída response........................: " + response);
		log.info("FIM: Método - obterGastoCartaoPorId");

		return response;
	}
	
	/**
	 * Método responsável por atualizar categoria de gasto cartão.
	 *
	 * @param gastoCartaoDTO {@link CategoriaGastoCartaoDTO} - gastoCartaoDTO.
	 * @return {@link ResponseEntity} - Retorna Response Entity.
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 20:56:28
	 */
	@PutMapping(path = "/atualizar-categoria", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> atualizarCategoriaGastoCartao(@RequestBody final CategoriaGastoCartaoDTO  gastoCartaoDTO) {

		log.info("INICIO: Método - atualizarCategoriaGastoCartao");

		ResponseEntity<?> response;

		try {

			gastoCartaoService.atualizarCategoriaGastoCartao(gastoCartaoDTO);

			response = new ResponseEntity<>(HttpStatus.OK);

		} catch (final GastoCartaoException e) {
			log.error("[GastoCartaoController - atualizarCategoriaGastoCartao] ERRO: " + e.getMessage(), e);
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("Saída response........................: " + response);
		log.info("FIM: Método - atualizarCategoriaGastoCartao");

		return response;
	}

}
