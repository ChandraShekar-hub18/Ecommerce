package com.codewithchandu.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codewithchandu.productservice.dto.ProductRequest;
import com.codewithchandu.productservice.dto.ProductResponse;
import com.codewithchandu.productservice.model.Product;
import com.codewithchandu.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
			
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());
	}

	public List<ProductResponse> getAllProducts() {
		 List<Product> products = productRepository.findAll();
		 
		 return products.stream().map(this::mapToProductResponse).toList();
		
	}
	
	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
				
	}

}
