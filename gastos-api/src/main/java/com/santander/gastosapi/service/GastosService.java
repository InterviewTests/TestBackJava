package com.santander.gastosapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santander.gastosapi.model.Categoria;
import com.santander.gastosapi.model.Gasto;
import com.santander.gastosapi.repository.GastosRepository;

@Service
public class GastosService {
	
	@Autowired
	private GastosRepository gastosRepository;
	
	public void gastosPorCartao(Gasto gasto) {
		gastosRepository.save(gasto);
	}
	
	public List<Gasto> listagemGastos(){
		List<Gasto> gastos = new ArrayList<>();
		gastosRepository.findAll().forEach(gastos::add);
		return gastos;
	}
	
	public List<Gasto> filtroGastos(Date filtro){
		return new ArrayList<>();
	}
	
	public void categorizacaoGastos(Categoria categoria) {
	}
	
	public List<Categoria> sugestaoCategoria(){
		return new ArrayList<>();
	}
	
	public void categorizacaoAutomaticaGastos(){
		
	}
}