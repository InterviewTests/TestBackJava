/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.santander.testbackjava.exception.SistemaCredenciadoException;
import br.com.santander.testbackjava.model.dto.GastoCartaoDTO;
import lombok.extern.log4j.Log4j2;

/**
 * Classe responsável por realizar as operações de negócio dos sistemas gerencias.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 17:42:09
 * @version x.x
 */
@Service
@Log4j2
public class SistemaCredenciadoService {

	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void validarSistemaCrendenciado(GastoCartaoDTO gastoCartaoDTO) throws SistemaCredenciadoException {
		log.info("INICIO - Método: validarSistemaCrendenciado");


		try {
			
			//FIXME aplicar regra validacao do sistema credenciado

		} catch (Exception e) {
			log.error("Erro SistemaCredenciadoService.validarSistemaCrendenciado", e);
			throw new SistemaCredenciadoException(e.getMessage(), e);
		}

		log.info("FIM - Método: validarSistemaCrendenciado");
	}

}
