/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.testbackjava.exception.CategoriaException;
import br.com.santander.testbackjava.model.dto.CategoriaDTO;
import br.com.santander.testbackjava.service.CategoriaService;
import lombok.extern.log4j.Log4j2;

/**
 * API de Categorização de gastos do cartão.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 17:55:41
 * @version x.x
 */
@RestController
@RequestMapping("api/v1/categoria")
@Log4j2
public class CategoriaController {

	/**
	 * Atributo categoriaService
	 */
	@Autowired
	private CategoriaService categoriaService;

	/**
	 * Método responsável por listar sugestão de categorias de acordo com a descricao informada.
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 21:05:58
	 * @param descricao {@link String} - descricao.
	 * @return {@link ResponseEntity} - Retorna Response Entity.
	 */
	@GetMapping(path = "/listar-sugestao", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obterSugestaoCategoriaByDescricao(@RequestParam(name = "descricao") final String descricao) {

		log.info("INICIO: Método - obterSugestaoCategoriaByDescricao");

		ResponseEntity<?> response;

		try {

			response = new ResponseEntity<List<CategoriaDTO>>(categoriaService.obterSugestaoCategoriaByDescricao(descricao),
					HttpStatus.OK);

		} catch (final CategoriaException e) {
			log.error("[CategoriaController - obterSugestaoCategoriaByDescricao] ERRO: " + e.getMessage(), e);
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("Saída response........................: " + response);
		log.info("FIM: Método - obterSugestaoCategoriaByDescricao");

		return response;
	}
	
}
