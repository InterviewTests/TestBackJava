/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.testbackjava.exception.AutenticacaoException;
import lombok.extern.log4j.Log4j2;

/**
 * Classe responsável por realizar as operações de autenticação.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 17:42:09
 * @version x.x
 */
@Service
@Log4j2
public class AutenticacaoService {

	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void validarSessaoCliente() throws AutenticacaoException {
		log.info("INICIO - Método: validarSessaoCliente");


		try {
			
			//FIXME validacao autenticacao

		} catch (Exception e) {
			log.error("Erro AutenticacaoService.validarSessaoCliente", e);
			throw new AutenticacaoException(e.getMessage(), e);
		}

		log.info("FIM - Método: validarSessaoCliente");
	}

}
