package br.com.brunots.testes.everis.dao;

import static com.mongodb.client.model.Filters.regex;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import br.com.brunots.testes.everis.entity.CategoriaEntity;
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

	private GastoEntity getNextGastoEntity(Document next) {
		GastoEntity entity = new GastoEntity();
		entity.setId(next.getLong("_id"));
		entity.setDescricao(next.getString("descricao"));
		entity.setValor(next.getDouble("valor"));
		entity.setCodigousuario(next.getInteger("codigousuario"));
		entity.setData(next.getDate("data"));
		Document categoria = (Document) next.get("categoria");
		if (categoria != null) {
			entity.setCategoria(new CategoriaEntity(categoria.getString("categoria")));
		}
		return entity;
	}

	@Override
	public void save(GastoEntity entity) {
		Document document = new Document();
		document.put("descricao", entity.getDescricao());
		document.put("valor", entity.getValor());
		document.put("codigousuario", entity.getCodigousuario());
		document.put("data", entity.getData());
		CategoriaEntity categoria = entity.getCategoria();
		if (categoria != null) {
			document.put("categoria", new Document("categoria", categoria.getCategoria()));
		} else {
			String descricao = getCategoriaByDescricao(entity.getDescricao());
			if (descricao != null) {
				document.put("categoria", new Document("categoria", descricao));
			}
		}
		if (entity.getId() == null) {
			document.put("_id", idGenerator.incrementAndGet());
			collection.insertOne(document);
		} else {
			document.put("_id", entity.getId());
			BasicDBObject filter = new BasicDBObject("_id", entity.getId());
			BasicDBObject updateOperationDocument = new BasicDBObject("$set", document);
			collection.updateOne(filter, updateOperationDocument);
		}
	}

	private String getCategoriaByDescricao(String descricao) {
		MongoCursor<Document> cursor = collection.find(new BasicDBObject("descricao", descricao))
				.sort(new BasicDBObject("_id", -1)).limit(1).iterator();
		CategoriaEntity categoriaEntity = getNextCategoriaEntity(cursor.next());
		return categoriaEntity.getCategoria();
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

	@Override
	public GastoEntity getById(Long id) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("_id", id);
		FindIterable<Document> find = collection.find(whereQuery);
		MongoCursor<Document> cursor = find.iterator();
		if (cursor.hasNext()) {
			return getNextGastoEntity(cursor.next());
		} else {
			return null;
		}
	}

	@Override
	public List<CategoriaEntity> listarCategorias(String startsWith) {
		Pattern pattern = Pattern.compile("^" + Pattern.quote(startsWith), Pattern.CASE_INSENSITIVE);
		MongoCursor<Document> cursor = collection.find(regex("categoria.categoria", pattern)).iterator();

		List<CategoriaEntity> ret = new ArrayList<>();
		while (cursor.hasNext()) {
			ret.add(getNextCategoriaEntity(cursor.next()));
		}
		return ret;
	}

	private CategoriaEntity getNextCategoriaEntity(Document next) {
		Document categoria = (Document) next.get("categoria");
		return new CategoriaEntity(categoria.getString("categoria"));
	}

}
