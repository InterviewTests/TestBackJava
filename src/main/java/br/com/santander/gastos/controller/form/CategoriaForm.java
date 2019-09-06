package br.com.santander.gastos.controller.form;

import java.util.Optional;

import br.com.santander.gastos.model.Categoria;
import br.com.santander.gastos.model.Gasto;
import br.com.santander.gastos.repository.CategoriaRepository;
import br.com.santander.gastos.repository.GastoRepository;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriaForm {
	
	@JsonProperty("descricao")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Optional<Gasto> incluirCategoria(Optional<Gasto> gasto, GastoRepository gastoRepository, CategoriaRepository categoriaRepository) {
		
		Categoria cat = categoriaRepository.findByDescricao(this.descricao);
		
		if(cat != null){
			gasto.get().setCategoria(cat);
		}else{
			cat = new Categoria();
			cat.setDescricao(this.descricao);
			
			categoriaRepository.save(cat);
			
			gasto.get().setCategoria(cat);
		}
		
		return gasto;
	}
}
