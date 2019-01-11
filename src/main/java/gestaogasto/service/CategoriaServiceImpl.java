package gestaogasto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestaogasto.model.Categoria;
import gestaogasto.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Categoria insert(Categoria categoria) {
		return categoriaRepository.insert(categoria);
	}

	@Override
	public List<Categoria> listaCategorias() {
		return categoriaRepository.listaCategorias();
	}

}
