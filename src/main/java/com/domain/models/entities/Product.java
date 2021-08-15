package com.domain.models.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tb_product")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id"
)
public class Product implements Serializable {

  private static final long serialVersionUID=1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Name is mandatory")
  @Column(name="product_name", length = 100)
  private String name;

  @NotEmpty(message = "Description is mandatory")
  @Column(name="product_description", length = 1000)
  private String description;

  private double price;
  
  @ManyToOne
  private Category category;


  @ManyToMany(fetch = FetchType.LAZY, targetEntity = Supplier.class)
  @JoinTable(
  name = "tb_product_supplier",
  joinColumns = @JoinColumn(name = "product_id"),
  inverseJoinColumns = @JoinColumn(name = "supplier_id"))
  private Set<Supplier> suppliers;

  // Hibernate as our JPA provider requires a default-no-arg constructor.
  // So you should provide default values in Entities' contstructors
  public Product(){}

  public Product(Long id, String name, String description, double price) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Set<Supplier> getSupplier() {
    return suppliers;
  }

  public void setSupplier(Set<Supplier> suppliers) {
    this.suppliers = suppliers;
  }
  
  
}
