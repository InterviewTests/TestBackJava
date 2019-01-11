package gestaogasto.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import gestaogasto.model.Gasto;

@Repository
public class GastoRepositoryImpl implements GastoRepository{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Gasto> listaGastosMaisAtuais() {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC,"data"));		
		
		return mongoTemplate.find(query, Gasto.class);
	}

	@Override
	public List<Gasto> listaGastosPorData(LocalDate dataGasto) {
		
		Query query = new Query().addCriteria(Criteria.where("data").is(dataGasto));		
		return mongoTemplate.find(query, Gasto.class);
	}

	@Override
	public Gasto insert(Gasto gasto) {
		return mongoTemplate.save(gasto);
	}

	@Override
	public Gasto categorizaGasto(String idGasto, String idCategoria) {
		Query query = new Query().addCriteria(Criteria.where("id").is(idGasto));
		
		mongoTemplate.updateFirst(query, Update.update("codigoCategoria", idCategoria),Gasto.class);
		
		Query queryUpdated = new Query().addCriteria(Criteria.where("id").is(idGasto));		
		return mongoTemplate.findOne(queryUpdated, Gasto.class);
	}

	@Override
	public Gasto buscaUltimaCategoriaUsuarioGasto(Integer codigoUsuario) {
	
		Query query = new Query().addCriteria(
		    new Criteria().andOperator(
		        Criteria.where("codigoUsuario").is(codigoUsuario),
		        Criteria.where("codigoCategoria").ne(null)))
				.with(new Sort(Sort.Direction.DESC,"data"));
		return mongoTemplate.findOne(query, Gasto.class);
	}

}
