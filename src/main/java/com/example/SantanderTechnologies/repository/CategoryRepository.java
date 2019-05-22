package com.example.SantanderTechnologies.repository;

import com.example.SantanderTechnologies.model.SpendingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<SpendingCategory, Long> {

    Optional<SpendingCategory> findByCategoryName(String categoryName);
}
