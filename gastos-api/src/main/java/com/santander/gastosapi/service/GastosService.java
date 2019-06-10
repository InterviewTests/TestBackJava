package com.santander.gastosapi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.gastosapi.model.Gasto;
import com.santander.gastosapi.repository.GastosRepository;

@Service
public class GastosService {
	
	@Autowired
	private GastosRepository gastosRepository;
	
	public void gastosPorCartao(Gasto gasto) {
		if(gasto.getCategoria()==null)
			categorizacaoAutomaticaGastos(gasto);
		gastosRepository.save(gasto);
	}
	
	public List<Gasto> listagemGastos(){
		List<Gasto> gastos = new ArrayList<>();
		gastosRepository.findAll().forEach(gastos::add);
		return gastos;
	}
	
	public Gasto getGasto(String gastoID){
		return gastosRepository.findOne(gastoID);
	}
	
	public List<Gasto> filtroGastos(String filtro){
		List<Gasto> gastos = listagemGastos();
		List<Gasto> gastosDiaEspecifico = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		gastos.forEach(g->{
			if(g.getData()!=null){
				String dataValue = simpleDateFormat.format(g.getData());
				
				if(dataValue.equals(filtro))
					gastosDiaEspecifico.add(g);
			}
		});
		return gastosDiaEspecifico;
	}
	
	public void categorizacaoGastos(String categoria, String gastoID) {
		Gasto gasto = gastosRepository.findOne(gastoID);
		gasto.setCategoria(categoria);
		gastosRepository.save(gasto);
	}
	
	public LinkedHashSet<String> sugestaoCategoria(){
		LinkedHashSet<String> categoria = new LinkedHashSet<>();
		
		gastosRepository.findAll().forEach(g->{
			if(g.getCategoria()!=null)
				categoria.add(g.getCategoria());
		});
		
		return categoria;
	}
	
	private void categorizacaoAutomaticaGastos(Gasto gasto){
		List<Gasto> gastos = listagemGastos();
		
		// busca todos os gastos do cliente
		List<Gasto> gastosUserFilter1 = gastos.stream().filter(g -> g.getCodigousuario()==g.getCodigousuario()).collect(Collectors.toList());
		
		// verifica se a descrição do gasto atual é igual a descrição de algum outro gasto
		List<Gasto> gastosUserFilter2 = gastosUserFilter1.stream().filter(g -> g.getDescricao().equals(gasto.getDescricao())).collect(Collectors.toList());
		
		if(gastosUserFilter2.size()>0)
			gasto.setCategoria(gastosUserFilter2.get(0).getCategoria());
	}
}