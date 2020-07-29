/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.testbackjava.exception.GastoCartaoException;
import br.com.santander.testbackjava.exception.SistemaCredenciadoException;
import br.com.santander.testbackjava.exception.ValidacaoIncluirGastoCartaoException;
import br.com.santander.testbackjava.integration.kafka.producer.GastoCartaoProducer;
import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import br.com.santander.testbackjava.service.SistemaCredenciadoService;
import br.com.santander.testbackjava.service.ValidateGestaoCartaoService;
import lombok.extern.log4j.Log4j2;

/**
 * Facade para Integração de gastos por cartão.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 22:32:21
 * @version x.x
 */
@Service
@Log4j2
public class GastoCartaoFacade {

	/**
	 * Atributo sistemaCredenciadoService
	 */
	@Autowired
	private SistemaCredenciadoService sistemaCredenciadoService;

	/**
	 * Atributo validateGestaoCartaoService
	 */
	@Autowired
	private ValidateGestaoCartaoService validateGestaoCartaoService;
	
	/**
	 * Atributo gastoCartaoProducer
	 */
	@Autowired
	private GastoCartaoProducer gastoCartaoProducer;

	/**
	 * Método responsável por realizar Integração de gastos por cartão
	 *
	 * @author Altran - jabes
	 * @since 28 de jul de 2020 19:20:43
	 * @param gastoCartaoDTO - {@link GastoCartaoDTO} - GastoCartaoDTO.
	 * @throws GastoCartaoException @link GastoCartaoException}
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void incluirGastoCartao(GastoCartaoDTO gastoCartaoDTO) throws GastoCartaoException {
		log.info("INICIO - Método: incluirGastoCartao");

		try {

			// Apenas sistemas credenciados poderão incluir novos gastos
			sistemaCredenciadoService.validarSistemaCrendenciado(gastoCartaoDTO);

			// Aplica validação das informações
			validateGestaoCartaoService.validarIncluirGastoCartao(gastoCartaoDTO);

			gastoCartaoProducer.sendIncluiGastoCartao(gastoCartaoDTO);

		} catch (SistemaCredenciadoException e) {
			log.error("Warn: Sistema não credenciado", e);
			throw new GastoCartaoException(e.getMessage(), e);
		} catch (ValidacaoIncluirGastoCartaoException e) {
			log.error("Warn: Dados inválidos", e);
			throw new GastoCartaoException(e.getMessage(), e);
		} catch (Exception e) {
			log.error("Erro GastoCartaoService.incluirGastoCartao", e);
			throw new GastoCartaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: incluirGastoCartao");
	}

	
}
