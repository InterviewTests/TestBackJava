package com.company.gestaogastos.domain.entity;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Gasto {
	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private Integer codigousuario;
	private Timestamp data;
	
	@ManyToOne (cascade = {CascadeType.ALL})
	private Categoria categoria;
	
	public Gasto() {
		super();
	}
	
    public Gasto(Long id, String descricao, BigDecimal valor, Integer codigousuario, Timestamp data,
			Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.codigousuario = codigousuario;
		this.data = data;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getCodigousuario() {
		return codigousuario;
	}

	public void setCodigousuario(Integer codigousuario) {
		this.codigousuario = codigousuario;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
    public String toString() {
        return String.format("Gasto{id=%s, descricao=%s, valor=%s, codigousuario=%s, data=%s}"
        		, getId(), getDescricao(), getValor(), getCodigousuario(), getData());
    }

}