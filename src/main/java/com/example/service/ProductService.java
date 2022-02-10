package com.example.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.model.Product;



public interface ProductService extends CrudRepository<Product, Integer>{
	
	@Query("Select pro from Product pro where pro.categoryId = ?1")
	List <Product> findByCetegoryId (Integer categoryId);

}
