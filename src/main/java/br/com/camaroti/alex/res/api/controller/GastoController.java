package br.com.camaroti.alex.res.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.camaroti.alex.res.api.model.Gasto;
import br.com.camaroti.alex.res.api.repository.GastoRepository;

@RestController
public class GastoController {
	
	@Autowired
	private GastoRepository gastoRepository;
	
	@PostMapping(path="/gastos") // Map ONLY GET Requests
	public @ResponseBody Gasto add(@RequestParam int codigousuario
			, @RequestParam String descricao, @RequestParam double valor) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Gasto n = new Gasto();
		n.setCodigousuario(codigousuario);
		n.setData(new Date());
		n.setDescricao(descricao);
		n.setValor(valor);
		Gasto newG = gastoRepository.save(n);
		return newG;
	}

	@RequestMapping(path="/gastos")
	public @ResponseBody Iterable<Gasto> getAll() {
		// This returns a JSON or XML with the users
		return gastoRepository.findAll();
	}
	

}
