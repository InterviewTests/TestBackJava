package com.santander.gastos.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santander.gastos.domain.Cliente;
import com.santander.gastos.dto.ClienteDTO;
import com.santander.gastos.services.ClienteService;

@RestController
@RequestMapping(value="/api/v1/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET )
	public ResponseEntity<?> find(@PathVariable Integer id){
		
		Cliente cli = service.buscarPorId(id);
		ClienteDTO dto = new ClienteDTO(cli);
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(method = RequestMethod.GET )
	public ResponseEntity<?> findAll(){
		
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listdto = list.stream().map( obj -> new ClienteDTO(obj) ).collect(Collectors.toList()) ;
		return ResponseEntity.ok().body(listdto);
	}
	
	
}
