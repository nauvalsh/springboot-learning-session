package com.domain.controllers;

import javax.validation.Valid;

import com.domain.dto.CategoryDto;
import com.domain.dto.ResponseData;
import com.domain.models.entities.Category;
import com.domain.services.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryDto categoryDto, Errors errors){
    ResponseData responseData = new ResponseData<>();

    if(errors.hasErrors()){
      for (ObjectError err : errors.getAllErrors()) {
        responseData.getMessages().add(err.getDefaultMessage());
      }

      responseData.setStatus(false);
      responseData.setPayload(null);

      return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    Category category = modelMapper.map(categoryDto, Category.class);
    Category createCategory = categoryService.save(category);

    responseData.setStatus(true);
    responseData.setPayload(createCategory);

    return new ResponseEntity<>(responseData, HttpStatus.OK);
  }

  @GetMapping
  public Iterable<Category> findAll(){
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public Category findOne(@PathVariable("id") Long id){
    return categoryService.findOne(id);
  }

  @PatchMapping
  public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryDto categoryDto, Errors errors){
    ResponseData responseData = new ResponseData<>();

    if(errors.hasErrors()){
      for (ObjectError err : errors.getAllErrors()) {
        responseData.getMessages().add(err.getDefaultMessage());
      }

      responseData.setStatus(false);
      responseData.setPayload(null);

      return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    Category category = modelMapper.map(categoryDto, Category.class);
    Category createCategory = categoryService.save(category);

    responseData.setStatus(true);
    responseData.setPayload(createCategory);

    return new ResponseEntity<>(responseData, HttpStatus.OK);
  }

}
