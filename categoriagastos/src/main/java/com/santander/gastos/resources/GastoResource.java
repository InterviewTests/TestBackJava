package com.santander.gastos.resources;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.santander.gastos.domain.Movimento;
import com.santander.gastos.dto.GastosDTO;
import com.santander.gastos.services.MovimentoService;

@RestController
@RequestMapping(value="/api/v1/gastos")
public class GastoResource {
	@Autowired
	private MovimentoService service;
	
	@RequestMapping(value="/cliente/{codigoCliente}", method=RequestMethod.GET)
	public ResponseEntity<Page<GastosDTO>> findPage(
			@PathVariable(value="codigoCliente") Long codigoCliente, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="data") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		
		Page<Movimento> list = service.findMovimentoByCodCli(codigoCliente, page, linesPerPage, orderBy, direction) ;
		Page<GastosDTO> listDto = list.map(obj -> new GastosDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/cliente/{codigoCliente}/{data}", method=RequestMethod.GET)
	public ResponseEntity<Page<GastosDTO>> findPageData(
			@PathVariable(value="codigoCliente") Long codigoCliente, 
			@PathVariable(value="data") String strdata,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="data") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(strdata);
		
		Page<Movimento> list = service.findMovimentoByCodCliAndDate(codigoCliente, date, page, linesPerPage, orderBy, direction);
		Page<GastosDTO> listDto = list.map(obj -> new GastosDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody GastosDTO objDto) throws ParseException {
		Movimento obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody GastosDTO objDto, @PathVariable Integer id) {
		objDto.setId(id);
		service.update(objDto);
		return ResponseEntity.noContent().build();
	}
	
}
