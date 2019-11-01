package com.gabrieldemery.gestaogastos.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_gasto")
public class Gasto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigo;

	private Long codigousuario;

	private Date data;

	private String categoria;

	private String descricao;

	private Double valor;
	
	public Long getCodigo() { return codigo; }
	public void setCodigo(Long codigo) { this.codigo = codigo; }
	
	public Long getCodigousuario() { return codigousuario; }
	public void setCodigousuario(Long codigousuario) { this.codigousuario = codigousuario; }
	
	public Date getData() { return data; }
	public void setData(Date data) { this.data = data; }
	
	public String getCategoria() { return categoria; }
	public void setCategoria(String categoria) { this.categoria = categoria; }
	
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	
	public Double getValor() { return valor; }
	public void setValor(Double valor) { this.valor = valor; }

}
