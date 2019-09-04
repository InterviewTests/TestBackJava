package br.com.santander.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public class ResponseUtil {
	public static void mensagemErro(String mensagem) {
		throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, mensagem, null);
	}
	
	public static void mensagemErro(String mensagem, Exception e) {
		throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, mensagem, e);
	}
	
	public static ResponseEntity<Object> retornaMensagemErro(List<String> mensagens) {
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(mensagens);
	}
}
