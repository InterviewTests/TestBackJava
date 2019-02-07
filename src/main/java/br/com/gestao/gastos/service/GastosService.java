package br.com.gestao.gastos.service;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.gestao.gastos.model.Gastos;

public interface GastosService {

	List<Gastos> listaDeGastos(int codigousuario, String data);
	
	Gastos detalheGasto(int codigousuario, ObjectId id);
	
	Gastos criarGasto(Gastos gastos);
	
	Gastos alterarGasto(Gastos gastos);
	
}
