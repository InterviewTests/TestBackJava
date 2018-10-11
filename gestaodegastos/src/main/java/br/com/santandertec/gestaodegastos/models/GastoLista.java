package br.com.santandertec.gestaodegastos.models;

import java.util.ArrayList;
import java.util.List;

public class GastoLista {
	
	private List<Gasto> gastos;
	
	public GastoLista() {
		this.gastos = new ArrayList<>();
	}

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}
	
}
