package br.com.santander.gastos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.gastos.controller.form.CategoriaForm;
import br.com.santander.gastos.controller.form.GastoForm;
import br.com.santander.gastos.model.Gasto;
import br.com.santander.gastos.model.User;
import br.com.santander.gastos.repository.CategoriaRepository;
import br.com.santander.gastos.repository.GastoRepository;
import br.com.santander.gastos.repository.UserRepository;
import br.com.santander.gastos.service.GastoService;
import br.com.santander.gastos.service.UserService;
import br.com.santander.gastos.vo.GastoVO;

@RestController
@RequestMapping("/gastos")
public class GastosController {

	@Autowired
	private GastoRepository gastoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GastoService gastoService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<GastoVO>> listarGastos(@RequestParam(required = false) String data) {
	
		String userName = userService.getUsuarioLogado();

		Optional<User> user = userService.findByEmail(userName);
		
		List<Gasto> listaGastos;
		if(data == null) {
			listaGastos = gastoService.findByIdUsuarioOrderByDataDesc(user.get().getId());

			return new ResponseEntity<>(GastoVO.converter(listaGastos), HttpStatus.OK);
		}else {
			listaGastos = gastoService.findByDate(user.get().getId(), data);
			
			return new ResponseEntity<>(GastoVO.converter(listaGastos), HttpStatus.OK);
		}
			
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GastoVO> detalhar(@PathVariable Long id) {
		
		String userName = userService.getUsuarioLogado();
		
		Optional<User> user = userService.findByEmail(userName);
		
		Optional<Gasto> gasto = gastoService.findById(user.get().getId(), id);
		
		if(gasto.isPresent()) {
			return ResponseEntity.ok(new GastoVO(gasto.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> cadastrarGasto(@RequestBody List<GastoForm> listaGastos) {
		
		for(GastoForm gasto : listaGastos){
			Gasto novoGasto = gasto.converter();
			gastoService.save(novoGasto);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Void> AssociarCategoria(@PathVariable Long id, @RequestBody CategoriaForm categoria) {
		
		String userName = userService.getUsuarioLogado();
		
		Optional<User> user = userService.findByEmail(userName);
		
		Optional<Gasto> gasto = gastoService.findById(user.get().getId(), id);
		
		if(gasto.isPresent()){
			if(gasto.get().getCategoria() == null) {
				gasto = categoria.incluirCategoria(gasto, gastoRepository, categoriaRepository); 
				gastoService.save(gasto.get());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
