package com.santander.gestaogastos.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.santander.gestaogastos.exception.GastosException;
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
	
	public Categoria pesquisarCategoria(Integer id) throws GastosException {
		
		if (this.categoriaRepositorio.getOne(id) == null) {
			throw new GastosException("Categoria para ser excluida não existe !");
		}
		return this.categoriaRepositorio.getOne(id);
	}
	
	public void removeCategoria(Categoria categoriaIn) throws GastosException {
		
		Categoria categoria = (Categoria) this.pesquisarCategoria(id);

		if (categoria != null) {
			throw new GastosException("Categoria para ser excluida não existe !");
		}
		
		this.categoriaRepositorio.delete(categoriaIn);	
	}
	public void validate () throws GastosException {
		
		if (this.descricao != null && !"".equals(this.descricao)) {
			throw new GastosException(" É necessário informar as descrição da categoria");
		}

	}
}
