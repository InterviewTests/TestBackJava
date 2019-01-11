package gestaogasto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestaogasto.model.Categoria;
import gestaogasto.response.Response;
import gestaogasto.service.CategoriaService;


@RestController
@RequestMapping(value="/santander")
public class CategoriaController {
	
	private static final String INSERT = "/categoria/insert";
	private static final String ALL = "/categoria/all";
	
	@Autowired
	private CategoriaService categoriaService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value=ALL)
	public ResponseEntity<Response> getCategorias() {	
		Response response = new Response<>();
		response.setData(this.categoriaService.listaCategorias());	
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value=INSERT)
	public ResponseEntity<Response<Categoria>> inserirCategoria(@Valid @RequestBody Categoria categoria, BindingResult result) {
		
		Response<Categoria> response = new Response<Categoria>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(erros -> response.getErrors().add(erros.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		categoria = categoriaService.insert(categoria);
		response.setData(categoria);
				
		return ResponseEntity.ok(response);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	}


}
