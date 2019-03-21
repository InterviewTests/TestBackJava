package br.com.camaroti.alex.res.api.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.camaroti.alex.res.api.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@RedisHash("category")
public @Data class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Category() {
		
	}
	
	public Category(String name) {
		this.name = name;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int cod;
	private String name;
	
	
	@Transient @Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private CategoryRepository categoryRepository;
	
	@Transient @Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private HashOperations hashOperations;
	
	@Transient @Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private String KEY;
	
	public Category(CategoryRepository categoryRepository, HashOperations hashOperations, String KEY) {
		this.categoryRepository = categoryRepository;
		this.hashOperations = hashOperations;
		this.KEY = KEY;
	}
	
	public Category(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	
	public Category save(Category category) throws IOException {
		Category newCategory = categoryRepository.save(category);
		updateRedisCategoriesList(newCategory);
		return newCategory;
	}
	
	public Category findByNameIgnoreCase(String name) {
		return categoryRepository.findByNameIgnoreCase(name);
	}
	
	public List<Category> findByNameContaining(String name) throws IOException {
		ObjectMapper objectMapper = checkIfRedisIsEmpty();
		Object listCategories = hashOperations.get(KEY, "1");
		List<Category> categoriesFound = new ArrayList<>();
		List<Category> categories = Arrays.asList(objectMapper.readValue(listCategories.toString(), Category[].class));
		//Verify the categories list and return the similar ones.
		for (Category category : categories) {
			if(category.getName().trim().toLowerCase().contains(name.trim().toLowerCase())) {
				categoriesFound.add(category);
			}
		}
		return categoriesFound;
	}
	
	private void updateRedisCategoriesList(Category newCategory)
			throws IOException, JsonParseException, JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		//Recover categories from Redis
		String redisCategories = hashOperations.get(KEY, "1").toString();
		//Convert the string to list of objects
		List<Category> categories = new ArrayList<>(Arrays.asList(objectMapper.readValue(redisCategories, Category[].class)));
		//Add a new Object
		categories.add(newCategory);
		//Convert and update Redis with the new data.
		String categoriesUpdated = objectMapper.writeValueAsString(categories);		
		hashOperations.put(KEY, "1", categoriesUpdated);
	}
	

	private ObjectMapper checkIfRedisIsEmpty() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		if (hashOperations.size(KEY) == null || hashOperations.size(KEY) == 0) {
			Iterable<Category> categories = categoryRepository.findAll();
			String json = objectMapper.writeValueAsString(categories);
			hashOperations.put(KEY, "1", json);
		}
		
		return objectMapper;
	}
	
}
