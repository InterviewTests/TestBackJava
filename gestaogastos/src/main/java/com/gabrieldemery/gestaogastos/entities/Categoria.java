package com.gabrieldemery.gestaogastos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigo;
	
	private String nome;
	
	public Long getCodigo() { return codigo; }
	public void setCodigo(Long codigo) { this.codigo = codigo; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	@Override
	public String toString() {
		return new StringBuilder("Categoria[")
				.append("codigo="+this.codigo+",")
				.append("nome="+this.nome)
				.append("]")
				.toString();
	}
}
