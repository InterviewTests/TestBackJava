package br.com.santandertec.gestaodegastos.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.santandertec.gestaodegastos.daos.GastoDAO;
import br.com.santandertec.gestaodegastos.models.Cliente;
import br.com.santandertec.gestaodegastos.models.Gasto;
import br.com.santandertec.gestaodegastos.models.GastoLista;
import br.com.santandertec.gestaodegastos.validations.GastoValidation;

@Controller
public class GastoController {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private GastoDAO gastoDAO;
	
	@RequestMapping("/")
	public ModelAndView listar(@AuthenticationPrincipal Cliente cliente) {
		
		StringBuilder url = new StringBuilder();
		url.append(request.getRequestURL().toString().split(request.getContextPath())[0]);
		url.append(request.getContextPath());
		url.append("/servico/gastos/cliente/");
		url.append(cliente.getId());
		
		GastoLista response = restTemplate.getForObject(url.toString(), GastoLista.class);
		
		List<Gasto> gastosDoCliente = response.getGastos();
		
		ModelAndView modelAndView = new ModelAndView("formListaGasto");
		modelAndView.addObject("gastos", gastosDoCliente);
		
		return modelAndView;
	}
	
	@RequestMapping("/pesquisar")
	public ModelAndView pesquisarGastosPorData(String data, @AuthenticationPrincipal Cliente cliente) throws ParseException {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		List<Gasto> gastosDoClientePorData = this.gastoDAO.findByDate(formatador.parse(data), cliente.getId());
		
		ModelAndView modelAndView = new ModelAndView("formListaGasto");
		modelAndView.addObject("gastos", gastosDoClientePorData);
		
		return modelAndView;
	}
	
	@RequestMapping("/detalhar/{id}")
	public ModelAndView detalhar(@PathVariable("id") Integer id) {
		
		StringBuilder url = new StringBuilder();
		url.append(request.getRequestURL().toString().split(request.getContextPath())[0]);
		url.append(request.getContextPath());
		url.append("/servico/gastos/");
		url.append(id);
		
		Gasto gasto = restTemplate.getForObject(url.toString(), Gasto.class);
		
		List<String> categorias = this.gastoDAO.buscarCategoriasDoCliente(gasto.getUsuario().getId());
		
		ModelAndView modelAndView = new ModelAndView("/formDetalharGasto"); 
		
		modelAndView.addObject("gasto", gasto);
		modelAndView.addObject("categorias", categorias);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrar/categoria", method=RequestMethod.POST)
	public ModelAndView cadastrarCategoria(@Valid Gasto gasto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return this.validarDetalharGasto(gasto);
		}
		
		StringBuilder url = new StringBuilder();
		url.append(request.getRequestURL().toString().split(request.getContextPath())[0]);
		url.append(request.getContextPath());
		url.append("/servico/gastos/categoria");
		
		restTemplate.postForObject(url.toString(), gasto, Gasto.class);
		
		redirectAttributes.addFlashAttribute("mensagem", "A categoria do Gasto foi cadastrada com Sucesso!");
		
		return new ModelAndView("redirect:/");
		
	}
	
	@RequestMapping("/detalhar/validar")
	public ModelAndView validarDetalharGasto(Gasto gasto) {
		return new ModelAndView("/formDetalharGasto");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.addValidators(new GastoValidation());
	}
	
}
