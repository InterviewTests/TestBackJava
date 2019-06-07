package com.santander.gastosapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.santander.gastosapi.model.Categoria;
import com.santander.gastosapi.model.Gasto;

@Service
public class GastosService {
	
	public void gastosPorCartao(Gasto gasto) {
	}
	
	public List<Gasto> listagemGastos(){
		return new ArrayList<>();
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