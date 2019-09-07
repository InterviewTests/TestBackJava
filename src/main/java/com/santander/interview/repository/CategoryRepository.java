package com.santander.interview.repository;

import com.santander.interview.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    public List<Category> findByDetail(String detail);
    public List<Category> findByDetailLike(String detail);
}
