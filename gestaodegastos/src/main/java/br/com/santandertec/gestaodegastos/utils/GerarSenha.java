package br.com.santandertec.gestaodegastos.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {

	public static void main(String[] args) {

		String senhaCriptografada = new BCryptPasswordEncoder().encode("123456");

		System.out.println(senhaCriptografada);

	}
	
}
