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
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.service.GastoService;
import br.com.santander.vo.CartaoVO;
import br.com.santander.vo.GastoVO;

@RestController
public class GastoController {

	@Autowired
	private GastoService gastoService;

	public GastoController() {

	}
	
	/**
	 * metodo para dar os primeiros inserts na base para permitir os testes iniciais
	 */
	@PostConstruct
	public void montaBaseParaInicialTestesDasConsultas() {
		List<CartaoVO> todosCartoes = gastoService.findAll();
		
		if(todosCartoes.isEmpty()) {
			GastoVO g1 = new GastoVO(UUID.randomUUID(), "Mercado Joanim", new BigDecimal("523.86"), 123456L, Calendar.getInstance().getTime());
			GastoVO g2 = new GastoVO(UUID.randomUUID(), "Comida Japonesa", new BigDecimal("65.90"), 123456L, Calendar.getInstance().getTime());
			GastoVO g3 = new GastoVO(UUID.randomUUID(), "Posto Combustivel", new BigDecimal("151.36"), 123456L, Calendar.getInstance().getTime());
			GastoVO g4 = new GastoVO(UUID.randomUUID(), "Estacionamento", new BigDecimal("200.00"), 123456L, Calendar.getInstance().getTime());
			GastoVO g5 = new GastoVO(UUID.randomUUID(), "Financiamento Casa", new BigDecimal("5000.00"), 123456L, Calendar.getInstance().getTime());
			
			CartaoVO c1 = new CartaoVO();
			c1.setNumeroCartao(5555444466662222L);
			c1.getGastos().add(g1);
			c1.getGastos().add(g3);
			c1.getGastos().add(g4);
			
			CartaoVO c2 = new CartaoVO();
			c2.setNumeroCartao(3333222288889999L);
			c2.getGastos().add(g2);
			c2.getGastos().add(g5);
			
			gastoService.save(c1);
			gastoService.save(c2);
		}
	}

	/**
	 * Retorna todos os gastos registrados na base
	 * @return
	 */
	@RequestMapping(value = "/gastos", method = RequestMethod.GET)
	public ResponseEntity<List<GastoVO>> listarTodosGastos() {
		List<CartaoVO> todosCartoes = gastoService.findAll();
		
		if (todosCartoes == null || todosCartoes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<GastoVO> gastosRetorno = new ArrayList<GastoVO>();
		for (CartaoVO cartaoVO : todosCartoes) {
			gastosRetorno.addAll(cartaoVO.getGastos());
		}

		return new ResponseEntity<List<GastoVO>>(new ArrayList<GastoVO>(gastosRetorno), HttpStatus.OK);
	}

	/**
	 * retorna todos os gastos registrados na base para o cartao especificado
	 * @param numeroCartao
	 * @return
	 */
	@RequestMapping(value = "/gastos/numerocartao/{numeroCartao}", method = RequestMethod.GET)
	public ResponseEntity<List<GastoVO>> buscarPorNumeroCartao(@PathVariable("numeroCartao") Long numeroCartao) {
		CartaoVO cartaoVO = gastoService.findByNumeroCartao(numeroCartao);
		if (cartaoVO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<GastoVO>>(new ArrayList<GastoVO>(cartaoVO.getGastos()), HttpStatus.OK);
	}
	
	/**
	 * retorna todos os gastos registrados na base para a data especificada no formato yyyy-mm-dd
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/gastos/datagasto/{data}", method = RequestMethod.GET)
	public ResponseEntity<List<GastoVO>> buscarPorData(@PathVariable("data") String data) {
		List<CartaoVO> cartoesPorData = gastoService.findByData(data);
		if (cartoesPorData == null || cartoesPorData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<GastoVO> gastosRetorno = new ArrayList<GastoVO>();
		for (CartaoVO cartaoVO : cartoesPorData) {
			gastosRetorno.addAll(cartaoVO.getGastos());
		}
		
		return new ResponseEntity<List<GastoVO>>(new ArrayList<GastoVO>(gastosRetorno), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/gastos/adiciona/{numeroCartao}", method = RequestMethod.POST)
	public ResponseEntity<GastoVO> update(@PathVariable("numeroCartao") Long numeroCartao, @RequestBody GastoVO gastoVO) {

		if (gastoVO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		CartaoVO cartaoVO = new CartaoVO(numeroCartao, gastoVO);
		
		gastoService.save(cartaoVO);

		return new ResponseEntity<GastoVO>(gastoVO, HttpStatus.OK);
	}
}
