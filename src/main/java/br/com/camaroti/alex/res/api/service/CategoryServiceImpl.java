package br.com.camaroti.alex.res.api.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import br.com.camaroti.alex.res.api.domain.Category;
import br.com.camaroti.alex.res.api.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final String KEY = "category";

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Resource(name = "redisTemplate")
	private HashOperations<String, String, String> hashOperations;

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findByNameContaining(String name) throws IOException {
		return new Category(categoryRepository, hashOperations, KEY).findByNameContaining(name);
	}


	@Override
	public Category save(Category category) throws IOException {
		return new Category(categoryRepository, hashOperations, KEY).save(category);
	}


	@Override
	public Category findByName(String name) {
		return new Category(categoryRepository).findByNameIgnoreCase(name);
	}

}
