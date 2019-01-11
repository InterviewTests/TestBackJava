package gestaogasto.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import gestaogasto.model.Categoria;

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Categoria> listaCategorias() {
		return mongoTemplate.findAll(Categoria.class);
	}

	@Override
	public Categoria insert(Categoria categoria) {	
		return mongoTemplate.save(categoria);	
	}

}
