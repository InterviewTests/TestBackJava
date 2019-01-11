package gestaogasto.service;

import java.util.List;

import gestaogasto.model.Categoria;

public interface CategoriaService {
	
	public List<Categoria> listaCategorias();
	
	public Categoria insert(Categoria categoria);

}
