package gestaogasto.repository;


import java.util.List;

import gestaogasto.model.Categoria;

public interface CategoriaRepository {
	
	public List<Categoria> listaCategorias();
	
	public Categoria insert(Categoria categoria);

}
