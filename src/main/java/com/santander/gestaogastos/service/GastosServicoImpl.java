package com.santander.gestaogastos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.gestaogastos.model.Gasto;
import com.santander.gestaogastos.repository.GastosRepositorio;

@Service
public class GastosServicoImpl implements GastosServico<Gasto> {
	

	@Autowired
 	private GastosRepositorio gastosDAO;
	
	@Override
	public List<Gasto> listaGastos(){
		List<Gasto> gastosDoCliente = gastosDAO.findAll();
		return gastosDoCliente;
	}
	
	@Override
	public Gasto salvarGasto(Gasto gastoIn) {
		return this.gastosDAO.save(gastoIn);		
	}
	
	@Override
	public <T> Object pesquisarGasto(Integer id) {
		return this.gastosDAO.getOne(id);
	}
	
	@Override
	public void removeGasto(Gasto gastoIn) {
		this.gastosDAO.delete(gastoIn);	
	}
	
	public List<String> buscarCategoriasDoCliente(Gasto gastoIn) {
		
		return this.gastosDAO.buscarCategoriasDoCliente(gastoIn.getUsuario().getId());
	}
	
	public List<Gasto> gastosDoClientePorData(String dataIn) throws ParseException {
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
		List<Gasto> gastosDoClientePorData = this.gastosDAO.findByDate(formatador.parse(dataIn.replace("-", "/")));
 		
		return gastosDoClientePorData;
	}

}
