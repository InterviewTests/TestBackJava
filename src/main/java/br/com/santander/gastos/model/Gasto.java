package br.com.santander.gastos.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Gasto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private Long idUsuario;
	
	private LocalDateTime data;
	
	private double valor;
	
	private String descricao;
	
	@ManyToOne
	private Categoria categoria;
	
	
	public Gasto(){
		
	}
	
	public Gasto(Long idUsuario, LocalDateTime data, double valor, String descricao, Categoria categoria) {
		this.idUsuario = idUsuario;
		this.data = data;
		this.valor = valor;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
