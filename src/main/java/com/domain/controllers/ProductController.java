package com.domain.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.domain.dto.ResponseData;
import com.domain.dto.SearchDto;
import com.domain.dto.SupplierDto;
import com.domain.dto.UploadFileDto;
import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.services.FileStorageService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  
  @Autowired
  private ProductService productService;

  @Autowired
  private FileStorageService fileStorageService;
  
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

  @PostMapping("/{productId}")
  public void addSupplier(@RequestBody Supplier supplier, @PathVariable("productId") Long productId){
    productService.addSupplier(supplier, productId);
  }
  
  @GetMapping("/search")
  public List<Product> getByProductName(@RequestParam("name") Optional<String> name, @RequestParam("ids") String ids){
    System.out.println("IDS: " + ids);
    if(name.isPresent()){
      System.out.println("Helow bos");
    }
    

    return productService.findByProductIds(ids);
  }

  @GetMapping("/search/category/{categoryId}")
  public List<Product> getByProductName(@PathVariable("categoryId") long categoryId){
    return productService.findByCategory(categoryId);
  }

  @GetMapping("/search/supplier/{supplierId}")
  public List<Product> getByProductName(@PathVariable("supplierId") Long supplierId){
    return productService.findBySupplier(supplierId);
  }
  
  @PostMapping("/upload")
  public UploadFileDto uploadPhoto(@RequestParam("file") MultipartFile file){
    String fileName = fileStorageService.storeFile(file);

    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(fileName)
            .toUriString();

    return new UploadFileDto(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
  }


}
