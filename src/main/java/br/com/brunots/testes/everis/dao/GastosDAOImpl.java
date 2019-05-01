package br.com.brunots.testes.everis.dao;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;

import br.com.brunots.testes.everis.helper.MongoDBHelper;

@Repository
public class GastosDAOImpl implements GastosDAO {
	
	@SuppressWarnings("unused")
	private MongoCollection<Document> collection;
	
	public GastosDAOImpl() {
		collection = MongoDBHelper.getDatabase().getCollection("gastos");
	}

}
