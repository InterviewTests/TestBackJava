package br.com.santander.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.santander.app.data.redis.GenerateKeys;
import br.com.santander.app.model.Category;

@Component
public class RedisTestes {

	@Autowired
	private RedisTemplate<String, String> redis;

	@Autowired
	private GenerateKeys keys;

	public void insert(final Category category) {
		final String json = new Gson().toJson(category);
		final String key = keys.keyForCategoryId(category.getId());

		redis.opsForValue().set(key, json);
		System.out.println(redis.opsForValue().get(key));
		System.out.println(redis.opsForValue().get(keys.keyForCategoryId(232L)));

		final String keyDeCategoriaQueNaoExiste = keys.keyForCategoryId(9999L);
		final String valorNull = redis.opsForValue().get(keyDeCategoriaQueNaoExiste);
		System.out.println(String.format("key:%s existe? %s", keyDeCategoriaQueNaoExiste, valorNull));
	}

}
