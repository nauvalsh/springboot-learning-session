package com.domain.models.repositories;

import com.domain.models.entities.Supplier;

import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
  
}
