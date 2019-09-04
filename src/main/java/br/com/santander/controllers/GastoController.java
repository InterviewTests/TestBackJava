package br.com.santander.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.dtos.GastoDTO;
import br.com.santander.entities.Gasto;
import br.com.santander.services.GastoService;
import br.com.santander.services.UserDetailsServiceImpl;

@RestController
@RequestMapping("/gastos")
public class GastoController {

	//Dependência onde tem o método que trás o código do usuário corrente
	@Autowired
	private UserDetailsServiceImpl userDetails;
	
	@Autowired
	private GastoService gastoService;
	
	/**
	 * Método responsável por cadastrar um gasto. Recebe um DTO de gasto pelo corpo da requisição e o código do usuário corrente 
	 * @author valdeci
	 * @param gasto
	 * @return GastoDTO
	 */
	@PostMapping
	public GastoDTO cadastrar(@RequestBody GastoDTO gasto) {
		return gastoService.cadastrar(gasto, userDetails.get().getCodigoUsuario());
	}
	
	/**
	 * Responsável por recuperar um gasto pelo uuid dele. Recebe o uuid pela url e o código do usuário corrente
	 * @author valdeci
	 * @param uuid
	 * @return GastoDTO
	 */
	@GetMapping("/{uuid}")
	public GastoDTO get(@NotBlank(message = "Uuid de gasto não pode ser nulo") @PathVariable("uuid") String uuid) {
		return gastoService.getDTO(uuid, userDetails.get().getCodigoUsuario());
	}
	
	/**
	 * Responsável por listar todos os gastos do usuário. Recebe apenas o código do usuário corrente
	 * @author valdeci
	 * @return List<GastoDTO>
	 */
	@GetMapping
	public List<GastoDTO> gastos(){
		return gastoService.listar(userDetails.get().getCodigoUsuario());
	}
	
	/**
	 * Responsável por listar os gastos de uma data em específico. 
	 * Recebe uma data no padrão brasilero(dd/MM/yyyy) pelo request params e o código do usuário corrente
	 * @author valdeci
	 * @param data(padrão BR)
	 * @return
	 */
	@GetMapping("/por-data")
	public List<GastoDTO> gastosPorData(@NotNull(message = "Data não pode ser nula") @RequestParam("data") String data){
		return gastoService.listarPorData(data, userDetails.get().getCodigoUsuario());
	}
	
	/**
	 * Responsável por alterar uma categoria de um gasto em específico. Recebe o uuid de um gasto pela url, 
	 * recebe a nova categora pelo request params e recebe o código do usuário corrente.
	 * @author valdeci
	 * @param categoria
	 * @param uuid
	 * @return GastoDTO
	 */
	@PutMapping("/altera-categoria/{uuid}")
	public GastoDTO alterarCategoria(@NotBlank(message = "Categoria não pode ser nula ou ficar em branco") @RequestParam("categoria") String categoria, 
			@NotBlank(message = "Uuid não pode ser nulo ou ficar em branco") @PathVariable("uuid") String uuid) {
		return gastoService.alterarCategoria(categoria, uuid, userDetails.get().getCodigoUsuario());
	}
	
	/**
	 * Responsável por pesquisar as categorias de a cordo com o que o usuário pessar nos parâmetros. 
	 * Recebe os carácteres da categorias que o usuário quer pesqusar e recebe o código do usuário corrente
	 * @author valdeci
	 * @param categoria
	 * @return List<String> 
	 */
	@GetMapping("/pesquisar-categoria/{categoria}")
	public List<String> pesquisarCategorias(@NotNull(message = "Categoria não pode ser nula") @PathVariable("categoria")String categoria){
		return gastoService.pesquisarCategorias(categoria, userDetails.get().getCodigoUsuario());
	}
	
	
}























