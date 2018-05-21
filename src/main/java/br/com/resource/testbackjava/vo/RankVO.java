package br.com.resource.testbackjava.vo;

import java.io.Serializable;
@Deprecated
public class RankVO implements Serializable, Comparable<RankVO>{

	private static final long serialVersionUID = 1L;

	private String categoria;
	private Long counter;
	
	
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
	public int compareTo(RankVO arg0) {
		return this.counter.compareTo(arg0.getCounter());
	}

}
