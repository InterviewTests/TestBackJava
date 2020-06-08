package com.paulo.altran.gasto.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.paulo.altran.gasto.dto.UsuarioDTO;

@FeignClient(name = "usuarios")
public interface UsuarioClient {

	@GetMapping("/pesquisa/acesso")
	public Long buscarIdPeloAcesso(@RequestParam String termo);

	@GetMapping("/{id}")
	public UsuarioDTO buscarUsuarioPeloId(@PathVariable Long id);
}
