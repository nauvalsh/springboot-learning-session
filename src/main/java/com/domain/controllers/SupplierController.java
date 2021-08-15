package com.domain.controllers;

import javax.validation.Valid;

import com.domain.dto.ResponseData;
import com.domain.dto.SupplierDto;
import com.domain.models.entities.Supplier;
import com.domain.services.SupplierService;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

  @Autowired
  private SupplierService supplierService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<ResponseData<Supplier>> create (@Valid @RequestBody SupplierDto supplierDto, Errors errors){
    ResponseData<Supplier> responseData = new ResponseData<>();

    if(errors.hasErrors()){
      for (ObjectError err : errors.getAllErrors()) {
        responseData.getMessages().add(err.getDefaultMessage());
      }

      responseData.setStatus(false);
      responseData.setPayload(null);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

    // Tanpa package Model Mapper
    // Supplier supplier = new Supplier();
    // supplier.setName(supplierDto.getName());
    // supplier.setAddress(supplierDto.getAddress());
    // supplier.setEmail(supplierDto.getEmail());

    Supplier saveSupplier = supplierService.save(supplier);

    responseData.setStatus(true);
    responseData.setPayload(saveSupplier);

    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }

  @GetMapping
  public Iterable<Supplier> findAll(@RequestParam("data_param") String data_param){
    System.out.println("Data Param: " + data_param);
    return supplierService.findAll();
  }

  @GetMapping("/{id}")
  public Supplier findOne(@PathVariable("id") Long id){
    return supplierService.findOne(id);
  }

  @PatchMapping
  public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierDto supplierDto, Errors errors){
    ResponseData<Supplier> responseData = new ResponseData<>();

    if(errors.hasErrors()){
      for (ObjectError err : errors.getAllErrors()) {
        responseData.getMessages().add(err.getDefaultMessage());
      }

      responseData.setStatus(false);
      responseData.setPayload(null);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

    Supplier saveSupplier = supplierService.save(supplier);

    responseData.setStatus(true);
    responseData.setPayload(saveSupplier);

    return ResponseEntity.status(HttpStatus.OK).body(responseData);
  }
}
