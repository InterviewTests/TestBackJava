package com.santander.gestaogastos.serviceImpl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.gestaogastos.exception.GastosException;
import com.santander.gestaogastos.model.Gasto;
import com.santander.gestaogastos.repository.GastosRepositorio;
import com.santander.gestaogastos.service.GastosServico;

@Service
public class GastosServicoImpl implements GastosServico<Gasto> {
	

	@Autowired
 	private GastosRepositorio gastosRepositorio;
	
	@Override
	public List<Gasto> listaGastos(){
		
		Gasto gasto = new Gasto(gastosRepositorio);
		
		return gasto.listaGastos();
	}
	
	@Override
	public Gasto salvarGasto(Gasto gastoIn) throws GastosException {
		Gasto gasto = new Gasto(gastosRepositorio);
		
		return gasto.salvarGasto(gastoIn);	
	}
	
	@Override
	public <T> Object pesquisarGasto(Integer id) throws GastosException {
		Gasto gasto = new Gasto(gastosRepositorio);

		return gasto.pesquisarGasto(id);
	}

	@Override
	public void removeGasto(Gasto gastoIn) throws GastosException {
		Gasto gasto = new Gasto(gastosRepositorio);

		gasto.removeGasto(gastoIn);
	}

	public List<String> buscarCategoriasDoCliente(Gasto gastoIn) {
		Gasto gasto = new Gasto(gastosRepositorio);
		
		return gasto.buscarCategoriasDoCliente(gastoIn);
	}
	
	public List<Gasto> gastosDoClientePorData(String dataIn) throws ParseException {
		Gasto gasto = new Gasto(gastosRepositorio);
		
		return gasto.gastosDoClientePorData(dataIn);
	}

}
