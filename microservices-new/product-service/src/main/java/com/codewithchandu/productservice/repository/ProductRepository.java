package com.codewithchandu.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.codewithchandu.productservice.model.Product;


public interface ProductRepository extends MongoRepository<Product, String>{

}
