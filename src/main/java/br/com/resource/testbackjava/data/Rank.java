package br.com.resource.testbackjava.data;

import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Rank implements Comparable<Rank>{

//	@PrimaryKeyColumn(ordinal=0, type = PrimaryKeyType.PARTITIONED)
	@PrimaryKey
	private String categoria;
	
//	@PrimaryKeyColumn(ordinal=1, type = PrimaryKeyType.PARTITIONED)
	@Indexed
	private String descricao;
	
	private Long counter; // type counter

	public Rank() {
	}

	public Rank(String descricao, String categoria) {
		this.descricao = descricao;
		this.categoria = categoria;
		this.counter = 1L;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long counter) {
		this.counter = counter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rank other = (Rank) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rank [descricao=" + descricao + ", categoria=" + categoria + ", counter=" + counter + "]";
	}

	@Override
	public int compareTo(Rank arg0) {
		return arg0.getCounter().compareTo(this.counter);
	}

	

}
