package br.com.resource.testbackjava.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.resource.testbackjava.data.CreditCardOutgoing;
import br.com.resource.testbackjava.exception.MensagemTratadaException;
import br.com.resource.testbackjava.service.HomeService;
import br.com.resource.testbackjava.service.LoginService;
import br.com.resource.testbackjava.vo.CreditCardOutgoingFilterVO;
import br.com.resource.testbackjava.vo.CreditCardOutgoingVO;
import br.com.resource.testbackjava.vo.FilterVO;
import br.com.resource.testbackjava.vo.LoginVO;
import br.com.resource.testbackjava.vo.UserVO;

@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	private final String VIEW_HOME = "index.html";

	@Autowired
	private LoginService loginService;

	@Autowired
	private HomeService creditCardOutgoingService;

	@ResponseBody
	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView home(final HttpServletRequest request) {

		ModelAndView model = new ModelAndView(VIEW_HOME);

		return model;
	}

	@ResponseBody
	@RequestMapping(value = "entrar", method = { RequestMethod.GET, RequestMethod.POST }, consumes = { "application/json" })
	public Object home(@RequestBody LoginVO login) throws MensagemTratadaException {
		UserVO user = loginService.login(login);
		System.out.println(user);
		return user;
	}

	@ResponseBody
	@RequestMapping(value = "listarGastos", method = { RequestMethod.GET, RequestMethod.POST }, consumes = { "application/json" })
	public Object listar(@RequestBody FilterVO filtro) throws MensagemTratadaException {
		List<CreditCardOutgoing> result = creditCardOutgoingService.filtrar(filtro);

		return result;
	}

	@ResponseBody
	@RequestMapping(value = "findById", method = { RequestMethod.GET, RequestMethod.POST }, consumes = { "application/json" })
	public Object findById(@RequestBody CreditCardOutgoingFilterVO filtro) throws MensagemTratadaException {
		CreditCardOutgoingVO result = creditCardOutgoingService.findById(filtro.getId());
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "salvarCategoria", method = { RequestMethod.GET, RequestMethod.POST }, consumes = { "application/json" })
	public Object salvarCategoria(@RequestBody CreditCardOutgoingFilterVO filtro) throws MensagemTratadaException {
		creditCardOutgoingService.salvarCategoria(filtro);
		return null;
	}
}
