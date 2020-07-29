/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Classe responsável por definir métodos utilitários da aplicação.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 19:08:05
 * @version x.x
 */
public final class AppUtil {
	
	public static Date getDateMenos5Segundos() {
		//FIXME subtract 5 segundos da data atual
		return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}

}
