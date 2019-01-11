package gestaogasto.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestaogasto.model.Categoria;
import gestaogasto.model.Gasto;
import gestaogasto.response.Response;
import gestaogasto.service.GastoService;

@RestController
@RequestMapping(value="/santander")
public class GastoController {
	
	private static final String TODOS_ATUAIS = "/gasto/maisatuais";
	private static final String INSERIR_GASTO = "/gasto/inserir";
	private static final String CATEGORIZA_GASTO = "/gasto/categoriza";
	private static final String AUTO_CATEGORIZA_GASTO = "/gasto/auto_categoriza";
	private static final String GASTOS_POR_DATA = "/gasto/pordata";
	
	@Autowired
	private GastoService gastoService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value=TODOS_ATUAIS)
	public ResponseEntity<Response> getListaTodosGastosMaisAtuais() {	
		Response response = new Response<>();
		response.setData(this.gastoService.listaGastosMaisAtuais());	
		return ResponseEntity.ok(response);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value=GASTOS_POR_DATA)
	public ResponseEntity<Response<List<Gasto>>> getListaGastosPorData(@Valid @RequestParam("data") String dataGasto) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataFormatada = LocalDate.parse(dataGasto, formatter);
		
		Response<List<Gasto>> response = new Response<List<Gasto>>();
		response.setData((List<Gasto>) this.gastoService.listaGastosPorData(dataFormatada));		
		return ResponseEntity.ok(response);		
	}
	
	@PostMapping(value=INSERIR_GASTO)
	public ResponseEntity<Response<Gasto>> inserirGasto(@Valid @RequestBody Gasto gasto, BindingResult result) {
		
		Response<Gasto> response = new Response<Gasto>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(erros -> response.getErrors().add(erros.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		gasto = gastoService.insert(gasto);
		response.setData(gasto);
				
		return ResponseEntity.ok(response);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	}
	
	@PostMapping(value=AUTO_CATEGORIZA_GASTO)
	public ResponseEntity<Response<Gasto>> autoCategorizaGasto(@Valid @RequestBody Gasto gasto, BindingResult result) {
		
		Response<Gasto> response = new Response<Gasto>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(erros -> response.getErrors().add(erros.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		gasto = gastoService.autoCategorizaGasto(gasto);
		response.setData(gasto);
				
		return ResponseEntity.ok(response);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	}
	
	@PostMapping(value=CATEGORIZA_GASTO)
	public ResponseEntity<Response<Gasto>> categorizaGasto(@Valid @RequestParam("idGasto") String idGasto, @RequestParam("idCategoria") String idCategoria) {
		
		Response<Gasto> response = new Response<Gasto>();				
		response.setData(gastoService.categorizaGasto(idGasto, idCategoria));
		
		return ResponseEntity.ok(response);			
	}
	

}
