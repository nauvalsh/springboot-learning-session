package com.domain.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.TransactionScoped;

import com.domain.models.entities.Category;
import com.domain.models.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@TransactionScoped
public class CategoryService {
  
  @Autowired
  private CategoryRepository categoryRepository;

  public Category save(Category category){
    return categoryRepository.save(category);
  }

  public Category findOne(Long id){
    Optional<Category> category = categoryRepository.findById(id);

    if(!category.isPresent()){
      return null;
    }

    return category.get();
  }

  public Iterable<Category> findAll(){
    return categoryRepository.findAll();
  }

  public void removeOne(Long id){
    categoryRepository.deleteById(id);
  }

  
}
