package br.com.santander.gastos.vo;

import java.util.List;
import java.util.stream.Collectors;

import br.com.santander.gastos.model.Categoria;

public class CategoriaVO {

	private Long id;
	private String descricao;
	
	public CategoriaVO(Categoria categoria) {
		this.id = categoria.getId();
		this.descricao = categoria.getDescricao();
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
	
	public static List<CategoriaVO> converter(List<Categoria> listaCategorias) {
		return listaCategorias.stream().map(CategoriaVO::new).collect(Collectors.toList());
	}
}
