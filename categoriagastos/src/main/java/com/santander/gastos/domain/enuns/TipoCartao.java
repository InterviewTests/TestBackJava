package com.santander.gastos.domain.enuns;

public enum TipoCartao {
	CREDITO(1,"Crédito"),
	DEBITO(2, "Débito");
	
	private int cod;
	private String descricao;
	
	private TipoCartao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static TipoConta toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TipoConta x : TipoConta.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
