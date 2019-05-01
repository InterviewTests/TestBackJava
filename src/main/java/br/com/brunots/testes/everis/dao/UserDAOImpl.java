package br.com.brunots.testes.everis.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import br.com.brunots.testes.everis.entity.UserEntity;
import br.com.brunots.testes.everis.helper.MongoDBHelper;

@Repository
public class UserDAOImpl implements UserDAO {

	private MongoCollection<Document> collection;

	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	@Autowired
	private PasswordEncoder encoder;

	public UserDAOImpl() {
		collection = MongoDBHelper.getDatabase().getCollection("user");
	}

	@Override
	public void save(UserEntity entity) {
		Document document = new Document();
		document.put("codigousuario", entity.getCodigousuario());
		document.put("username", entity.getUsername());
		document.put("password", entity.getPassword());
		collection.insertOne(document);
		inMemoryUserDetailsManager.createUser(User.withUsername(entity.getUsername())
				.password(encoder.encode(entity.getPassword())).roles("USER").build());
	}

	@PostConstruct
	public void atualizarSecurityConfig() {
		for (UserEntity user : listAll()) {
			inMemoryUserDetailsManager.createUser(User.withUsername(user.getUsername())
					.password(encoder.encode(user.getPassword())).roles("USER").build());
		}
	}

	@Override
	public List<UserEntity> listAll() {
		List<UserEntity> ret = new ArrayList<>();
		FindIterable<Document> find = collection.find();
		MongoCursor<Document> cursor = find.iterator();
		while (cursor.hasNext()) {
			Document next = cursor.next();
			UserEntity entity = new UserEntity();
			entity.setCodigousuario(next.getInteger("codigousuario"));
			entity.setUsername(next.getString("username"));
			entity.setPassword(next.getString("password"));
			ret.add(entity);
		}
		return ret;
	}

	@Override
	public UserEntity fingByUsername(String username) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("username", username);
		FindIterable<Document> find = collection.find(whereQuery);
		MongoCursor<Document> cursor = find.iterator();
		Document next = cursor.next();
		UserEntity entity = new UserEntity();
		entity.setCodigousuario(next.getInteger("codigousuario"));
		entity.setUsername(next.getString("username"));
		entity.setPassword(next.getString("password"));
		return entity;
	}

}
