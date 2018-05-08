package br.com.santander.app.repository.redis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import br.com.santander.app.model.Category;
import br.com.santander.app.support.RedisKeysHelper;

@Repository
public class CategoryRepositoryRedisImpl implements CategoryRepositoryRedis{

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public Category insert(final Category category) {
		final String categoryKey = RedisKeysHelper.generateCategoriesKey(category.getId());
		redisTemplate.opsForValue().set(categoryKey, new Gson().toJson(category));
		return new Gson().fromJson(redisTemplate.opsForValue().get(categoryKey), Category.class);
	}

	@Override
	public Category findByDescriptionEqualsIgnoreCase(final String description) {
		for (final String keys : redisTemplate.keys("categories:*")) {
			final Category category= new Gson().fromJson(redisTemplate.opsForValue().get(keys), Category.class);
			if(description.equalsIgnoreCase(category.getDescription())) {
				return category;
			}
		}
		return null;
	}

	@Override
	public List<Category> findCategorySuggestionByDescription(final String description) {
		final List<Category> listCategory= new ArrayList<>();
		for (final String keys : redisTemplate.keys("categories:*")) {
			listCategory.add(new Gson().fromJson(redisTemplate.opsForValue().get(keys), Category.class));
		}
		return findAllSugestionsCategories(listCategory, description);
	}

	public List<Category> findAllSugestionsCategories(final List<Category> listCategories, final String description) {
		final List<Category> listOfSuggested = new ArrayList<>();
		for (final Category category : listCategories) {
			if(category.getDescription().contains(description)) {
				listOfSuggested.add(category);
			}
		}
		return listOfSuggested;
	}
}
