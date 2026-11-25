package com.training.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.ApiResponse;
import com.training.dto.ProductRequestDTO;
import com.training.dto.ProductResponseDTO;
import com.training.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	private ProductService productService;
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
//	@GetMapping
//	public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() // response status code 200 ok by default
//	{
//		return ResponseEntity.status(200)
//				.body(
//				new ApiResponse<>(true,,"products fetched!")
//				);
//	}
	
	


	@PostMapping
	public ResponseEntity<ApiResponse<ProductResponseDTO>> addProduct(
			@Valid @RequestBody ProductRequestDTO product)
	{
		ProductResponseDTO savedProduct=productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ApiResponse<>(true, savedProduct, "product saved!")
				);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> fetchProducts()
	{
		return ResponseEntity.ok().body(
				new ApiResponse<>(true, productService.getAllProducts(), "product saved!")
				);
	}
	@PutMapping("{id}")
	public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProduct(
			@Valid @RequestBody ProductRequestDTO product,@PathVariable Long id)
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
				new ApiResponse<>(true, productService.updateProduct(id,product), "product updated!")
				);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponse<Boolean>> updateProduct(@PathVariable Long id)
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
				new ApiResponse<>(true, productService.deleteProduct(id), "product deleted!")
				);
	}
	
	

}
