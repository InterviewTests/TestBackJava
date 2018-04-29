package br.com.santander.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GASTO")
public class Gasto{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_GASTO")
	private Long idGasto;

	@ManyToOne(targetEntity = Cliente.class)
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName="ID_CLIENTE")
	private Cliente cliente;

	@Column(name = "DATA", nullable = false)
	private LocalDateTime data;

	@Column(name = "TOTAL", nullable = false)
	private double total;

	public Long getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(Long idGasto) {
		this.idGasto = idGasto;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(final LocalDateTime data) {
		this.data = data;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}