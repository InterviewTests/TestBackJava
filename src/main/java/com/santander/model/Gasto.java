package com.santander.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class Gasto  implements Runnable{

	private Long id;

	private String descricao;

	private Double valor;

	private int codigoUsuario;

	private LocalDateTime data;

	public void setDescricao(String descricao) {
		this.descricao = descricao;

	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigousuario) {
		this.codigoUsuario = codigousuario;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void run() {
		
		System.out.println("incluindo " );
		
	}

}
