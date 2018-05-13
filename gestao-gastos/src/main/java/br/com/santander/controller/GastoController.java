package br.com.santander.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.service.CartaoService;
import br.com.santander.service.GastoService;
import br.com.santander.service.UsuarioService;
import br.com.santander.vo.CartaoVO;
import br.com.santander.vo.GastoVO;
import br.com.santander.vo.UsuarioVO;

/**
 * Controller responsável por disponibilizar os serviços REST responsável por manter os gastos por cartão
 * @author AntonioJolvino
 *
 */
@RestController
public class GastoController {

	@Autowired
	private GastoService gastoService;
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private UsuarioService usuarioService;

	public GastoController() {

	}
	
	/**
	 * metodo para dar os primeiros inserts na base para permitir os testes iniciais
	 */
	@PostConstruct
	public void montaBaseParaInicialTestesDasConsultas() {
		
		Long codigoUsuario;
		
		Long numeroCartao;
		
		List<UsuarioVO> usuarios = usuarioService.findAll();
		if(usuarios.isEmpty()) {
			codigoUsuario = 12345L;
			UsuarioVO usuarioBase = new UsuarioVO(codigoUsuario, "UsuarioTeste", "senha", true, Calendar.getInstance().getTime());
			usuarioService.save(usuarioBase);
		} else {
			codigoUsuario = usuarios.get(0).getCodigoUsuario();
		}
		
		List<CartaoVO> cartoes = cartaoService.findAll();
		if(cartoes.isEmpty()) {
			numeroCartao = 1111222233334444L;
			CartaoVO cartaoBase = new CartaoVO(numeroCartao, codigoUsuario, Calendar.getInstance().getTime());
			cartaoService.save(cartaoBase);
		} else {
			numeroCartao = cartoes.get(0).getNumeroCartao();
		}
		
		
		List<GastoVO> gastos = gastoService.findAll();
		
		if(gastos.isEmpty()) {
			GastoVO g1 = new GastoVO(UUID.randomUUID(), 1L, "Mercado Joanim", new BigDecimal("523.86"), codigoUsuario, Calendar.getInstance().getTime());
			GastoVO g2 = new GastoVO(UUID.randomUUID(), 1L, "Comida Japonesa", new BigDecimal("65.90"), codigoUsuario, Calendar.getInstance().getTime());
			
			GastoVO g3 = new GastoVO(UUID.randomUUID(), 1L, "Loja Fisica", "Posto Combustivel", new BigDecimal("151.36"), codigoUsuario, Calendar.getInstance().getTime());
			GastoVO g4 = new GastoVO(UUID.randomUUID(), 1L, "Loja Virtual", "Mercado Livre", new BigDecimal("253.81"), codigoUsuario, Calendar.getInstance().getTime());
			
			gastoService.save(numeroCartao, g1);
			gastoService.save(numeroCartao, g2);
			gastoService.save(numeroCartao, g3);
			gastoService.save(numeroCartao, g4);
		}
	}

	/**
	 * Retorna todos os gastos registrados na base
	 * @return
	 */
	@RequestMapping(value = "/santander/gastos", method = RequestMethod.GET)
	public ResponseEntity<List<GastoVO>> listarTodosGastos() {
		List<GastoVO> todosGastos = gastoService.findAll();
		
		if (todosGastos == null || todosGastos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<GastoVO>>(new ArrayList<GastoVO>(todosGastos), HttpStatus.OK);
	}

	/**
	 * retorna todos os gastos registrados na base para o cartao especificado
	 * @param numeroCartao
	 * @return
	 */
	@RequestMapping(value = "/santander/{idUsuario}/gastos/numerocartao", method = RequestMethod.GET)
	public ResponseEntity<List<GastoVO>> buscarPorNumeroCartao(
			@PathVariable("idUsuario") Long idUsuario, 
			@RequestParam(value="numeroCartao", required=true) Long numeroCartao) {
		
		if(usuarioService.findById(idUsuario) == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		if(cartaoService.findById(numeroCartao) == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		List<GastoVO> gastos = gastoService.findByNumeroCartao(numeroCartao);
		if (gastos == null || gastos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<GastoVO>>(new ArrayList<GastoVO>(gastos), HttpStatus.OK);
	}
	
	/**
	 * retorna todos os gastos registrados na base para a data especificada no formato yyyy-mm-dd
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/santander/{idUsuario}/gastos/datagasto", method = RequestMethod.GET)
	public ResponseEntity<List<GastoVO>> buscarPorData(
			@PathVariable("idUsuario") Long idUsuario,
			@RequestParam(value="numeroCartao", required=true) Long numeroCartao,
			@RequestParam(value="data", required=true) String data) {
		
		if(usuarioService.findById(idUsuario) == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		if(cartaoService.findById(numeroCartao) == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		List<GastoVO> gastosPorData = gastoService.findByDataECartao(data, numeroCartao);
		if (gastosPorData == null || gastosPorData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<GastoVO>>(new ArrayList<GastoVO>(gastosPorData), HttpStatus.OK);
	}
	
	/**
	 * Salva o gasto para um cartão especificado
	 * @param idUsuario
	 * @param numeroCartao
	 * @param gastoVO
	 * @return
	 */
	@RequestMapping(value = "/santander/{idUsuario}/gastos/adiciona", method = RequestMethod.POST)
	public ResponseEntity<GastoVO> adicionaGasto(
			@PathVariable("idUsuario") Long idUsuario,
			@RequestParam(value="numeroCartao", required=true) Long numeroCartao,
			@RequestBody GastoVO gastoVO) {

		if(usuarioService.findById(idUsuario) == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		if(cartaoService.findById(numeroCartao) == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		if (gastoVO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		gastoService.save(numeroCartao, gastoVO);

		return new ResponseEntity<GastoVO>(gastoVO, HttpStatus.OK);
	}
	
	/**
	 * Atualiza a categoria de um gasto especificado
	 * @param idUsuario
	 * @param codigoGasto
	 * @param categoria
	 * @return
	 */
	@RequestMapping(value = "/santander/{idUsuario}/gastos/atualiza", method = RequestMethod.PUT)
	public ResponseEntity<GastoVO> adicionaGasto(
			@PathVariable("idUsuario") Long idUsuario,
			@RequestParam(value="codigoGasto", required=true) UUID codigoGasto,
			@RequestParam(value="categoria", required=true) String categoria){
		
		if(usuarioService.findById(idUsuario) == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		GastoVO gastoAtualizado = gastoService.atualizaCategoria(codigoGasto, categoria);
		
		if(gastoAtualizado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<GastoVO>(gastoAtualizado, HttpStatus.OK);
	}
}
