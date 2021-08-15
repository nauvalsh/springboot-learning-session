package com.domain.models.repositories;

import com.domain.models.entities.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
  
}
