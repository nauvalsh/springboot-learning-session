package com.domain.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.TransactionScoped;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.models.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@TransactionScoped
public class ProductService {
  
  @Autowired
  private ProductRepository productRepository;

  public Product save(Product product){

    return productRepository.save(product);
  }

  public Product findOne(Long id){
    Optional<Product> product = productRepository.findById(id);

    if(!product.isPresent()){
      return null;
    }

    return product.get();
  }

  public Iterable<Product> findAll(){
    return productRepository.findAll();
  }

  public void removeOne(Long id){
    productRepository.deleteById(id);
  }

  public int remove(Long id){
    return productRepository.deleteByIds(id);
  }

  public List<Product> findByNameContains(String name){
    return productRepository.findByNameContains(name);
  }

  public void addSupplier(Supplier supplier, Long productId){
    Product product = findOne(productId);
    
    if(product == null){
      throw new RuntimeException("Product with ID: "+ productId+" not found");
    }

    product.getSupplier().add(supplier);

    save(product);
    
  }
  
}
