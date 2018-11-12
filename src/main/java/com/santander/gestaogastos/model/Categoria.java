package com.santander.gestaogastos.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.santander.gestaogastos.repository.CategoriaRepositorio;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Categoria {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String descricao;		
	
	private CategoriaRepositorio categoriaRepositorio;

	public Categoria () {		
	}
	
	public Categoria (CategoriaRepositorio categoriaRepositorio) {		
		this.categoriaRepositorio = categoriaRepositorio;
	}
	
	public Categoria salvarCategoria(Categoria categoriaIn) {
		return this.categoriaRepositorio.save(categoriaIn);		
	}
	
	public List<Categoria> listaCategoria() {
		return this.categoriaRepositorio.findAll();
	}
	
	public Categoria pesquisarCategoria(Integer id) {
		return this.categoriaRepositorio.getOne(id);
	}
	
	public void removeCategoria(Categoria categoriaIn) {
		this.categoriaRepositorio.delete(categoriaIn);	
	}
}
