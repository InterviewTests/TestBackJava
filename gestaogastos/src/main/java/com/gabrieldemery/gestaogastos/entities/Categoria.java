package com.gabrieldemery.gestaogastos.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("categoria")
public class Categoria {
	
	@Id
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
