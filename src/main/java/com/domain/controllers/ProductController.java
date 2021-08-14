package com.domain.controllers;

import javax.validation.Valid;

import com.domain.dto.ResponseData;
import com.domain.models.entities.Product;
import com.domain.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  
  @Autowired
  private ProductService productService;
  
  @PostMapping
  public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){
    ResponseData<Product> responseData = new ResponseData<>();

    if(errors.hasErrors()){
      for (ObjectError err : errors.getAllErrors()) {
        responseData.getMessages().add(err.getDefaultMessage());
      }
      responseData.setStatus(false);
      responseData.setPayload(null);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    responseData.setStatus(true);
    responseData.setPayload(productService.save(product));
    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

  @GetMapping
  public Iterable<Product> findAll(){

    return productService.findAll();
  }

  @GetMapping("/{id}")
  public Product findById(@PathVariable Long id){

    return productService.findOne(id);
  }

  @PatchMapping
  public Product update(@RequestBody Product product){
    return productService.save(product);
  }

  @DeleteMapping("/{id}")
  public int delete(@PathVariable Long id){
    return productService.remove(id);
  }


}
