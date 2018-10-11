package br.com.santandertec.gestaodegastos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.santandertec.gestaodegastos.daos.RoleDAO;
import br.com.santandertec.gestaodegastos.models.Role;
import br.com.santandertec.gestaodegastos.validations.RoleValidation;

@Controller
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleDAO roleDAO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listarRoles() {
		ModelAndView modelAndView = new ModelAndView("roles/formLista");
		modelAndView.addObject("roles", roleDAO.buscarTodas());
		return modelAndView;
	}
	
	@RequestMapping("/incluir")
	public ModelAndView abrirFormularioInclusao(Role role) {
		return new ModelAndView("roles/formInclusao");
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView cadastrarRole(@Valid Role role, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return this.abrirFormularioInclusao(role);
		}
		
		this.roleDAO.gravar(role);
		redirectAttributes.addFlashAttribute("mensagem", "Role cadastrada com Sucesso!");
		return new ModelAndView("redirect:/roles");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.addValidators(new RoleValidation());
	}
	
}
