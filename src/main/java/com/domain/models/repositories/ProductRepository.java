package com.domain.models.repositories;

import java.util.List;

import javax.websocket.server.PathParam;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
  
  List<Product> findByNameContains(String name);

  @Modifying
  @Query("delete from Product e where e.id = ?1")
  int deleteByIds(Long id);

  @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
  public List<Product> findProductByName(@PathParam("name") String name);

  @Query("SELECT p FROM Product p WHERE p.id IN (:ids)")
  public List<Product> findProductByIds(@PathParam("name") long[] ids);
  
  @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
  public List<Product> findProductByCategory(@PathParam("categoryId") long categoryId);
  
  @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
  public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);


}
