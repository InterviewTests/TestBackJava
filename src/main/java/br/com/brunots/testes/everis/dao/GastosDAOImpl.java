package br.com.brunots.testes.everis.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import br.com.brunots.testes.everis.entity.GastoEntity;
import br.com.brunots.testes.everis.helper.MongoDBHelper;

@Repository
public class GastosDAOImpl implements GastosDAO {

	private MongoCollection<Document> collection;

	private static AtomicLong idGenerator;

	public GastosDAOImpl() {
		collection = MongoDBHelper.getDatabase().getCollection("gastos");
		idGenerator = new AtomicLong(getLastId());
	}

	private long getLastId() {
		MongoCursor<Document> cursor = collection.find().sort(new BasicDBObject("_id", -1)).limit(1).iterator();
		if (cursor.hasNext()) {
			return cursor.next().getLong("_id");
		} else {
			return 0;
		}
	}

	@Override
	public void save(GastoEntity entity) {
		Document document = new Document();
		document.put("_id", idGenerator.incrementAndGet());
		document.put("descricao", entity.getDescricao());
		document.put("valor", entity.getValor());
		document.put("codigousuario", entity.getCodigousuario());
		document.put("data", entity.getData());
		collection.insertOne(document);
	}

	@Override
	public List<GastoEntity> listAll() {
		List<GastoEntity> ret = new ArrayList<>();
		FindIterable<Document> find = collection.find();
		MongoCursor<Document> cursor = find.iterator();
		while (cursor.hasNext()) {
			ret.add(getNextGastoEntity(cursor.next()));
		}
		return ret;
	}

	@Override
	public List<GastoEntity> listAllByCodigousuario(Integer codigousuario) {
		List<GastoEntity> ret = new ArrayList<>();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("codigousuario", codigousuario);
		FindIterable<Document> find = collection.find(whereQuery);
		MongoCursor<Document> cursor = find.iterator();
		while (cursor.hasNext()) {
			ret.add(getNextGastoEntity(cursor.next()));
		}
		return ret;
	}

	@Override
	public List<GastoEntity> listAllByCodigousuarioWithDate(Integer codigousuario, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();

		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();

		List<GastoEntity> ret = new ArrayList<>();

		List<BasicDBObject> queryClauses = new ArrayList<>();
		queryClauses.add(new BasicDBObject("codigousuario", codigousuario));
		queryClauses.add(new BasicDBObject("data", new BasicDBObject("$gte", start).append("$lte", end)));

		BasicDBObject andQuery = new BasicDBObject();
		andQuery.put("$and", queryClauses);

		FindIterable<Document> find = collection.find(andQuery);
		MongoCursor<Document> cursor = find.iterator();
		while (cursor.hasNext()) {
			ret.add(getNextGastoEntity(cursor.next()));
		}
		return ret;
	}

	private GastoEntity getNextGastoEntity(Document next) {
		GastoEntity entity = new GastoEntity();
		entity.setId(next.getLong("_id"));
		entity.setDescricao(next.getString("descricao"));
		entity.setValor(next.getDouble("valor"));
		entity.setCodigousuario(next.getInteger("codigousuario"));
		entity.setData(next.getDate("data"));
		return entity;
	}

}
