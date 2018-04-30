package br.com.santander.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartaoVO implements Serializable{

	private static final long serialVersionUID = -4117021791011630752L;

	Long numeroCartao;
	
	List<GastoVO> gastos;
	
	public CartaoVO (Long numeroCartao, List<GastoVO> gastos) {
		this.numeroCartao = numeroCartao;
		this.gastos = gastos;
	}
	
	public CartaoVO (Long numeroCartao, GastoVO gasto) {
		this.numeroCartao = numeroCartao;
		gastos = new ArrayList<GastoVO>();
		gastos.add(gasto);
	}
	
	public CartaoVO () {
		gastos = new ArrayList<GastoVO>();
	}

	/**
	 * @return the numeroCartao
	 */
	public Long getNumeroCartao() {
		return numeroCartao;
	}

	/**
	 * @param numeroCartao the numeroCartao to set
	 */
	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	/**
	 * @return the gastos
	 */
	public List<GastoVO> getGastos() {
		return gastos;
	}

	/**
	 * @param gastos the gastos to set
	 */
	public void setGastos(List<GastoVO> gastos) {
		this.gastos = gastos;
	}
	
}
