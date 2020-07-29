/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.testbackjava.exception.ValidacaoIncluirGastoCartaoException;
import br.com.santander.testbackjava.model.dto.CategoriaGastoCartaoDTO;
import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import lombok.extern.log4j.Log4j2;

/**
 * Classe responsável por realizar as operações de validação da operação de cartão.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 18:57:12
 * @version x.x
 */
@Service
@Log4j2
public class ValidateGestaoCartaoService {

	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void validarIncluirGastoCartao(GastoCartaoDTO gastoCartaoDTO) throws ValidacaoIncluirGastoCartaoException {
		log.info("INICIO - Método: validarIncluirGastoCartao");


		try {
			
			//FIXME aplicar regra validacao das informação, formatos, dados, etc...
			//Exemplos
			if (gastoCartaoDTO == null) {
				throw new ValidacaoIncluirGastoCartaoException("Dados do gasto do cartão não foram preenchidos");
			}
			if (gastoCartaoDTO.getDescricao() == null || "".equalsIgnoreCase(gastoCartaoDTO.getDescricao())) {
				throw new ValidacaoIncluirGastoCartaoException("Descrição do gasto do cartão não foi preenchido");
			}
			if (gastoCartaoDTO.getCodigoUsuario() == null) {
				throw new ValidacaoIncluirGastoCartaoException("Código usuário do gasto do cartão não foi preenchido");
			}
			if (gastoCartaoDTO.getValor() == null) {
				throw new ValidacaoIncluirGastoCartaoException("Valor do gasto do cartão não foi preenchido");
			}
			//etc...

		} catch (ValidacaoIncluirGastoCartaoException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error("Erro ValidateGestaoCartaoService.validarIncluirGastoCartao", e);
			throw new ValidacaoIncluirGastoCartaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: validarIncluirGastoCartao");
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void validarIncluirCategoriaGastoCartao(CategoriaGastoCartaoDTO categoriaGastoCartaoDTO) throws ValidacaoIncluirGastoCartaoException {
		log.info("INICIO - Método: validarIncluirCategoriaGastoCartao");


		try {
			
			//FIXME aplicar regra validacao das informação..
			//Exemplos
			if (categoriaGastoCartaoDTO == null || categoriaGastoCartaoDTO.getIdGastoCartao() == null || categoriaGastoCartaoDTO.getCategoria() == null) {
				throw new ValidacaoIncluirGastoCartaoException("Dados da categoria do gasto do cartão não foram preenchidos");
			}
			//etc...

		} catch (ValidacaoIncluirGastoCartaoException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error("Erro ValidateGestaoCartaoService.validarIncluirCategoriaGastoCartao", e);
			throw new ValidacaoIncluirGastoCartaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: validarIncluirCategoriaGastoCartao");
	}

}
