package br.com.santander.gastos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.santander.gastos.controller.form.GastoForm;
import br.com.santander.gastos.model.Gasto;
import br.com.santander.gastos.model.User;
import br.com.santander.gastos.repository.CategoriaRepository;
import br.com.santander.gastos.repository.GastoRepository;
import br.com.santander.gastos.repository.UserRepository;
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
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<GastoVO>> listarGastos(@RequestParam(required = false) String data) {
	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String userName;
		if(principal instanceof UserDetails) {
			 userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		
		Optional<User> user = userRepository.findByEmail(userName);
		
		List<Gasto> listaGastos;
		if(data == null) {
			listaGastos = gastoRepository.findByIdUsuarioOrderByDataDesc(user.get().getId());

			return new ResponseEntity<>(GastoVO.converter(listaGastos), HttpStatus.OK);
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = new Date();
			try {
				date = sdf.parse(data);
			} catch (ParseException e) {
				System.out.println("Erro ao converter data: " + e.getMessage());
			}
			
			listaGastos = gastoRepository.findGastosByDate(user.get().getId(), date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			
			return new ResponseEntity<>(GastoVO.converter(listaGastos), HttpStatus.OK);
		}
			
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Transactional
	public ResponseEntity<Void> cadastrarGasto(@RequestBody List<GastoForm> listaGastos, UriComponentsBuilder uriBuilder) {
		
		for(GastoForm gasto : listaGastos){
			Gasto novoGasto = gasto.converter();
			gastoRepository.save(novoGasto);
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
