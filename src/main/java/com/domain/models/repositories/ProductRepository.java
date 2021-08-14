package com.domain.models.repositories;

import java.util.List;

import com.domain.models.entities.Product;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
  
  List<Product> findByNameContains(String name);

  @Modifying
  @Query("delete from Product e where e.id = ?1")
  int deleteByIds(Long id);
}
